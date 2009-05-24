package pl.qti.gui;

import java.util.ArrayList;

import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.question.factory.MultipleChoiceFactory;

public class MultipleChoiceQuestion extends SimpleChoiceQuestion {

	private static final long serialVersionUID = 1L;
	
	public MultipleChoiceQuestion(QTIEditor editor) {
		super(editor);
		upperEnable();
		lowerEnable();
	}
	@Override
	public String getQuestionType() {
		return "Multiple Question";
	}
	
	@Override
	public void addAnswers(int count) {
		for(int i = 0; i < count; i++)
		{
			AnswerPanel choice = new AnswerPanel(0, position, this, true);
			choice.setNrLabel((getAnswers().size() + 1) + "");
			getAnswers().add(choice);
			jPanel.add(choice);	
	        position += height; 
	        answerCount++;
	        jScrollPane.validate();
		}
	}
	
	public void saveToXML(String path) throws XmlSaveException
	{
		//ostatni parametr to list z lowerBound, upperBound, defaultValue dokladnie w takiej kolejnosci jak podalem
			MultipleChoiceFactory.saveQuestion(getAnswers(), getQuestionName(), getQuestionText(), path, ((getShuffleValue() == true) ? "true" : "false"), getScoreValues());
	}
}
