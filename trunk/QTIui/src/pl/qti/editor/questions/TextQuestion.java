package pl.qti.editor.questions;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.qti.editor.exceptions.InvalidXmlException;

public class TextQuestion extends AbstractQuestion {
	
	@Override
	public String getText() throws InvalidXmlException
	{
		return parseText("extendedTextInteraction");
	}
}
