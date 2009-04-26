package pl.wiecek.qti.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ComboBoxValues {
	public static final Vector<String> QUESTION_TYPES = new Vector<String>()
	{
		private static final long serialVersionUID = 1L;
		{
			add("Simple Question");
			add("Multiple Question");
			add("Yes No Question");
			add("Open Question");
			add("Fill in blank Question");
			add("Correcr Order Question");
		}
	};
	public static final String[] ANSWERS_NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }; 
	public static final String[] YN_ANSWERS_NUMBER = {"2"};
	public static final Map<String, String> QUESTION_ALIASES =  new HashMap<String, String>()   
	        {  
				private static final long serialVersionUID = 1L;
				{  
	                 put("Simple Question", "pl.wiecek.qti.gui.SimpleChoiceQuestion");  
	                 put("Multiple Question", "pl.wiecek.qti.gui.MultipleChoiceQuestion");  
	                 put("Yes No Question", "pl.wiecek.qti.gui.YesNoChoiceQuestion");  
	                 put("Open Question", "pl.wiecek.qti.gui.OpenQuestion");  
	                 put("Fill in blank Question", "pl.wiecek.qti.gui.BlankQuestion"); 
	                 put("Correcr Order Question", "pl.wiecek.qti.gui.OrderQuestion"); 
	             }  
	         };
	public static String getQuestionAlias(String question)
	{
		return QUESTION_ALIASES.get(question);
	}
}
