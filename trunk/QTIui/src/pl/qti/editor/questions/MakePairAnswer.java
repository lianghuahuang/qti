package pl.qti.editor.questions;

public class MakePairAnswer {

	private String LHS;
	private String RHS;
	private double score;
	private boolean correct;
	
	public MakePairAnswer(String lhs, String rhs) {
		super();
		LHS = lhs;
		RHS = rhs;
	}
	
	public MakePairAnswer(String lhs, String rhs, double score, boolean correct) {
		super();
		LHS = lhs;
		RHS = rhs;
		this.score = score;
		this.correct = correct;
	}

	public String getLHS() {
		return LHS;
	}
	public void setLHS(String lhs) {
		LHS = lhs;
	}
	public String getRHS() {
		return RHS;
	}
	public void setRHS(String rhs) {
		RHS = rhs;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
