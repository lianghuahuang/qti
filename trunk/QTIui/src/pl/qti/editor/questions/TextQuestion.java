package pl.qti.editor.questions;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TextQuestion extends AbstractQuestion {
	
	@Override
	public String getText()
	{
		NodeList list = this.itemBody.getChildNodes();
		Node prompt = null;
		for(int i=0; i<list.getLength();i++)
		{
			if(list.item(i).getNodeName().equals("extendedTextInteraction"))
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
		return prompt.getTextContent();
	}
}
