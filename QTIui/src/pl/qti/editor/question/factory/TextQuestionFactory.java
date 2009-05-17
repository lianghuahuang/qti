package pl.qti.editor.question.factory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.parser.SaveQuestionUtility;
import pl.qti.editor.questions.TextQuestion;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.OpenQuestion;
import pl.qti.gui.QTIEditor;

public class TextQuestionFactory extends AbstractQuestionFactory {

	@Override
	public AbstractQuestionPanel makeQuestion(Document questionXml,
			QTIEditor editor) throws InvalidXmlException {
		this.question = new TextQuestion();
		this.questionPanel = new OpenQuestion(editor);
		this.questionXml = questionXml;
		super.makeHeader();
		return this.questionPanel;
	}
	
	public static void saveQuestion(String title, String text, String filename) throws XmlSaveException
	{
		SaveQuestionUtility.init();
		Document doc = SaveQuestionUtility.getDoc();
		Element assessmentItem = SaveQuestionUtility.getNodeAssesment(doc);
		assessmentItem.setAttribute( SaveQuestionUtility.TITLE, title);
		assessmentItem.setAttribute(SaveQuestionUtility.IDENTIFIER, "extendedText");
		assessmentItem.setAttribute(SaveQuestionUtility.ADAPTIVE, "false");
		assessmentItem.setAttribute(SaveQuestionUtility.TIMEDEP, "false");
		
		Element response = SaveQuestionUtility.createResponse(doc, "single", "string");		
		Element outcome = SaveQuestionUtility.createOutcome(doc, "single", "integer");
		Element itemBody = doc.createElement(SaveQuestionUtility.ITEMB);
		Element extended = doc.createElement("extendedTextInteraction");
		extended.setAttribute("responseIdentifier", "RESPONSE");
		extended.setAttribute("expectedLength", "200");
		Element prompt = doc.createElement(SaveQuestionUtility.PROMPT);
		prompt.setTextContent(text);
		extended.appendChild(prompt);
		itemBody.appendChild(extended);
		
		
		assessmentItem.appendChild(response);
		assessmentItem.appendChild(outcome);
		assessmentItem.appendChild(itemBody);
		doc.appendChild(assessmentItem);
		SaveQuestionUtility.save(doc, filename);
	}

}
