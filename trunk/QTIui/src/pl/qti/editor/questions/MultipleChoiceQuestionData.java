package pl.qti.editor.questions;

import pl.qti.editor.exceptions.InvalidXmlException;

public class MultipleChoiceQuestionData extends AbstractQuestion {

	@Override
	public String getText() throws InvalidXmlException {
		return super.parseText("choiceInteraction");
	}

}
