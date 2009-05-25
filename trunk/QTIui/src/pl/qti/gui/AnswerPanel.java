package pl.qti.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class AnswerPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JLabel nrLabel = null;
	private JTextArea ChoiceTextArea = null;
	private JScrollPane AnswerScrollBar = null;
	private JRadioButton CorrectRadioButton = null;
	private JTextField ScoreTextField = null;
	private JButton feedbackButton = null;
	private JButton deleteButton = null;
	private AbstractQuestionPanel  parent;
	private String feedback = "";  //  @jve:decl-index=0:
	private FeedbackPanel f = null;  
	private int x, y;
	private boolean enable;
	/**
	 * This is the default constructor
	 */
	public AnswerPanel() {
		super();
		initialize(0, 0);
	}
	
	public AnswerPanel(int x, int y) {
		super();
		initialize(x, y);
	}
	/**
	 * This is the set position constructor
	 */
	public AnswerPanel(int x, int y, AbstractQuestionPanel parent, boolean enable) {
		super();
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.enable = enable;
		initialize(x, y);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int x, int y) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(5);
		nrLabel = new JLabel();
		
		nrLabel.setPreferredSize(new Dimension(15, 30));
		nrLabel.setText("1");
		this.setLayout(flowLayout);
		this.setBackground(new Color(221, 236, 251));
		this.setMaximumSize(new Dimension(1680,50));
		this.setMinimumSize(new Dimension(800,50));
		this.setPreferredSize(new Dimension(700,50));
		this.setLocation(x, y);
		this.add(nrLabel, null);
		this.add(getChoiceTextArea(), null);
		this.add(getAnswerScrollBar(), null);
		this.add(getCorrectRadioButton(), null);
		this.add(getScoreTextField(), null);
		this.add(getFeedbackButton(), null);
		this.add(getDeleteButton(), null);
	}

	/**
	 * This method initializes ChoiceTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getChoiceTextArea() {
		if (ChoiceTextArea == null) {
			ChoiceTextArea = new JTextArea();
			ChoiceTextArea.setLineWrap(true);
			ChoiceTextArea.setFont(new Font("Arial", Font.PLAIN, 11));
			ChoiceTextArea.setWrapStyleWord(true);
			this.setMinimumSize(new Dimension(100,40));
		}
		return ChoiceTextArea;
	}

	/**
	 * This method initializes AnswerScrollBar	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollPane getAnswerScrollBar() {
		if (AnswerScrollBar == null) {
			AnswerScrollBar = new JScrollPane(getChoiceTextArea());
			AnswerScrollBar.setPreferredSize(new Dimension(400, 35));
			AnswerScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			//AnswerScrollBar.setEnabled(false);
			AnswerScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			AnswerScrollBar.setBackground(Color.white);
			AnswerScrollBar.setForeground(Color.white);
			AnswerScrollBar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
			AnswerScrollBar.setVisible(true);
		}
		return AnswerScrollBar;
	}

	/**
	 * This method initializes CorrectRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getCorrectRadioButton() {
		if (CorrectRadioButton == null) {
			CorrectRadioButton = new JRadioButton();
			CorrectRadioButton.setBackground(new Color(221, 236, 251));
			CorrectRadioButton.addActionListener(this);
		}
		return CorrectRadioButton;
	}

	/**
	 * This method initializes ScoreTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getScoreTextField() {
		if (ScoreTextField == null) {
			ScoreTextField = new JTextField();
			ScoreTextField.setPreferredSize(new Dimension(40, 25));
			ScoreTextField.setEnabled(enable);
		}
		return ScoreTextField;
	}

	/**
	 * This method initializes feedbackButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFeedbackButton() {
		if (feedbackButton == null) {
			feedbackButton = new JButton();
			feedbackButton.setText("Add feedback");
			feedbackButton.addActionListener(this);
		}
		return feedbackButton;
	}

	/**
	 * This method initializes deleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setIcon(new ImageIcon(getClass().getResource("/icons/icon-delete.gif")));
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}
	
	public void setNrLabel(String msg)
	{
		nrLabel.setText(msg);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == feedbackButton)
		{
			if(f == null)
			{
				f = new FeedbackPanel(new Frame(), "Feedback", this);
		        f.setLocationRelativeTo(this);
		        f.setVisible(true);
			}
			else
			{
				f.setVisible(true);
			}
		}
		if(source == CorrectRadioButton)
		{
			if(!parent.getQuestionType().equals("Multiple Question"))
			{
				ArrayList<AnswerPanel> answ = parent.getAnswers();
				for(int i = 0; i < answ.size(); i++)
					answ.get(i).getRadioButton().setSelected(false);
				CorrectRadioButton.setSelected(true);
			}
		}
		if(source == deleteButton)
		{
			if(parent.delEnable())
			 {
				int answer = JOptionPane.showConfirmDialog ( this, "Do you really want to delete this answer?" );
				if ( answer == JOptionPane.YES_OPTION )
				   {
					  if(!(parent.getQuestionNumber() > 2))
						  JOptionPane.showMessageDialog(this, "There must be at least 2 answers", "WARNING", JOptionPane.INFORMATION_MESSAGE);
				      else
						parent.removeAnswerAt(new Integer(nrLabel.getText()));
				   }
		     }
			else
				JOptionPane.showMessageDialog(this, "Delete is unavailable", "WARNING", JOptionPane.INFORMATION_MESSAGE);
	   }
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setText(String txt) {
		ChoiceTextArea.setText(txt);
	}
	
	public String getText() {
		return ChoiceTextArea.getText();
	}
	
	public JTextArea getJTextArea() {
		return ChoiceTextArea;
	}
	
	public void setRadioButton(Boolean selected) {
		CorrectRadioButton.setSelected(selected);
	}
	
	public JRadioButton getRadioButton() {
		return CorrectRadioButton;
	}
	
	public void setScoreText(String txt) {
		ScoreTextField.setText(txt);
	}
	
	public String getScoreText() {
		return ScoreTextField.getText();
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
		if(f != null)
		{
		  f.updateTextArea();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
