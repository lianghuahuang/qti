package pl.qti.editor.question.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.parser.SaveQuestionUtility;
import pl.qti.editor.questions.MultipleChoiceQuestionData;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.AnswerPanel;
import pl.qti.gui.MultipleChoiceQuestion;
import pl.qti.gui.QTIEditor;

public class MultipleChoiceFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) throws InvalidXmlException {
		this.question = new MultipleChoiceQuestionData();
		this.questionPanel = new MultipleChoiceQuestion(editor);
		this.questionXml = questionXml;		
		super.makeHeader();
		MultipleChoiceQuestion panel = (MultipleChoiceQuestion)this.questionPanel;
		NodeList values = questionXml.getElementsByTagName("value");
		NodeList simpleChoices = questionXml.getElementsByTagName("simpleChoice");
		NodeList mappingEntry = questionXml.getElementsByTagName("mapEntry");
		//TODO upperBound i lowerBound jak TIT dorobi!
		NodeList mapping = questionXml.getElementsByTagName("mapping");
		String upperBound = "";
		if(mapping.item(0).getAttributes().getNamedItem("upperBound")!=null)
			upperBound = mapping.item(0).getAttributes().getNamedItem("upperBound").getNodeValue();
		String lowerBound = "";
		if(mapping.item(0).getAttributes().getNamedItem("lowerBound")!=null)
			lowerBound = mapping.item(0).getAttributes().getNamedItem("lowerBound").getNodeValue();
		String defaultValue = mapping.item(0).getAttributes().getNamedItem("defaultValue").getNodeValue();
		panel.setUpperBound(upperBound);
		panel.setLowerBound(lowerBound);
		panel.setDefaultValue(defaultValue);
		
		HashMap<String, String> feedbacks = QuestionsUtilities.getFeedbacks(questionXml.getElementsByTagName("feedbackInline"));
		if(simpleChoices.getLength()==0)
			throw new InvalidXmlException("There are no answers!");
		// utworzenie odpowiedzi w panelu
		panel.addAnswers(simpleChoices.getLength());
		ArrayList<AnswerPanel> answers = panel.getAnswers();
		// zmapowanie specyficznej punktacji do hasmapy
		HashMap<String, Integer> mappedEntries = new HashMap<String, Integer>();
		for(int i=0;i<mappingEntry.getLength();i++)
		{
			mappedEntries.put(mappingEntry.item(i).getAttributes().getNamedItem("mapKey").getNodeValue().trim(),
					Integer.parseInt(mappingEntry.item(i).getAttributes().getNamedItem("mappedValue").getNodeValue().trim()));
			System.out.println(mappingEntry.item(i).getAttributes().getNamedItem("mapKey").getNodeValue().trim()+" aaa "
					+mappingEntry.item(i).getAttributes().getNamedItem("mappedValue").getNodeValue().trim());
		}
		
		ArrayList<String> correctAns = new ArrayList<String>();
		for(int i=0;i<values.getLength();i++)
		{
			correctAns.add(values.item(i).getTextContent().trim());
		}
		ArrayList<AnswerPanel> newAnswers = new ArrayList<AnswerPanel>();
		// wypelnianie odpowiedzi
		for(int i=0;i<simpleChoices.getLength();i++)
		{
			Node identifier = simpleChoices.item(i).getAttributes().getNamedItem("identifier");
			AnswerPanel ans = answers.get(i);
			Node question = QuestionsUtilities.removeChilds(simpleChoices.item(i));
			ans.setText(question.getTextContent());
			if(feedbacks.containsKey(identifier.getNodeValue()))
			{
				ans.setFeedback(feedbacks.get(identifier.getNodeValue()).trim());
			}
			if(mappedEntries.containsKey(identifier.getNodeValue().trim()))
			{
				ans.setScoreText(mappedEntries.get(identifier.getNodeValue().trim()).toString());
			}
			if(correctAns.contains(identifier.getNodeValue()))
			{
				ans.setRadioButton(true);
			}
			newAnswers.add(ans);
		}
		panel.addAnswers(newAnswers);
		return panel;
	}
	
	public static void saveQuestion(ArrayList<AnswerPanel> answers, String title, String question, String filename, String isShuffle,
			ArrayList<String> bounds) throws XmlSaveException
	{
		SaveQuestionUtility.init();
		Document doc = SaveQuestionUtility.getDoc();
		
		Element assessmentItem = SaveQuestionUtility.getNodeAssesment(doc);
		assessmentItem.setAttribute( SaveQuestionUtility.TITLE, title);
		assessmentItem.setAttribute(SaveQuestionUtility.IDENTIFIER, "choice");
		assessmentItem.setAttribute(SaveQuestionUtility.ADAPTIVE, "false");
		assessmentItem.setAttribute(SaveQuestionUtility.TIMEDEP, "false");
		
		Element response = SaveQuestionUtility.createResponse(doc, "multiple", "identifier");	
		Element correctResponse = doc.createElement(SaveQuestionUtility.CORRECT_RESPONSE);

		Element mapping = doc.createElement("mapping");
		mapping.setAttribute("lowerBound", bounds.get(0));
		mapping.setAttribute("upperBound", bounds.get(1));
		mapping.setAttribute("defaultValue", bounds.get(2));
		
		Element itemBody = doc.createElement(SaveQuestionUtility.ITEMB);
		Element extended = doc.createElement("choiceInteraction");
		extended.setAttribute("responseIdentifier", "RESPONSE");
		extended.setAttribute("shuffle", isShuffle);
		extended.setAttribute("maxChoices", "0");

		int i = 0;
		for(AnswerPanel a: answers)
		{
			if(a.getRadioButton().isSelected())
			{
				Element value = doc.createElement(SaveQuestionUtility.VALUE);
				value.setTextContent(i+"");
				correctResponse.appendChild(value);
			}
			if(a.getScoreText().trim().length()>0)
			{
				Element mapEntry = doc.createElement("mapEntry");
				mapEntry.setAttribute("mapKey", i+"");
				mapEntry.setAttribute("mappedValue", a.getScoreText().trim());
				mapping.appendChild(mapEntry);
			}
			Element simpleChoice = doc.createElement("simpleChoice");
			simpleChoice.setAttribute(SaveQuestionUtility.IDENTIFIER, i+"");
			if(a.getFeedback().trim().length()>0)
			{
				Element feedback = doc.createElement(SaveQuestionUtility.FEEDBACK);
				feedback.setTextContent(a.getFeedback().trim());
				simpleChoice.appendChild(feedback);
			}
			simpleChoice.setAttribute("fixed", "false");
			simpleChoice.setTextContent(a.getText().trim());
			extended.appendChild(simpleChoice);
			
			i++;
		}
		response.appendChild(correctResponse);
		response.appendChild(mapping);
		
		Element outcome = SaveQuestionUtility.createOutcome(doc, "single", "integer");
		
		Element prompt = doc.createElement(SaveQuestionUtility.PROMPT);
		prompt.setTextContent(question);
		extended.appendChild(prompt);
		
		itemBody.appendChild(extended);
		
		Element responseProc = doc.createElement(SaveQuestionUtility.RESPONSE_PROC);
		responseProc.setAttribute("template", "http://www.imsglobal.org/question/qti_v2p0/rptemplates/map_response");
		
		assessmentItem.appendChild(response);
		assessmentItem.appendChild(outcome);
		assessmentItem.appendChild(itemBody);
		assessmentItem.appendChild(responseProc);
		doc.appendChild(assessmentItem);
		SaveQuestionUtility.save(doc, filename);
	}

}
