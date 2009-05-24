package pl.qti.gui;

import java.util.ArrayList;

import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.question.factory.MultipleChoiceFactory;

public class MultipleChoiceQuestion extends SimpleChoiceQuestion {

	private static final long serialVersionUID = 1L;
	
	public MultipleChoiceQuestion(QTIEditor editor) {
		super(editor);
	}
	@Override
	public String getQuestionType() {
		return "Multiple Question";
	}
	
	public void saveToXML(String path)
	{
		ArrayList<String> odp = new ArrayList<String>();
		odp.add("1");
		odp.add("1");
		odp.add("1");
		//ostatni parametr to list z lowerBound, upperBound, defaultValue dokladnie w takiej kolejnosci jak podalem
		try {
			MultipleChoiceFactory.saveQuestion(getAnswers(), getQuestionName(), getQuestionText(), path, ((getShuffleValue() == true) ? "true" : "false"), odp);
		} catch (XmlSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
