package pl.qti.editor.parser;
import java.io.File;
import java.text.ParseException;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import pl.qti.editor.exceptions.InvalidXmlException;
import pl.qti.editor.question.factory.AbstractQuestionFactory;
import pl.qti.editor.question.factory.MultipleChoiceFactory;
import pl.qti.editor.question.factory.MultiplePairFactory;
import pl.qti.editor.question.factory.OrderedChoiceFactory;
import pl.qti.editor.question.factory.PairQuestionFactory;
import pl.qti.editor.question.factory.SingleChoiceFactory;
import pl.qti.editor.question.factory.TextQuestionFactory;
import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.QTIEditor;



public class QuestionBuilder {

	private final static String recognitionTag = "responseDeclaration";
	private final static String recAttrCard = "cardinality";
	private final static String recAttrType = "baseType";
	private static HashMap<String, Integer> values = null;
	
	public static AbstractQuestionPanel buildQuestion(File questionFile, QTIEditor editor) 
		throws InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException, InvalidXmlException
		{
			ParserWrapper parser = (ParserWrapper)Class.forName("pl.qti.editor.parser.Xerces").newInstance();	
			Document document = null;
			try {
				parser.setFeature("http://xml.org/sax/features/namespaces", false);
			} catch (SAXNotRecognizedException e) {
				throw new InvalidXmlException(e.getMessage());
			} catch (SAXNotSupportedException e) {
				throw new InvalidXmlException(e.getMessage());
			}
	        try {
				document = parser.parse(questionFile.getAbsolutePath());
			} catch (Exception e) {
				throw new InvalidXmlException(e.getMessage());
			}
	        NodeList elements = document.getElementsByTagName(recognitionTag);
	        NamedNodeMap attributes = elements.item(0).getAttributes();
	        Node attrCard = attributes.getNamedItem(recAttrCard);
	        Node attrType = attributes.getNamedItem(recAttrType);
	        
	        AbstractQuestionFactory questionFactory = null;
	        switch(getValues().get(attrCard.getNodeValue()+"."+attrType.getNodeValue()))
	        {
	        	case 0:
	        		questionFactory = new SingleChoiceFactory();
	        		// create Single choice question factory http://www.imsglobal.org/question/qti_v2p0/examples/items/choice.xml
	        		break;
	        	case 1:
	        		questionFactory = new MultipleChoiceFactory();
		        	// create Multiple choice question factory http://www.imsglobal.org/question/qti_v2p0/examples/items/choice_multiple.xml
		        	break;
	        	case 2:
	        		questionFactory = new OrderedChoiceFactory();
	        		// create Ordered choice question factory http://www.imsglobal.org/question/qti_v2p0/examples/items/order.xml
	        		break;
	        	case 3:
	        		questionFactory = new MultiplePairFactory();
	        		// create Multiple Pair question factory http://www.imsglobal.org/question/qti_v2p0/examples/items/associate.xml
	        		break;
	        	case 4:
	        		questionFactory = new TextQuestionFactory();
	        		// create Text question factory http://www.imsglobal.org/question/qti_v2p0/examples/items/extended_text.xml
	        		break;
	        	case 5:
	        		questionFactory = new PairQuestionFactory();
	        		break;
	        	default:
	        		throw new ParseException("Provided file does not contain proper QTI question!", 0);
	        		
	        }
			return questionFactory.makeQuestion(document, editor);

		}
	
	private static HashMap<String, Integer> getValues()
	{
		if(values==null)
		{
			values = new HashMap<String, Integer>();
			values.put("single.identifier", 0);
			values.put("multiple.identifier", 1);
			values.put("ordered.identifier", 2);
			values.put("multiple.pair", 3);
			values.put("single.string", 4);
			values.put("multiple.pair", 5);
		}
		return values;
	}
}
