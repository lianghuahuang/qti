package pl.qti.gui;


public class YesNoChoiceQuestion extends SimpleChoiceQuestion {

	private static final long serialVersionUID = 1L;
	
	public YesNoChoiceQuestion(QTIEditor editor) {
		super(editor);
	}
	
	@Override
	public boolean delEnable() {
		return false;
	}
	
	@Override
	public String getQuestionType() {
		return "Yes No Question";
	}
	@Override
	public void addAnswers(int count)
	{
		super.addAnswers(count);
		getAnswers().get(0).setText("YES");
		getAnswers().get(1).setText("NO");
	}
}
