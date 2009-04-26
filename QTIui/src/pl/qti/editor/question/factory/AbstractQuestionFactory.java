package pl.qti.editor.question.factory;

import org.w3c.dom.Document;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.questions.AbstractQuestion;
import pl.wiecek.qti.gui.AbstractQuestionPanel;
import pl.wiecek.qti.gui.QTIEditor;

public abstract class AbstractQuestionFactory {
	
	protected AbstractQuestion question;
	protected AbstractQuestionPanel questionPanel;
	protected Document questionXml;
	
	protected void makeHeader() throws InvalidXmlException
	{
		question.setAssesmentItem(questionXml.getElementsByTagName("assessmentItem"));
		question.setOutcomeDeclaration(questionXml.getElementsByTagName("outcomeDeclaration"));
		question.setResponseDeclaration(questionXml.getElementsByTagName("responseDeclaration"));
		question.setItemBody(questionXml.getElementsByTagName("itemBody"));
		question.setResponseProcessing(questionXml.getElementsByTagName("responseProcessing"));
		questionPanel.setTitle(question.getTitle());
		questionPanel.setText(question.getText());
	}
	public abstract AbstractQuestionPanel makeQuestion(Document questionXml, QTIEditor editor) throws InvalidXmlException;
}
