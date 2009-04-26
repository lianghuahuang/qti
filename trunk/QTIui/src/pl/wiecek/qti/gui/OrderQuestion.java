package pl.wiecek.qti.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OrderQuestion extends AbstractQuestionPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel CenterPanel;
	
	
	public OrderQuestion(QTIEditor editor) {
		super(editor);
		super.add(getMainPanel(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes MainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (MainPanel == null) {
			MainPanel = new JPanel();
			MainPanel.setLayout(new BorderLayout());
			MainPanel.add(new TableOfAnswers(), BorderLayout.NORTH);
			MainPanel.add(getCenterPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
	}
	
	private Component getCenterPanel() {
		if (CenterPanel == null) {
			CenterPanel = new JPanel();
			CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.Y_AXIS));
			CenterPanel.setBackground(new Color(221, 236, 251));
			CenterPanel.add(new CorrectOrderList());
		}
		return CenterPanel;
	}

	@Override
	public void addAnswers(int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delEnable() {
		return false;
	}

	@Override
	public ArrayList<AnswerPanel> getAnswers() {
		return null;
	}

	@Override
	public int getQuestionNumber() {
		return 0;
	}

	@Override
	public String getQuestionType() {
		return "Correcr Order Question";
	}

	@Override
	public void removeAnswerAt(int index) {
		
	}

	@Override
	public void saveToXML() {
		// TODO Auto-generated method stub
		
	}

}
