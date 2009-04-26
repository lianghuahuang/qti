package pl.qti.editor.question.factory;

import org.w3c.dom.Document;

import pl.qti.editor.questions.TextQuestion;
import pl.wiecek.qti.gui.AbstractQuestionPanel;
import pl.wiecek.qti.gui.OpenQuestion;
import pl.wiecek.qti.gui.QTIEditor;

public class TextQuestionFactory extends AbstractQuestionFactory {


	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) {
		this.question = new TextQuestion();
		this.questionPanel = new OpenQuestion(editor);
		this.questionXml = questionXml; 
		super.makeHeader();
		this.questionPanel.setText(this.question.getText());
		return this.questionPanel;
	}

}
