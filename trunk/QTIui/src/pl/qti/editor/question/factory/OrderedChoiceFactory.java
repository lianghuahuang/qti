package pl.qti.editor.question.factory;

import java.util.ArrayList;

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
		ArrayList<Integer> orderInserted = new ArrayList<Integer>();
		ArrayList<SimpleAnswer> answers = new ArrayList<SimpleAnswer>();
		for(int i=0;i<simpleChoices.getLength();i++)
		{
			SimpleAnswer sp = new SimpleAnswer(simpleChoices.item(i).getTextContent().trim());
			Node fixed = simpleChoices.item(i).getAttributes().getNamedItem("fixed");
			if(fixed!=null & fixed.getNodeValue().equalsIgnoreCase("true"))
				sp.setFixed(true);
			answers.add(sp);
			for(int k=0;k<values.getLength();k++)
			{
				if(simpleChoices.item(i).getAttributes().getNamedItem("identifier").getNodeValue().trim().
						equalsIgnoreCase(values.item(k).getTextContent().trim()))
				{
					orderInserted.add(k);
					break;
				}
			}
		}
		panel.setAnswers(answers, orderInserted);
		return panel;
	}

}
