package pl.qti.editor.question.factory;

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
}
