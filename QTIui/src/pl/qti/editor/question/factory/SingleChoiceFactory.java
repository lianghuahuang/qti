package pl.qti.editor.question.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
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

}
