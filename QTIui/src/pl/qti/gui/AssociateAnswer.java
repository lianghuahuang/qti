package pl.qti.gui;

import java.util.ArrayList;

import pl.qti.editor.questions.MakePairAnswer;
import pl.qti.editor.questions.SimpleAnswer;

public class AssociateAnswer {

	private ArrayList<SimpleAnswer> answerList;
	private ArrayList<MakePairAnswer> pairList;
	
	public AssociateAnswer(ArrayList<SimpleAnswer> answerList, ArrayList<MakePairAnswer> pairList) {
		this.answerList = answerList;
		this.pairList = pairList;
	}
	public ArrayList<SimpleAnswer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(ArrayList<SimpleAnswer> answerList) {
		this.answerList = answerList;
	}
	public ArrayList<MakePairAnswer> getPairList() {
		return pairList;
	}
	public void setPairList(ArrayList<MakePairAnswer> pairList) {
		this.pairList = pairList;
	}
}
