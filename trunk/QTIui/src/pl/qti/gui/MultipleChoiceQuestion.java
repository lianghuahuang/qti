package pl.qti.gui;

public class MultipleChoiceQuestion extends SimpleChoiceQuestion {

	private static final long serialVersionUID = 1L;
	
	public MultipleChoiceQuestion(QTIEditor editor) {
		super(editor);
	}
	@Override
	public String getQuestionType() {
		return "Multiple Question";
	}
}
