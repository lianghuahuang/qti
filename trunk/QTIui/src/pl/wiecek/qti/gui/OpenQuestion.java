package pl.wiecek.qti.gui;

import java.util.ArrayList;

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
	public void saveToXML() {
		// TODO Auto-generated method stub
	}
}
