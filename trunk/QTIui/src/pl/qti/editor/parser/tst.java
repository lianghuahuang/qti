package pl.qti.editor.parser;

import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.question.factory.TextQuestionFactory;

public class tst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TextQuestionFactory.saveQuestion("tytul", "test", "c:\\buc.xml");
		} catch (XmlSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
