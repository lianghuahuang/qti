package pl.qti.gui;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.Color;

public class FeedbackPanel extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel infoLabel = null;
	private JTextArea feedbackTextArea = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private AnswerPanel choice = null;
	private JScrollPane jScrollBar = null;
	/**
	 * @param owner
	 */
	public FeedbackPanel(Frame owner, String title, AnswerPanel choice) {
		super(owner, title);
		this.choice = choice;
		initialize();
		feedbackTextArea.setText(choice.getFeedback());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(674, 196);
		this.setResizable(false);
		this.setBackground(new Color(221, 236, 251));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			infoLabel = new JLabel();
			infoLabel.setBounds(new Rectangle(17, 6, 66, 21));
			infoLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			infoLabel.setText("Feedback");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(221, 236, 251));
			jContentPane.add(infoLabel, null);
			jContentPane.add(getFeedbackTextArea(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getCancelButton(), null);
			jContentPane.add(getJScrollBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes feedbackTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getFeedbackTextArea() {
		if (feedbackTextArea == null) {
			feedbackTextArea = new JTextArea();
			feedbackTextArea.setBounds(new Rectangle(8, 28, 650, 106));
			feedbackTextArea.setLineWrap(true);
			feedbackTextArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
			feedbackTextArea.setWrapStyleWord(true);
		}
		return feedbackTextArea;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(529, 140, 50, 19));
			okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
			okButton.setText("OK");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	/**
	 * This method initializes cancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setBounds(new Rectangle(589, 140, 67, 20));
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}
	
	public String getFeedback()
	{
		return feedbackTextArea.getText();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == okButton)
		{
			choice.setFeedback(getFeedback());
		}
		else
		{
			updateTextArea();
		}
		this.setVisible(false);
	}
	
	public void updateTextArea()
	{
		feedbackTextArea.setText(choice.getFeedback());
		feedbackTextArea.repaint();
	}

	/**
	 * This method initializes jScrollBar	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollPane getJScrollBar() {
		if (jScrollBar == null) {
			jScrollBar = new JScrollPane(getFeedbackTextArea());
			jScrollBar.setBounds(new Rectangle(8, 28, 650, 106));
			jScrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollBar.setEnabled(false);
			jScrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollBar.setBackground(Color.white);
			jScrollBar.setForeground(Color.white);
			jScrollBar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
			jScrollBar.setVisible(true);
		}
		return jScrollBar;
	}

}  //  @jve:decl-index=0:visual-constraint="9,10"
