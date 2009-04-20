package pl.wiecek.qti.utils;

import pl.wiecek.qti.gui.AbstractQuestionPanel;


public class ClassLoader {
	
	public static AbstractQuestionPanel createQuestion(String type)
	{
		Object object = null;
	      try {
	          object = Class.forName(type).newInstance();
	      } catch (InstantiationException e) {
	          e.printStackTrace();
	      } catch (IllegalAccessException e) {
	    	  e.printStackTrace();
	      } catch (ClassNotFoundException e) {
	    	  e.printStackTrace();
	      }
	      return (AbstractQuestionPanel)object;
	}

}
