package pl.qti.editor.questions;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;

public class OrderQuestionData extends AbstractQuestion {

	@Override
	public String getText() throws InvalidXmlException {
		NodeList list = this.itemBody.getChildNodes();
		Node prompt = null;
		for(int i=0; i<list.getLength();i++)
		{
			if(list.item(i).getNodeName().equals("orderInteraction"))
			{
				list = list.item(i).getChildNodes();
				break;
			}
		}
		for(int i=0;i<list.getLength();i++)
		{
			if(list.item(i).getNodeName().equals("prompt"))
			{
				prompt = list.item(i);
				break;
			}			
		}
		if(prompt==null)
		{
			throw new InvalidXmlException("Invalid XML file!");
		}
		return prompt.getTextContent().trim();
	}

}
