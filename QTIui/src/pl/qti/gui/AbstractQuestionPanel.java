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

import pl.qti.editor.exceptions.XmlSaveException;

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
	protected JPanel ValuesPanel = null;
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
	
	private String questionName;

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
		
		questionName = getQuestionType();
		
		this.setLayout(new BorderLayout());
		this.setSize(705, 500);
		this.add(NPanel, BorderLayout.NORTH);
		this.setBackground(new Color(221, 236, 251));
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
					questionName = newNameTextField.getText();
				    model = (DefaultListModel)editor.getQuestionsList().getModel();
					model.setElementAt(questionName, editor.getCurrentSelected());
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
			upperBoundText.setText("0");
			lowerBoundText = new JTextField();
			lowerBoundText.setPreferredSize(new Dimension(60, 20));
			lowerBoundText.setText("0");
			defaultValueText = new JTextField();
			defaultValueText.setPreferredSize(new Dimension(60, 20));
			defaultValueText.setText("0");
			ValuesPanel.setLayout(flowLayout);
			ValuesPanel.setSize(new Dimension(710, 44));
			ValuesPanel.add(lowerBoundLabel, null);
			ValuesPanel.add(lowerBoundText, null);
			ValuesPanel.add(upperBoundLabel, null);
			ValuesPanel.add(upperBoundText, null);
			ValuesPanel.add(defaultValueLabel, null);
			ValuesPanel.add(defaultValueText, null);
			ValuesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 1), "Score Values", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 11), new Color(0, 70, 213)));
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
	public abstract void saveToXML(String path) throws XmlSaveException;
	public abstract String getQuestionType();
	public abstract ArrayList<AnswerPanel> getAnswers();
	public abstract boolean delEnable();
	public abstract void removeAnswerAt(int index);
	public abstract void addAnswers(int count);
	public abstract int getQuestionNumber();
	public abstract void addAnswers(ArrayList<AnswerPanel> answers);

	
	public String getQuestionName()
	{
		return questionName;
	}
	
	public void setTitle(String title) {
	    model = (DefaultListModel)editor.getQuestionsList().getModel();
		model.addElement(title);
		questionName = title;
	}

	public void setText(String text) {
		jTextArea.setText(text);
	}
	
	public String getQuestionText() {
		return jTextArea.getText();
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
	
	public void upperEnable()
	{
		upperBoundText.setEnabled(true);
		upperBoundLabel.setEnabled(true);
	}
	
	public void lowerEnable()
	{
		lowerBoundText.setEnabled(true);
		lowerBoundLabel.setEnabled(true);
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
	
	public void setLowerBound(String lower)
	{
		lowerBoundText.setText(lower);
	}
	
	public void setUpperBound(String upper)
	{
		upperBoundText.setText(upper);
	}
	
	public void setDefaultValue(String defaultVal)
	{
		defaultValueText.setText(defaultVal);
	}
	
	public ArrayList<String> getScoreValues()
	{
		ArrayList<String> values = new ArrayList<String>();
		values.add(lowerBoundText.getText());
		values.add(upperBoundText.getText());
		values.add(defaultValueText.getText());
		return values;
	}
	
	public String getDefaultValue()
	{
		return defaultValueText.getText();
	}
	
	public void setScoreValues(ArrayList<String> values)
	{
		setLowerBound(values.get(0));
		setUpperBound(values.get(1));
		setDefaultValue(values.get(2));
	}
	
	public void setShuffleValue(boolean isShuffle) {};

}  //  @jve:decl-index=0:visual-constraint="10,10"
