package pl.qti.editor.parser;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;



public class Test {

	
	public static void main(String[] args) throws Exception
	{
		ParserWrapper parser = (ParserWrapper)Class.forName("Xerces").newInstance();
        Document document = parser.parse(args[0]);
        String elementName = "pies";
        NodeList elements = document.getElementsByTagName(elementName);

        // is there anything to do?
        if (elements == null) {
            return;
        }
        String attributeName = null;
        attributeName = "syn";
        // print all elements
        if (attributeName == null) {
            int elementCount = elements.getLength();
            for (int i = 0; i < elementCount; i++) {
                Element element = (Element)elements.item(i);
                System.out.println("element: "+element.getLocalName());
            }
        }
        // print elements with given attribute name
        else {
            int elementCount = elements.getLength();
            for (int i = 0; i < elementCount; i++) {
                Element      element    = (Element)elements.item(i);
                NamedNodeMap attributes = element.getAttributes();
                if (attributes.getNamedItem(attributeName) != null) {
                	System.out.println("element: "+element.getLocalName());
                    System.out.println(attributes.getNamedItem(attributeName).getLocalName()+" value:"+attributes.getNamedItem(attributeName).getNodeValue());
                }
            }
        }        
	}
}
