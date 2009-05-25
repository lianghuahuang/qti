package pl.qti.editor.question.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.parser.SaveQuestionUtility;
import pl.qti.editor.questions.MakePairAnswer;
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
		MakePairQuestion panel = (MakePairQuestion) this.questionPanel;
		NodeList mappingList = questionXml.getElementsByTagName("mapEntry");
		ArrayList<SimpleAnswer> sa = new ArrayList<SimpleAnswer>();
		for(String key:associations.keySet())
		{
			sa.add(associations.get(key));
		}
		
		ArrayList<MakePairAnswer> answers = new ArrayList<MakePairAnswer>();
		for(int i=0;i<mappingList.getLength();i++)
		{
			String[] identifiers = mappingList.item(i).getAttributes().getNamedItem("mapKey").getTextContent().split(" ");
			MakePairAnswer mp = new MakePairAnswer();
			mp.setCorrect(true);
			mp.setScore(Double.parseDouble((mappingList.item(i).getAttributes().getNamedItem("mappedValue")).getTextContent()));
			mp.setLHS(associations.get(identifiers[0].trim()).getValue());
			mp.setRHS(associations.get(identifiers[1].trim()).getValue());
			answers.add(mp);
		}
		panel.setDefaultValue(questionXml.getElementsByTagName("mapping").item(0).getAttributes().getNamedItem("defaultValue").getNodeValue());
		
		panel.setAnswers(sa, answers);
		return panel;
	}
	
	public void saveQuestion(ArrayList<SimpleAnswer> answers, ArrayList<MakePairAnswer> pairs, String title, String question, String defaultValue) throws XmlSaveException
	{
		SaveQuestionUtility.init();
		Document doc = SaveQuestionUtility.getDoc();
		
		Element assessmentItem = SaveQuestionUtility.getNodeAssesment(doc);
		assessmentItem.setAttribute( SaveQuestionUtility.TITLE, title);
		assessmentItem.setAttribute(SaveQuestionUtility.IDENTIFIER, "associate");
		assessmentItem.setAttribute(SaveQuestionUtility.ADAPTIVE, "false");
		assessmentItem.setAttribute(SaveQuestionUtility.TIMEDEP, "false");
		
		Element response = SaveQuestionUtility.createResponse(doc, "multiple", "pair");	
		Element correctResponse = doc.createElement(SaveQuestionUtility.CORRECT_RESPONSE);	
		
		Element mapping = doc.createElement(SaveQuestionUtility.MAPPING);
		
		Element outcome = SaveQuestionUtility.createOutcome(doc, "single", "float");		
	}
	
	

}
