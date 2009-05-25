package pl.qti.editor.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import pl.qti.editor.exceptions.XmlSaveException;

public class SaveQuestionUtility {

	private static LSSerializer dom3Writer;
	private static LSOutput output;
	private static SaveQuestionUtility save;
	private static String assessment = "assessmentItem";
	private static String schemaName = "xsi:schemaLocation";
	private static String schemaValue = "http://www.imsglobal.org/xsd/imsqti_v2p0 imsqti_v2p0.xsd";
	
	public static String TITLE = "title";
	public static String IDENTIFIER = "identifier";
	public static String ADAPTIVE = "adaptive";
	public static String TIMEDEP = "timeDependent";
	public static String CARDINALITY = "cardinality";
	public static String BASET = "baseType";
	public static String CORRECT_RESPONSE = "correctResponse";
	
	public static String RESPONSE = "responseDeclaration";
	public static String OUTCOME = "outcomeDeclaration";
	public static String ITEMB = "itemBody";
	public static String PROMPT = "prompt";
	public static String VALUE = "value";
	public static String DEFAULTV = "defaultValue";
	public static String FEEDBACK = "feedbackInline";
	public static String RESPONSE_PROC = "responseProcessing";
	public static String MAPPING = "mapping";
	
	public SaveQuestionUtility() throws XmlSaveException
	{
		System.setProperty(DOMImplementationRegistry.PROPERTY,
        "org.apache.xerces.dom.DOMImplementationSourceImpl");
		DOMImplementationRegistry registry = null;
		try {
			registry = DOMImplementationRegistry.newInstance();
		} catch (ClassCastException e) {
			throw new XmlSaveException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new XmlSaveException(e.getMessage());
		} catch (InstantiationException e) {
			throw new XmlSaveException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new XmlSaveException(e.getMessage());
		}	
		DOMImplementation domImpl = registry.getDOMImplementation("LS 3.0");
		DOMImplementationLS implLS = (DOMImplementationLS)domImpl;
		dom3Writer = implLS.createLSSerializer();
		output=implLS.createLSOutput();
	}
	
	public static void init() throws XmlSaveException
	{
		if(save==null)
			save = new SaveQuestionUtility();
	}

	public static void save(Document doc, String filename) throws XmlSaveException
	{
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(new File(filename));
		} catch (FileNotFoundException e) {
			throw new XmlSaveException(e.getMessage());
		}
	 	output.setByteStream(outputStream);
	 	dom3Writer.write(doc,output);
		
	}

	public static Document getDoc() throws XmlSaveException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(false);
		dbf.setCoalescing(false);
		dbf.setExpandEntityReferences(true);
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(false);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XmlSaveException(e.getMessage());
		}
		Document document = db.newDocument();
		return document;
	}
	
	public static Element getNodeAssesment(Document doc)
	{
		Element ass = doc.createElement(assessment);
		ass.setAttribute(schemaName, schemaValue);
		return ass;
	}
	
	public static Element createResponse(Document doc, String cardinality, String baseT)
	{
		return SaveQuestionUtility.createSpecifiedElement(doc, cardinality, baseT, 
				SaveQuestionUtility.RESPONSE, "RESPONSE");
	}
	public static Element createOutcome(Document doc, String cardinality, String baseT)
	{
		return SaveQuestionUtility.createSpecifiedElement(doc, cardinality, baseT, 
				SaveQuestionUtility.OUTCOME, "SCORE");
	}
	
	private static Element createSpecifiedElement(Document doc, String cardinality, String baseT, 
			String elemN, String elemV)
	{
		Element element = doc.createElement(elemN);
		element.setAttribute(SaveQuestionUtility.BASET, baseT);
		element.setAttribute(SaveQuestionUtility.IDENTIFIER, elemV);
		element.setAttribute(SaveQuestionUtility.CARDINALITY, cardinality);	
		return element;
	}
}
