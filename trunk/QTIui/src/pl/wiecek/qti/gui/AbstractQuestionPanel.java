package pl.wiecek.qti.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import java.util.ArrayList;

public abstract class AbstractQuestionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea jTextArea = null;
	private JLabel titleLabel = null;
	private JLabel SouthLabel = null;
	private JScrollPane scrollPane = null;
	private JPanel NPanel = null;

	/**
	 * This is the default constructor
	 */
	public AbstractQuestionPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		SouthLabel = new JLabel();
		titleLabel = new JLabel("QUESTION");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setPreferredSize(new Dimension(710, 24));
		NPanel = new JPanel();
		NPanel.setBackground(new Color(221, 236, 251));
		NPanel.setLayout(new BorderLayout());
		NPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		NPanel.add(titleLabel, BorderLayout.NORTH);
		NPanel.add(getScrollPane(), BorderLayout.CENTER);
		NPanel.add(new JLabel("                "), BorderLayout.EAST);
		NPanel.add(new JLabel("                "), BorderLayout.WEST);
		SouthLabel.setPreferredSize(new Dimension(710, 24));
		NPanel.add(SouthLabel, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.setSize(705, 500);
		this.setBackground(new Color(153, 204, 255));
		this.add(NPanel, BorderLayout.NORTH);
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setFont(new Font("Tahoma", Font.BOLD, 11));
			jTextArea.setLineWrap(true);
			jTextArea.setWrapStyleWord(true);
		}
		return jTextArea;
	}
	
	/**
	 * This method initializes scrollPane	
	 * Adding ScrollBar to TextArea	
	 * 
	 * @return java.awt.ScrollPane	
	 */
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getJTextArea());
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setEnabled(false);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(50, 120));
			scrollPane.setViewportView(getJTextArea());
			scrollPane.setVisible(true);
		}
		return scrollPane;
	}

	// ABSTRACT METHODS - need to implement in subclasses
	public abstract void saveToXML();
	public abstract String getQuestionType();
	public abstract ArrayList<AnswerPanel> getAnswers();
	public abstract boolean delEnable();
	public abstract void removeAnswerAt(int index);
	public abstract void addAnswers(int count);
	public abstract int getQuestionNumber();

}  //  @jve:decl-index=0:visual-constraint="10,10"
