package pl.qti.editor.questions;

public class SimpleAnswer {

	protected String value;
	protected boolean fixed = false;

	public SimpleAnswer()
	{
	}
	public SimpleAnswer(String val)
	{
		this.value = val;
	}
	
	public String getValue() {
		return value.trim();
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	
}
