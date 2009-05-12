package pl.qti.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JTextArea;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class AbstractQuestionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea jTextArea = null;
	private JLabel titleLabel = null;
	private JScrollPane scrollPane = null;
	private JPanel NPanel = null;
	private JPanel ValuesPanel = null;
	private JPanel NameValuesPanel = null;
	private JPanel newQuestionNamePanel = null;
	private JLabel newNameLabel = null;
	private JTextField newNameTextField = null;
	private JButton newNameButton = null;
	private QTIEditor editor = null;
	private DefaultListModel model;
	private JLabel lowerBoundLabel;
	private JLabel upperBoundLabel;
	private JLabel defaultValueLabel;
	private JTextField lowerBoundText;
	private JTextField upperBoundText;
	private JTextField defaultValueText;

	/**
	 * This is the default constructor
	 */
	public AbstractQuestionPanel(QTIEditor editor) {
		super();
		this.editor = editor;
		initialize();
	}
	
	public AbstractQuestionPanel()
	{
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
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
		NPanel.add(getNameVauesPanel(), BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.setSize(705, 500);
		this.add(NPanel, BorderLayout.NORTH);
		this.setBackground(new Color(221, 236, 251));
		//setValuesPanel();
		//upperDisable();
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
			scrollPane.setPreferredSize(new Dimension(400, 60));
			scrollPane.setMaximumSize(new Dimension(400, 60));
			scrollPane.setViewportView(getJTextArea());
			scrollPane.setVisible(true);
		}
		return scrollPane;
	}
	
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNewNameJTextField() {
		if (newNameTextField == null) {
			newNameTextField = new JTextField();
			newNameTextField.setPreferredSize(new Dimension(360, 20));
		}
		return newNameTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNewNameJButton() {
		if (newNameButton == null) {
			newNameButton = new JButton();
			newNameButton.setText("CHANGE NAME");
			newNameButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
				    model = (DefaultListModel)editor.getQuestionsList().getModel();
					model.setElementAt(newNameTextField.getText(), editor.getCurrentSelected());
				 }	
			});
		}
		return newNameButton;
	}
	
	private JPanel getNewQuestionNamePanel() {
		if (newQuestionNamePanel == null) {
			newQuestionNamePanel = new JPanel();
			newQuestionNamePanel.setBackground(new Color(221, 236, 251));
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(21);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(11);
			newNameLabel = new JLabel();
			newNameLabel.setText("Insert new question name");
			newNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
			newQuestionNamePanel.setLayout(flowLayout);
			newQuestionNamePanel.setSize(new Dimension(710, 44));
			newQuestionNamePanel.add(newNameLabel, null);
			newQuestionNamePanel.add(getNewNameJTextField(), null);
			newQuestionNamePanel.add(getNewNameJButton(), null);
		}
		return newQuestionNamePanel;
	}
	
	private JPanel getVauesPanel() {
		if (ValuesPanel == null) {
			ValuesPanel = new JPanel();
			ValuesPanel.setBackground(new Color(221, 236, 251));
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(21);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			//flowLayout.setVgap(5);
			lowerBoundLabel = new JLabel();
			lowerBoundLabel.setText("Lower Bound");
			lowerBoundLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
			upperBoundLabel = new JLabel();
			upperBoundLabel.setText("Upper Bound");
			upperBoundLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
			defaultValueLabel = new JLabel();
			defaultValueLabel.setText("Default Value");
			defaultValueLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
			upperBoundText = new JTextField();
			upperBoundText.setPreferredSize(new Dimension(60, 20));
			lowerBoundText = new JTextField();
			lowerBoundText.setPreferredSize(new Dimension(60, 20));
			defaultValueText = new JTextField();
			defaultValueText.setPreferredSize(new Dimension(60, 20));
			ValuesPanel.setLayout(flowLayout);
			ValuesPanel.setSize(new Dimension(710, 44));
			ValuesPanel.add(lowerBoundLabel, null);
			ValuesPanel.add(lowerBoundText, null);
			ValuesPanel.add(upperBoundLabel, null);
			ValuesPanel.add(upperBoundText, null);
			ValuesPanel.add(defaultValueLabel, null);
			ValuesPanel.add(defaultValueText, null);
			ValuesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 1), "Values", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11), new Color(0, 70, 213)));
		}
		return ValuesPanel;
	}
	
	private JPanel getNameVauesPanel() {
		if (NameValuesPanel == null) {
			NameValuesPanel = new JPanel();
			NameValuesPanel.setBackground(new Color(221, 236, 251));
			NameValuesPanel.setLayout(new BorderLayout());
			NameValuesPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			NameValuesPanel.add(getNewQuestionNamePanel(), BorderLayout.NORTH);
		}
		return NameValuesPanel;
	}
	

	// ABSTRACT METHODS - need to implement in subclasses
	public abstract void saveToXML();
	public abstract String getQuestionType();
	public abstract ArrayList<AnswerPanel> getAnswers();
	public abstract boolean delEnable();
	public abstract void removeAnswerAt(int index);
	public abstract void addAnswers(int count);
	public abstract int getQuestionNumber();
	public abstract void addAnswers(ArrayList<AnswerPanel> answers);

	public void setTitle(String title) {
	    model = (DefaultListModel)editor.getQuestionsList().getModel();
		model.addElement(title);
	}

	public void setText(String text) {
		jTextArea.setText(text);
	}
	
	public void upperDisable()
	{
		upperBoundText.setEnabled(false);
		upperBoundLabel.setEnabled(false);
	}
	
	public void lowerDisable()
	{
		lowerBoundText.setEnabled(false);
		lowerBoundLabel.setEnabled(false);
	}
	
	public void defaultDisable()
	{
		defaultValueText.setEnabled(false);
		defaultValueLabel.setEnabled(false);
	}
	
	public void setValuesPanel()
	{
		NameValuesPanel.add(getVauesPanel(), BorderLayout.CENTER);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
