package pl.qti.editor.question.factory;

import org.w3c.dom.Document;

import pl.wiecek.qti.gui.AbstractQuestionPanel;

public abstract class AbstractQuestionFactory {
	public abstract AbstractQuestionPanel makeQuestion(Document questionXml);
}
