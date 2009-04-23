package pl.wiecek.qti.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleChoiceQuestion extends AbstractQuestionPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;  //  @jve:decl-index=0:visual-constraint="96,118"
	private JPanel jPanel, mainPanel;
	private ArrayList<AnswerPanel> choiceList= new ArrayList<AnswerPanel>();  //  @jve:decl-index=0:
	private int answerCount;
	private int position = 5;
	private final int height = 48;
	private static final int MAX_QUESTIONS = 10;
	
	/**
	 * This method initializes 
	 * 
	 */
	public SimpleChoiceQuestion(QTIEditor editor) {
		super(editor);
		initialize();
	}
	/**
	 * This method initializes this
	 * Add mainPanel to the MainFrame in center position 
	 * 
	 */
	private void initialize() {
        super.add(getMainPanel(), BorderLayout.CENTER);
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			//jScrollPane.setSize(new Dimension(810, 153));
			jScrollPane.setViewportView(getJPanel());
			//jScrollPane.scrollRectToVisible(new Rectangle(0, jScrollPane.getHeight()-2, 10, 10));
			//jPanel.setPreferredSize(jScrollPane.getViewport().getExtentSize()); 
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
			jScrollPane.setLocation(0, 80);
			//jScrollPane.getVerticalScrollBar().setBlockIncrement(30);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBackground(new Color(221, 236, 251));
			jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
			//jPanel.add(getDescJPanel());
			//jPanel.add(getInfoJPanel());
			//jPanel.scrollRectToVisible(new Rectangle(0, jPanel.getHeight()-2, 10, 10));
		}
		return jPanel;
	}
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setBackground(new Color(221, 236, 251));
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			AnswerAddPanel ap = new AnswerAddPanel();
			ap.setListener(this);
			mainPanel.add(ap);
			mainPanel.add(new AnswerDescriptionInfo());
			mainPanel.add(getJScrollPane());
		}
		return mainPanel;
	}

	@Override
	public String getQuestionType() {
		return "Simple Choice";
	}
	@Override
	public ArrayList<AnswerPanel> getAnswers() {
		return choiceList;
	}
	@Override
	public boolean delEnable() {
		return true;
	}
	@Override
	public int getQuestionNumber() {
		return answerCount;
	}
	@Override
	public void saveToXML() {
		// TODO save to XML file
	}

	@Override
	public void addAnswers(int count) {
		for(int i = 0; i < count; i++)
		{
			AnswerPanel choice = new AnswerPanel(0, position, this);
			choice.setNrLabel((choiceList.size() + 1) + "");
			choiceList.add(choice);
			jPanel.add(choice);	
	        position += height; 
	        answerCount++;
	        jScrollPane.validate();
		}
	}

	@Override
	public void removeAnswerAt(int index) {
		int size = choiceList.size() - 1;
		for(int i = index - 1; i < size ; i++)
		{
			AnswerPanel temp = choiceList.get(i);
			AnswerPanel temp2 = choiceList.get(i + 1);
			temp.setNrLabel("" + (i+1));
			temp.setText(temp2.getText());
			temp.setRadioButton(temp2.getRadioButton().isSelected());
			temp.setScoreText(temp2.getScoreText());
			temp.setFeedback(temp2.getFeedback());
		}
        jPanel.remove(choiceList.remove(size));
        position -= height;
        answerCount--;
        this.validate();
        this.repaint();
	}
	
@Override
public void actionPerformed(ActionEvent e) {
		if(answerCount < MAX_QUESTIONS)
			addAnswers(1);
		else
			JOptionPane.showMessageDialog(null, "Sorry ... You can add only 10 answers", "WARNING", JOptionPane.INFORMATION_MESSAGE);
 }	
}  //  @jve:decl-index=0:visual-constraint="64,82"
