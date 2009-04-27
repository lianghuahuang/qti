package pl.qti.editor.question.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.questions.OrderQuestionData;
import pl.qti.editor.questions.SimpleAnswer;
import pl.wiecek.qti.gui.AbstractQuestionPanel;
import pl.wiecek.qti.gui.OrderQuestion;
import pl.wiecek.qti.gui.QTIEditor;

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
		HashMap<String, SimpleAnswer> list = new HashMap<String, SimpleAnswer>();
		for(int i=0;i<simpleChoices.getLength();i++)
		{
			SimpleAnswer sp = new SimpleAnswer(simpleChoices.item(i).getTextContent().trim());
			Node fixed = simpleChoices.item(i).getAttributes().getNamedItem("fixed");
			if(fixed!=null & fixed.getNodeValue().equalsIgnoreCase("true"))
				sp.setFixed(true);
			list.put(simpleChoices.item(i).getAttributes().getNamedItem("identifier").getNodeValue(), sp);
		}
		ArrayList<SimpleAnswer> answers = new ArrayList<SimpleAnswer>();
		for(int i=0;i<values.getLength();i++)
		{
			if(!list.containsKey(values.item(i).getTextContent().trim()))
				throw new InvalidXmlException("Invalid XML file! Correct answer is not present in choices!");
			answers.add(list.get(values.item(i).getTextContent().trim()));
		}
		panel.setAnswers(answers);
		return panel;
	}

}
