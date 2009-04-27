package pl.qti.utils;

import java.lang.reflect.InvocationTargetException;

import pl.qti.gui.AbstractQuestionPanel;
import pl.qti.gui.QTIEditor;


public class ClassLoader {
	
	public static AbstractQuestionPanel createQuestion(String type, QTIEditor editor)
	{
		Object object = null;
	      try {
	    	  object = Class.forName(type).getConstructor(QTIEditor.class).newInstance(editor);
	      } catch (InstantiationException e) {
	          e.printStackTrace();
	      } catch (IllegalAccessException e) {
	    	  e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	    	  e.printStackTrace();
	      } catch (SecurityException e) {
			e.printStackTrace();
		 } catch (NoSuchMethodException e) {
			e.printStackTrace();
		 } catch (IllegalArgumentException e) {
			e.printStackTrace();
		 } catch (InvocationTargetException e) {
			e.printStackTrace();
		 }
	      return (AbstractQuestionPanel)object;
	}

}
