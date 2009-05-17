package pl.qti.editor.question.factory;

import java.util.HashMap;

import org.w3c.dom.Document;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.questions.PairQuestion;
import pl.qti.editor.questions.SimpleAnswer;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.MakePairQuestion;
import pl.qti.gui.QTIEditor;

public class PairQuestionFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) throws InvalidXmlException {
		this.question = new PairQuestion();
		this.questionPanel = new MakePairQuestion(editor);
		this.questionXml = questionXml;		
		super.makeHeader();
		HashMap<String, SimpleAnswer> associations = 
			QuestionsUtilities.getIdentifierToValue(questionXml.getElementsByTagName("simpleAssociableChoice"));
		
		
		return null;
	}

}
