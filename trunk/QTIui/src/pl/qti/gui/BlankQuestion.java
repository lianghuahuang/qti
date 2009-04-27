package pl.qti.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class BlankQuestion extends AbstractQuestionPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel NorthPanel;
	private JPanel CenterPanel;
	private JPanel ButtonPanel;
	private JLabel infoLabel;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JButton copyButton, cutButton, pasteButton, EditButton;
	private JButton CreateBlankButton, DeleteBlankButton, ResetBlanksButton, EditBlankButton;
	private String copiedText = "";
	private int offset;
	private int length;
	private StyledDocument doc;
	private MutableAttributeSet attr = new SimpleAttributeSet();
	private ArrayList<BlankField> blanks = new ArrayList<BlankField>();
	private AnswerPanel blankInfo = new AnswerPanel(0, 75, this);
	private AnswerDescriptionInfo answerDesc = new AnswerDescriptionInfo();
	private BlankField addedBlank;
	private int position, start, end;

	public BlankQuestion(QTIEditor editor) {
		super(editor);
		initialize();
	}
	/**
	 * Add MainPanel to question main panel	
	 */
	private void initialize()
	{
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
			MainPanel.add(getNorthPanel(), BorderLayout.NORTH);
			MainPanel.add(getCenterPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
	}
	
	private void setInitMutableAttributeSetBold()
	{
		StyleConstants.setBold(attr, true);
		StyleConstants.setBackground(attr, Color.red);
	}
	
	private void setInitMutableAttributeSetPlain()
	{
		StyleConstants.setBold(attr, false);
		StyleConstants.setBackground(attr, Color.white);
	}
	
	/**
	 * This method initializes ButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonPanel() {
		if (ButtonPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(5);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(5);
			ButtonPanel = new JPanel();
			ButtonPanel.setLayout(flowLayout);
			ButtonPanel.setBackground(new Color(221, 236, 251));
			ButtonPanel.setMaximumSize(new Dimension(1680,50));
			ButtonPanel.setMinimumSize(new Dimension(800,50));
			ButtonPanel.setPreferredSize(new Dimension(700,50));
			ButtonPanel.add(getCopyButton());
			ButtonPanel.add(getCutButton());
			ButtonPanel.add(getPasteButton());
			ButtonPanel.add(new JLabel("     "));
			ButtonPanel.add(getEditButton());
			ButtonPanel.add(new JLabel("     "));
			ButtonPanel.add(getCreateBlankButton());
			ButtonPanel.add(getDeleteBlankButton());
			ButtonPanel.add(getEditBlankButton());
			ButtonPanel.add(getResetBlanksButton());
			ButtonPanel.setLocation(0, 5);
		}
		return ButtonPanel;
	}
	/**
	 * This methods initializes jButtons	
	 */
	private JButton getCopyButton() {
		if (copyButton == null) {
			copyButton = new JButton();
			copyButton.setText("Copy");
			copyButton.addActionListener(this);
		}
		return copyButton;
	}
	private JButton getPasteButton() {
		if (pasteButton == null) {
			pasteButton = new JButton();
			pasteButton.setText("Paste");
			pasteButton.addActionListener(this);
		}
		return pasteButton;
	}

	private JButton getCutButton() {
		if (cutButton == null) {
			cutButton = new JButton();
			cutButton.setText("Cut");
			cutButton.addActionListener(this);
		}
		return cutButton;
	}
	
	private JButton getEditButton() {
		if (EditButton == null) {
			EditButton = new JButton();
			EditButton.setText("Edit");
			EditButton.addActionListener(this);
		}
		return EditButton;
	}
	
	private JButton getCreateBlankButton()
	{
		if (CreateBlankButton == null) {
			CreateBlankButton = new JButton();
			CreateBlankButton.setText("Create Blank");
			CreateBlankButton.setEnabled(false);
			CreateBlankButton.addActionListener(this);
		}
		return CreateBlankButton;
	}
	private JButton getDeleteBlankButton()
	{
		if (DeleteBlankButton == null) {
			DeleteBlankButton = new JButton();
			DeleteBlankButton.setText("Delete Blank");
			DeleteBlankButton.setEnabled(false);
			DeleteBlankButton.addActionListener(this);
		}
		return DeleteBlankButton;
	}
	private JButton getResetBlanksButton()
	{
		if (ResetBlanksButton == null) {
			ResetBlanksButton = new JButton();
			ResetBlanksButton.setText("Reset Blanks");
			ResetBlanksButton.setEnabled(false);
			ResetBlanksButton.addActionListener(this);
		}
		return ResetBlanksButton;
	}
	private JButton getEditBlankButton()
	{
		if (EditBlankButton == null) {
			EditBlankButton = new JButton();
			EditBlankButton.setText("Edit Blank");
			EditBlankButton.setEnabled(false);
			EditBlankButton.addActionListener(this);
		}
		return EditBlankButton;
	}

	/**
	 * This method initializes CenterPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private Component getCenterPanel() {
		if (CenterPanel == null) {
			CenterPanel = new JPanel();
			CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.Y_AXIS));
			CenterPanel.setBackground(new Color(221, 236, 251));
			CenterPanel.add(getButtonPanel());
			CenterPanel.add(answerDesc);
		}
		return CenterPanel;
	}
	/**
	 * This method initializes NorthPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private Component getNorthPanel() {
		if (NorthPanel == null) {
			infoLabel = new JLabel();
			infoLabel.setText("Write text and then insert Edit to select word to be blank");
			infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			infoLabel.setPreferredSize(new Dimension(30, 40));
			NorthPanel = new JPanel();
			NorthPanel.setLayout(new BorderLayout());
			NorthPanel.add(infoLabel, BorderLayout.NORTH);
			NorthPanel.add(getJScrollPane(), BorderLayout.CENTER);
			NorthPanel.add(new JLabel("                "), BorderLayout.EAST);
			NorthPanel.add(new JLabel("                "), BorderLayout.WEST);
			NorthPanel.setBackground(new Color(221, 236, 251));
		}
		return NorthPanel;
	}

	private JScrollPane getJScrollPane() {
		 scrollPane = new JScrollPane(createTextPane());
		 scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollPane.setPreferredSize(new Dimension(500, 200));
		 scrollPane.setMinimumSize(new Dimension(500, 198));
		 scrollPane.setMaximumSize(new Dimension(500, 203));
		 return scrollPane;
	}
	
	private JTextPane createTextPane() {
		 textPane = new JTextPane();
		 textPane.setPreferredSize(new Dimension(500,200));
		 textPane.setMaximumSize(new Dimension(500,200));
		 textPane.setFont(new Font("DialogInput", Font.PLAIN, 12));
		 StyledDocument doc = textPane.getStyledDocument();
		 addStylesToDocument(doc);
		 doc = textPane.getStyledDocument();
		 return textPane;
		}
	private void addStylesToDocument(StyledDocument doc) {
		 // Initialize some styles.
		 Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		 Style regular = doc.addStyle("regular", def);
		 StyleConstants.setFontFamily(def, "SansSerif");
		 Style s = doc.addStyle("italic", regular);
		 StyleConstants.setItalic(s, true);
		 s = doc.addStyle("bold", regular);
		 StyleConstants.setBold(s, true);
		 s = doc.addStyle("small", regular);
		 StyleConstants.setFontSize(s, 10);
		 s = doc.addStyle("large", regular);
		 StyleConstants.setFontSize(s, 16);
		 s = doc.addStyle( "highlight", regular );
		 StyleConstants.setBackground(s, Color.red);
		}

	@Override
	public void addAnswers(int count) {
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
		return blanks.size();
	}
	@Override
	public String getQuestionType() {
		return "Fill in blink Question";
	}
	@Override
	public void removeAnswerAt(int index) {
	}
	@Override
	public void saveToXML() {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object event = arg0.getSource();
		
		if(event ==  EditButton)
		{
			int answer = JOptionPane.showConfirmDialog ( this, "Do you really want to define blanks?" );
			if ( answer == JOptionPane.YES_OPTION )
			   {
					buttonsEnable(true, false);
			   }
		}
		
		else if(event ==  ResetBlanksButton)
		{
			int answer = JOptionPane.showConfirmDialog ( this, "Do you really want to delete blanks and edit text?" );
			if ( answer == JOptionPane.YES_OPTION )
			   {
					buttonsEnable(false, true);
					removeBlankInfo();
					doc = textPane.getStyledDocument();
					setInitMutableAttributeSetPlain();
					doc.setCharacterAttributes(0, doc.getLength(), attr, false);
					blanks.clear();
			   }
		}
		else if(event == copyButton)
		{
			copiedText = textPane.getSelectedText();
		}
		else if(event == cutButton)
		{
			offset = textPane.getSelectionStart();
			length = textPane.getSelectionEnd() - offset;
			copiedText = textPane.getSelectedText();
			doc = textPane.getStyledDocument();
			try {
				doc.remove(offset, length);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		else if(event ==  pasteButton)
		{
			doc = textPane.getStyledDocument();
			try {
				doc.insertString(textPane.getSelectionStart(), copiedText, null);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		else if(event ==  CreateBlankButton)
		{
			doc = textPane.getStyledDocument();
			setInitMutableAttributeSetBold();
			doc = textPane.getStyledDocument();
			offset = textPane.getSelectionStart();
			length = textPane.getSelectionEnd() - offset;
			if(offset >= doc.getLength())
				return;
			
			removeBlankInfo();
			addedBlank = new BlankField();
			addedBlank.setContent(textPane.getSelectedText());
			addedBlank.setStart(offset);
			addedBlank.setEnd(length + offset);
			blankInfo = new AnswerPanel(0,75,this);
			blankInfo.getJTextArea().setEditable(false);
			blankInfo.getRadioButton().setSelected(true);
			blankInfo.getRadioButton().setEnabled(false);
			blankInfo.setNrLabel("");
			blankInfo.setText(textPane.getSelectedText());
			addedBlank.setDescription(blankInfo);
			blanks.add(addedBlank);
			addBlankInfo();
			
			doc.setCharacterAttributes(offset, length, attr, false);
		}
		else if(event ==  DeleteBlankButton)
		{
			setInitMutableAttributeSetPlain();
			doc = textPane.getStyledDocument();
			for(int i = 0; i < blanks.size(); i++)
			{
				addedBlank = blanks.get(i);
				getPositionStartEnd();
				if(position >= start && position <= end)
				{
					doc.setCharacterAttributes(start, (end - start), attr, false);
					blanks.remove(addedBlank);
					removeBlankInfo();
				}
			}
			System.out.println("SIZE: " + blanks.size());
		}
		else if(event ==  EditBlankButton)
		{
			for(int i = 0; i < blanks.size(); i++)
			{
				addedBlank = blanks.get(i);
				getPositionStartEnd();
				if(position >= start && position <= end)
				{
					removeBlankInfo();
					blankInfo = addedBlank.getDescription();
					addBlankInfo();
				}
			}
		}
	}
	
	public void getPositionStartEnd()
	{
		position = textPane.getCaretPosition();
		start = addedBlank.getStart();
		end = addedBlank.getEnd();
	}
	
	public void addBlankInfo()
	{
		CenterPanel.add(blankInfo);
		CenterPanel.validate();
		CenterPanel.repaint();
	}
	
	public void removeBlankInfo()
	{
		CenterPanel.remove(blankInfo);
		CenterPanel.validate();
		CenterPanel.repaint();
	}
	private void buttonsEnable(boolean blank, boolean edit)
	{
		EditButton.setEnabled(edit);
		cutButton.setEnabled(edit);
		pasteButton.setEnabled(edit);
		copyButton.setEnabled(edit);
		CreateBlankButton.setEnabled(blank);
		DeleteBlankButton.setEnabled(blank);
		ResetBlanksButton.setEnabled(blank);
		EditBlankButton.setEnabled(blank);
		textPane.setEditable(edit);
	}
	
	// private class to storage blank elements
	private class BlankField
	{
		private String content;
		private int start, end;
		private AnswerPanel description;
		
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public AnswerPanel getDescription() {
			return description;
		}
		public void setDescription(AnswerPanel description) {
			this.description = description;
		}
	}
	
	@Override
	public void addAnswers(ArrayList<AnswerPanel> answers) {}	
}
