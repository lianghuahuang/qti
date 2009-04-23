package pl.wiecek.qti.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class SimpleChoiceQuestion extends AbstractQuestionPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;  //  @jve:decl-index=0:visual-constraint="96,118"
	private JPanel description,info, jPanel, mainPanel;
	private JCheckBox shuffleBox;
	private JButton addChoice;
	private JLabel nrLabel, shuffleLabel, correct, score, choiceText;
	private ArrayList<AnswerPanel> choiceList= new ArrayList<AnswerPanel>();  //  @jve:decl-index=0:
	private int answerCount;
	private int position = 10;
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
			mainPanel.add(getDescJPanel());
			mainPanel.add(getInfoJPanel());
			//mainPanel.getGraphics().drawLine(0, 60, 800, 60);
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
	
	
	
	
	private JPanel getInfoJPanel() {
		
		info = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(5);
		choiceText = new JLabel("Choice text");
		
		choiceText.setPreferredSize(new Dimension(395, 20));
		choiceText.setHorizontalAlignment(SwingConstants.LEFT);
		choiceText.setHorizontalTextPosition(SwingConstants.LEFT);
		info.setLayout(flowLayout);
		info.setBackground(new Color(221, 236, 251));
		info.setMaximumSize(new Dimension(1680,25));
		info.setMinimumSize(new Dimension(800,25));
		info.add(choiceText, null);
		
		correct = new JLabel("Choice");
		correct.setPreferredSize(new Dimension(35, 20));
		info.add(correct, null);
		
		score = new JLabel("Score");
		score.setPreferredSize(new Dimension(190, 20));
		info.add(score, null);
		info.setLocation(0, 55);
		
		return info;
	}
	
private JPanel getDescJPanel() {
		
		description = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(5);
		nrLabel = new JLabel("Answer");
		
		nrLabel.setPreferredSize(new Dimension(500, 50));
		nrLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nrLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nrLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		description.setLayout(flowLayout);
		description.setBackground(new Color(221, 236, 251));
		description.setMaximumSize(new Dimension(1680,55));
		description.setMinimumSize(new Dimension(800,55));
		description.add(nrLabel, null);
		shuffleBox = new JCheckBox();
		shuffleBox.setBackground(new Color(221, 236, 251));
		description.add(shuffleBox, null);
		shuffleLabel = new JLabel("Shuffle");
		shuffleLabel.setPreferredSize(new Dimension(50, 50));
		shuffleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		shuffleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		description.add(shuffleLabel, null);
	    addChoice = new JButton("Add choice");
		addChoice.addActionListener(this);
		description.add(addChoice, null);
		description.setLocation(0, 0);
		
		return description;
	}

@Override
public void actionPerformed(ActionEvent e) {
		if(answerCount < MAX_QUESTIONS)
			addAnswers(1);
		else
			JOptionPane.showMessageDialog(null, "Sorry ... You can add only 10 answers", "WARNING", JOptionPane.INFORMATION_MESSAGE);
 }	
}  //  @jve:decl-index=0:visual-constraint="64,82"
