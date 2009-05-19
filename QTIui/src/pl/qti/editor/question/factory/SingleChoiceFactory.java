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
import pl.qti.editor.questions.SingleChoiceQuestion;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.AnswerPanel;
import pl.qti.gui.QTIEditor;
import pl.qti.gui.SimpleChoiceQuestion;
import pl.qti.gui.YesNoChoiceQuestion;

public class SingleChoiceFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) throws InvalidXmlException {
		this.question = new SingleChoiceQuestion();
		this.questionPanel = new SimpleChoiceQuestion(editor);
		this.questionXml = questionXml;		
		NodeList simpleChoices = questionXml.getElementsByTagName("simpleChoice");
		if(simpleChoices.getLength()==0)
			throw new InvalidXmlException("There are no answers!");
		if(simpleChoices.getLength()==2 && (simpleChoices.item(0).getTextContent().trim().equalsIgnoreCase("Yes") 
				|| simpleChoices.item(0).getTextContent().trim().equalsIgnoreCase("No")) && (simpleChoices.item(1).getTextContent().trim().equalsIgnoreCase("Yes") 
						|| simpleChoices.item(1).getTextContent().trim().equalsIgnoreCase("No")))
		{
			this.questionPanel = new YesNoChoiceQuestion(editor);
		}
		super.makeHeader();
		HashMap<String, String> feedbacks = QuestionsUtilities.getFeedbacks(questionXml.getElementsByTagName("feedbackInline"));
		SimpleChoiceQuestion panel = (SimpleChoiceQuestion)this.questionPanel;

		NodeList list = questionXml.getElementsByTagName("correctResponse").item(0).getChildNodes();
		String value = null;
		for(int i=0;i<list.getLength();i++)
		{
			if(list.item(i).getNodeName().equalsIgnoreCase("value"))
			{
				value = list.item(i).getTextContent().trim();
				break;
			}
		}
		
		// is shuffle?
		
		
		if(value==null)
			throw new InvalidXmlException("Invalid XML, correct response was not provided.");
		panel.addAnswers(simpleChoices.getLength());
		ArrayList<AnswerPanel> answers = panel.getAnswers();		
		ArrayList<AnswerPanel> newAnswers = new ArrayList<AnswerPanel>();
		// wypelnianie odpowiedzi
		for(int i=0;i<simpleChoices.getLength();i++)
		{
			AnswerPanel ans = answers.get(i);
			Node identifier = simpleChoices.item(i).getAttributes().getNamedItem("identifier");
			Node question = QuestionsUtilities.removeChilds(simpleChoices.item(i));
			ans.setText(question.getTextContent().trim());
			if(feedbacks.containsKey(identifier.getNodeValue()))
			{
				ans.setFeedback(feedbacks.get(identifier.getNodeValue()).trim());
			}
			if(value.equalsIgnoreCase(identifier.getNodeValue()))
			{
				ans.setRadioButton(true);
			}
			newAnswers.add(ans);
		}
		panel.addAnswers(newAnswers);
		return panel;			
	}
	
	public static void saveQuestion(ArrayList<AnswerPanel> answers, String title, String question, String filename, String isShuffle) throws XmlSaveException
	{
		SaveQuestionUtility.init();
		Document doc = SaveQuestionUtility.getDoc();
		
		Element assessmentItem = SaveQuestionUtility.getNodeAssesment(doc);
		assessmentItem.setAttribute( SaveQuestionUtility.TITLE, title);
		assessmentItem.setAttribute(SaveQuestionUtility.IDENTIFIER, "choice");
		assessmentItem.setAttribute(SaveQuestionUtility.ADAPTIVE, "false");
		assessmentItem.setAttribute(SaveQuestionUtility.TIMEDEP, "false");
		
		Element response = SaveQuestionUtility.createResponse(doc, "single", "identifier");	
		Element correctResponse = doc.createElement(SaveQuestionUtility.CORRECT_RESPONSE);
		Element value = doc.createElement(SaveQuestionUtility.VALUE);
		int i = 0;
		for(AnswerPanel a: answers)
		{
			if(a.getRadioButton().isSelected())
			{
				value.setTextContent(i+"");
				break;
			}
			i++;
		}
		correctResponse.appendChild(value);
		response.appendChild(correctResponse);
		
		Element outcome = SaveQuestionUtility.createOutcome(doc, "single", "integer");
		Element defaultV = doc.createElement(SaveQuestionUtility.DEFAULTV);
		Element val2 = doc.createElement(SaveQuestionUtility.VALUE);
		val2.setTextContent("0");
		defaultV.appendChild(val2);
		outcome.appendChild(defaultV);
		
		Element itemBody = doc.createElement(SaveQuestionUtility.ITEMB);
		Element extended = doc.createElement("choiceInteraction");
		extended.setAttribute("responseIdentifier", "RESPONSE");
		extended.setAttribute("shuffle", isShuffle);
		extended.setAttribute("maxChoices", "1");
		Element prompt = doc.createElement(SaveQuestionUtility.PROMPT);
		prompt.setTextContent(question);
		extended.appendChild(prompt);
		
		i=0;
		for(AnswerPanel a:answers)
		{
			Element simpleChoice = doc.createElement("simpleChoice");
			simpleChoice.setAttribute(SaveQuestionUtility.IDENTIFIER, i+"");
			if(a.getFeedback().trim().length()>0)
			{
				Element feedback = doc.createElement(SaveQuestionUtility.FEEDBACK);
				feedback.setTextContent(a.getFeedback().trim());
				simpleChoice.appendChild(feedback);
			}
			simpleChoice.setTextContent(a.getText().trim());
			extended.appendChild(simpleChoice);
		}
		itemBody.appendChild(extended);
		
		assessmentItem.appendChild(response);
		assessmentItem.appendChild(outcome);
		assessmentItem.appendChild(itemBody);
		doc.appendChild(assessmentItem);
		SaveQuestionUtility.save(doc, filename);
		
		
	}

}
