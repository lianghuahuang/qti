package pl.qti.editor.question.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.questions.MultipleChoiceQuestionData;
import pl.wiecek.qti.gui.AbstractQuestionPanel;
import pl.wiecek.qti.gui.AnswerPanel;
import pl.wiecek.qti.gui.MultipleChoiceQuestion;
import pl.wiecek.qti.gui.QTIEditor;

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
		NodeList simpleChoices = questionXml.getElementsByTagName("value");
		NodeList mappingEntry = questionXml.getElementsByTagName("mapEntry");
		//TODO upperBound i lowerBound jak TIT dorobi!
		//TODO feedback
		if(simpleChoices.getLength()==0)
		{
			throw new InvalidXmlException("There are no answers!");
		}
		// utworzenie odpowiedzi w panelu
		panel.addAnswers(simpleChoices.getLength());
		ArrayList<AnswerPanel> answers = panel.getAnswers();
		// zmapowanie specyficznej punktacji do hasmapy
		HashMap<String, Integer> mappedEntries = new HashMap<String, Integer>();
		for(int i=0;i<mappingEntry.getLength();i++)
		{
			mappedEntries.put(mappingEntry.item(i).getAttributes().getNamedItem("mapKey").getNodeValue().trim(),
					Integer.parseInt(mappingEntry.item(i).getTextContent()));
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
			ans.setText(simpleChoices.item(i).getTextContent());
			if(mappedEntries.containsKey(simpleChoices.item(i).getTextContent().trim()))
			{
				ans.setScoreText(mappedEntries.get(simpleChoices.item(i).getTextContent().trim()).toString());
			}
			if(correctAns.contains(identifier.getNodeValue()))
			{
				ans.setRadioButton(true);
			}
			newAnswers.add(ans);
		}
		panel.addAnswers(newAnswers);
		return null;
	}

}
