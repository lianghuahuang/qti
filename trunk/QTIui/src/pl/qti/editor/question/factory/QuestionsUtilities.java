package pl.qti.editor.question.factory;

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.questions.SimpleAnswer;

public class QuestionsUtilities {

	public static Node removeChilds(Node parent)
	{
		NodeList childs = parent.getChildNodes();
		for(int i=0; i<childs.getLength();i++)
		{
			if(childs.item(i).getNodeType()!=Node.TEXT_NODE)
				parent.removeChild(childs.item(i));
		}
		return parent;
	}

	public static HashMap<String, String> getFeedbacks(
			NodeList elementsByTagName) {
		HashMap<String, String> feed = new HashMap<String, String>();
		for(int i=0; i<elementsByTagName.getLength();i++)
		{
			Node identifier = elementsByTagName.item(i).getAttributes().getNamedItem("identifier");
			feed.put(identifier.getNodeValue(), elementsByTagName.item(i).getTextContent());
		}
		return feed;
	}
	
	public static HashMap<String, SimpleAnswer> getIdentifierToValue(NodeList choices)
	{
		HashMap<String, SimpleAnswer> choicesMap = new HashMap<String, SimpleAnswer>();
		for(int i=0; i<choices.getLength();i++)
		{
			SimpleAnswer sa = new SimpleAnswer(choices.item(i).getTextContent().trim());
			Node fixed = choices.item(i).getAttributes().getNamedItem("fixed");
			if(fixed!=null && fixed.getNodeValue().equalsIgnoreCase("true"))
				sa.setFixed(true);
			String identifier = choices.item(i).getAttributes().getNamedItem("identifier").getNodeValue().trim();
			choicesMap.put(identifier, sa);
		}
		return choicesMap;
	}
}
