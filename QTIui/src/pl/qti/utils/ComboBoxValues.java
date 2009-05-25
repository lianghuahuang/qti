package pl.qti.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ComboBoxValues {
	
	private static final String PATH = "/";
	public static final Vector<String> QUESTION_TYPES = new Vector<String>()
	{
		private static final long serialVersionUID = 1L;
		{
			add("Simple Question");
			add("Multiple Question");
			add("Yes No Question");
			add("Open Question");
			//add("Fill in blank Question");
			add("Correcr Order Question");
			add("Make Pair Question");
		}
	};
	public static final String[] ANSWERS_NUMBER = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }; 
	public static final String[] YN_ANSWERS_NUMBER = {"2"};
	public static final Map<String, String> QUESTION_ALIASES =  new HashMap<String, String>()   
	        {  
				private static final long serialVersionUID = 1L;
				{  
	                 put("Simple Question", "pl.qti.gui.SimpleChoiceQuestion");  
	                 put("Multiple Question", "pl.qti.gui.MultipleChoiceQuestion");  
	                 put("Yes No Question", "pl.qti.gui.YesNoChoiceQuestion");  
	                 put("Open Question", "pl.qti.gui.OpenQuestion");  
	                 put("Fill in blank Question", "pl.qti.gui.BlankQuestion"); 
	                 put("Correcr Order Question", "pl.qti.gui.OrderQuestion"); 
	                 put("Make Pair Question", "pl.qti.gui.MakePairQuestion"); 
	             }  
	         };
	         
	public static final Map<String, String> IMAGE_ALIASES =  new HashMap<String, String>()   
	        {  
				private static final long serialVersionUID = 1L;
				{  
	                 put("Simple Question", PATH + "simpleChoice.png");  
	                 put("Multiple Question", PATH + "multipleChoice.png");  
	                 put("Yes No Question", PATH + "multipleChoice.png");  
	                 put("Open Question", PATH + "openQuestion.png");  
	                 put("Fill in blank Question", PATH + "gapQuestion.png"); 
	                 put("Correcr Order Question", PATH + "order.png"); 
	                 put("Make Pair Question", PATH + "makePair.png"); 
	             }  
	         };
	         
	public static String getQuestionAlias(String question)
	{
		return QUESTION_ALIASES.get(question);
	}
	
	public static String getImageAlias(String question)
	{
		return IMAGE_ALIASES.get(question);
	}
}
