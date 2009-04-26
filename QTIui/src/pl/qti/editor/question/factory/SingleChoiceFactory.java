package pl.qti.editor.question.factory;

import org.w3c.dom.Document;

import pl.wiecek.qti.gui.AbstractQuestionPanel;
import pl.wiecek.qti.gui.QTIEditor;

public class SingleChoiceFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) {
		return null;
	}

}
