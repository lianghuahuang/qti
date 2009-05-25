package pl.qti.gui;

public class OrderAnswer {
	private String content;
	private int position;
	private boolean isFixed;
	private int index;
	
	public OrderAnswer()
	{
	}
	
	public OrderAnswer(String content, int position, boolean isFixed)
	{
		this.content = content;
		this.position = position;
		this.isFixed = isFixed;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isFixed() {
		return isFixed;
	}
	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
