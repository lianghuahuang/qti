package pl.wiecek.qti.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

public class OrderQuestion extends AbstractQuestionPanel{

	public OrderQuestion(QTIEditor editor) {
		super(editor);
		super.add(new OrderTest(), BorderLayout.CENTER);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void addAnswers(int count) {
		// TODO Auto-generated method stub
		
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
		return "Correcr Order Question";
	}

	@Override
	public void removeAnswerAt(int index) {
		
	}

	@Override
	public void saveToXML() {
		// TODO Auto-generated method stub
		
	}

}
