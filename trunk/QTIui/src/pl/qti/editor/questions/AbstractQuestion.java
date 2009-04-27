package pl.qti.editor.questions;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;

public abstract class AbstractQuestion {

	protected Node assessmentItem = null;
	protected Node responseDeclaration = null;
	protected Node itemBody = null;
	protected Node responseProcessing = null;
	protected Node outcomeDeclaration = null;
	
	public void setAssesmentItem(NodeList elementsByTagName) {
		assessmentItem = elementsByTagName.item(0);
	}

	public void setResponseDeclaration(NodeList elementsByTagName) {
		responseDeclaration = elementsByTagName.item(0);
	}

	public void setItemBody(NodeList elementsByTagName) {
		itemBody = elementsByTagName.item(0);
	}

	public void setResponseProcessing(NodeList elementsByTagName) {
		if(elementsByTagName.getLength()>0)
			responseProcessing = elementsByTagName.item(0);
	}

	public void setOutcomeDeclaration(NodeList elementsByTagName) {
		outcomeDeclaration = elementsByTagName.item(0);
	}

	public String getTitle() {
		return assessmentItem.getAttributes().getNamedItem("title").getNodeValue();
	}

	public abstract String getText() throws InvalidXmlException;

	public String parseText(String elementName) throws InvalidXmlException
	{
		NodeList list = this.itemBody.getChildNodes();
		Node prompt = null;
		for(int i=0; i<list.getLength();i++)
		{
			if(list.item(i).getNodeName().equals(elementName))
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
