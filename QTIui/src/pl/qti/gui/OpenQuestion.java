package pl.qti.gui;

import java.util.ArrayList;

import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.question.factory.TextQuestionFactory;

public class OpenQuestion extends AbstractQuestionPanel{

	private static final long serialVersionUID = 1L;

	public OpenQuestion(QTIEditor editor) {
		super(editor);
	}

	@Override
	public void addAnswers(int count) {
	}

	@Override
	public boolean delEnable() {
		return false;
	}

	@Override
	public ArrayList<AnswerPanel> getAnswers() {
		return null;
	}

	@Override
	public int getQuestionNumber() {
		return 0;
	}

	@Override
	public String getQuestionType() {
		return "Open Question";
	}

	@Override
	public void removeAnswerAt(int index) {
	}

	@Override
	public void saveToXML(String path) {
		try {
			TextQuestionFactory.saveQuestion(getQuestionName(), getQuestionText(), path);
		} catch (XmlSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addAnswers(ArrayList<AnswerPanel> answers) {}	
}
