package pl.qti.editor.questions;

public class TextQuestion extends AbstractQuestion {
	
	@Override
	public String getText()
	{
		return this.itemBody.getNodeValue();
	}
}
