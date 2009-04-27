package pl.qti.editor.questions;

import pl.qti.editor.exceptions.InvalidXmlException;

public class OrderQuestionData extends AbstractQuestion {

	@Override
	public String getText() throws InvalidXmlException {
		return parseText("orderInteraction");
	}

}
