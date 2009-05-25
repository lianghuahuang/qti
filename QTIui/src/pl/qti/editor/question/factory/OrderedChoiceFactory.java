package pl.qti.editor.question.factory;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.parser.SaveQuestionUtility;
import pl.qti.editor.questions.OrderQuestionData;
import pl.qti.editor.questions.SimpleAnswer;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.OrderAnswer;
import pl.qti.gui.OrderQuestion;
import pl.qti.gui.QTIEditor;

public class OrderedChoiceFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) throws InvalidXmlException {
		this.question = new OrderQuestionData();
		this.questionPanel = new OrderQuestion(editor);
		this.questionXml = questionXml;		
		super.makeHeader();
		OrderQuestion panel = (OrderQuestion)this.questionPanel;
		NodeList simpleChoices = questionXml.getElementsByTagName("simpleChoice");
		if(simpleChoices.getLength()==0)
			throw new InvalidXmlException("There are no answers!");
		NodeList values = questionXml.getElementsByTagName("value");
		ArrayList<Integer> orderInserted = new ArrayList<Integer>();
		ArrayList<SimpleAnswer> answers = new ArrayList<SimpleAnswer>();
		for(int i=0;i<simpleChoices.getLength();i++)
		{
			SimpleAnswer sp = new SimpleAnswer(simpleChoices.item(i).getTextContent().trim());
			Node fixed = simpleChoices.item(i).getAttributes().getNamedItem("fixed");
			if(fixed!=null && fixed.getNodeValue().equalsIgnoreCase("true"))
				sp.setFixed(true);
			answers.add(sp);
		}
		for(int k=0;k<values.getLength();k++)
		{
			for(int i=0;i<simpleChoices.getLength();i++)
			{
				if(simpleChoices.item(i).getAttributes().getNamedItem("identifier").getNodeValue().trim().
						equalsIgnoreCase(values.item(k).getTextContent().trim()))
				{
					orderInserted.add(i);
					break;
				}
			}
		}
		panel.setAnswers(answers, orderInserted);
		return panel;
	}
	
	public static void saveQuestion(ArrayList<OrderAnswer> answers, String title, 
			String question, String isShuffle, String filename) throws XmlSaveException
	{
		SaveQuestionUtility.init();
		Document doc = SaveQuestionUtility.getDoc();
		
		Element assessmentItem = SaveQuestionUtility.getNodeAssesment(doc);
		assessmentItem.setAttribute( SaveQuestionUtility.TITLE, title);
		assessmentItem.setAttribute(SaveQuestionUtility.IDENTIFIER, "order");
		assessmentItem.setAttribute(SaveQuestionUtility.ADAPTIVE, "false");
		assessmentItem.setAttribute(SaveQuestionUtility.TIMEDEP, "false");
		
		Element response = SaveQuestionUtility.createResponse(doc, "ordered", "identifier");	
		Element correctResponse = doc.createElement(SaveQuestionUtility.CORRECT_RESPONSE);

		Element outcome = SaveQuestionUtility.createOutcome(doc, "single", "integer");
		
		Element itemBody = doc.createElement(SaveQuestionUtility.ITEMB);
		Element extended = doc.createElement("orderInteraction");
		extended.setAttribute("responseIdentifier", "RESPONSE");
		extended.setAttribute("shuffle", isShuffle);
		
		Element prompt = doc.createElement(SaveQuestionUtility.PROMPT);
		prompt.setTextContent(question);
		extended.appendChild(prompt);
		Integer[] answersIndexes = new Integer[answers.size()];
		int i=0;
		for(OrderAnswer o: answers)
		{
			Element ans = doc.createElement("simpleChoice");
			if(o.isFixed())
				ans.setAttribute("fixed", "true");
			ans.setAttribute(SaveQuestionUtility.IDENTIFIER, i+"");
			ans.setTextContent(o.getContent());
			answersIndexes[o.getPosition()] = i;
			extended.appendChild(ans);
			i++;
		}

		for(int p:answersIndexes)
		{
			Element val = doc.createElement(SaveQuestionUtility.VALUE);
			val.setTextContent(p+"");
			correctResponse.appendChild(val);
		}
		
		response.appendChild(correctResponse);
		itemBody.appendChild(extended);
		
		Element responseProc = doc.createElement(SaveQuestionUtility.RESPONSE_PROC);
		responseProc.setAttribute("template", "http://www.imsglobal.org/question/qti_v2p0/rptemplates/map_correct");
		
		assessmentItem.appendChild(response);
		assessmentItem.appendChild(outcome);
		assessmentItem.appendChild(itemBody);
		assessmentItem.appendChild(responseProc);
		doc.appendChild(assessmentItem);
		SaveQuestionUtility.save(doc, filename);
	}

}
