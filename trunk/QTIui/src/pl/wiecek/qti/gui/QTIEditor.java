package pl.wiecek.qti.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextArea;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import java.awt.Font;

import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import pl.wiecek.qti.utils.ComboBoxValues;
import pl.wiecek.qti.utils.ClassLoader;
import pl.wiecek.qti.utils.XMLDirectoryFilter;
import pl.wiecek.qti.utils.XMLFileFilter;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.ComponentOrientation;
import java.io.File;

public class QTIEditor extends JFrame implements MouseListener, ListSelectionListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JMenuBar jJMenuBar;
	private JMenu jMenu;
	private JMenu jMenu1;
	private JMenuItem saveItem;
	private JMenuItem exitItem;
	private JPanel jPanel;
	private JPanel jPanel2;
	private JTabbedPane jTabbedPane;
	private JList questionsList;
	private JScrollPane jScrollPane;
	private  JPopupMenu Pmenu;
	private JMenuItem delete;
	private JMenuItem saveToXML;
	private JPanel mainPanel;
	private JPanel menuMainPanel1;
	private JLabel QTILabel;
	private JLabel delLabel;
	private JLabel addLabel;
	private JLabel exampleLabel;
	private JLabel typeLabel;
	private JLabel number;
	private JButton delButton;
	private JComboBox typeComboBox;
	private JButton addButton;
	private JButton add2jButton;
	private JLabel numberLabel = null;
	private JComboBox numberComboBox = null;
	private JFileChooser fc;
	
	// list of added questions
	private ArrayList<AbstractQuestionPanel> questionList= new ArrayList<AbstractQuestionPanel>(); 
	// inserted question
	private AbstractQuestionPanel insertedQestion;
	// number of added answers
	private int answersNumber = 4;
	// Dialog that display example question usage
	private QuestionExampleDialog questionExample;
	// type of current added question
	private String questionType = ComboBoxValues.QUESTION_TYPES.get(0);  //  @jve:decl-index=0:
	// index of current selected question
	private int currentSelected = -1;
	// tell us if we have selected question
	private boolean selected = true;
	// LOOK AND FEEL
	private final static String LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";  //  @jve:decl-index=0:
	private JMenuItem openItem = null;
	private JMenuItem saveSelectedItem = null;
	private JMenuItem newQuestionItem = null;
	private JMenuItem exitEditorItem = null;

	
	/**
	 * This is the default constructor
	 */
	public QTIEditor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(970, 700);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/icon.png")));
		this.setPreferredSize(new Dimension(671, 457));
		this.setMinimumSize(new Dimension(980, 800));
		this.setResizable(true);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("QTI Editor");
		this.createPopupMenu();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.WEST);
			jContentPane.add(getTabbedJPanel(), BorderLayout.CENTER);
			jContentPane.add(getMainPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
			jJMenuBar.add(getJMenu1());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("File");
			jMenu.add(getNewQuestionItem());
			jMenu.add(getOpenItem());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem());
			jMenu.add(getSaveSelectedItem());
			jMenu.addSeparator();
			jMenu.add(getExitEditorItem());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu1() {
		if (jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("Help");
			jMenu1.add(getJMenuItem1());
		}
		return jMenu1;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (saveItem == null) {
			saveItem = new JMenuItem();
			saveItem.setText("Save ALL");
			saveItem.setHorizontalAlignment(SwingConstants.LEFT);
			saveItem.setHorizontalTextPosition(SwingConstants.LEFT);
			saveItem.setPreferredSize(new Dimension(119, 19));
			saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK, false));
			saveItem.addActionListener(this);
		}
		return saveItem;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (exitItem == null) {
			exitItem = new JMenuItem();
			exitItem.setText("Exit");
		}
		return exitItem;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());			
			jPanel.setBackground(SystemColor.scrollbar);
			jPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 2), "ALL QUESTIONS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), new Color(0, 70, 213)));
			//jPanel.add(getJList(), BorderLayout.NORTH);
			JScrollPane scroll = new JScrollPane(getJList());
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setBackground(SystemColor.scrollbar);
			jPanel.add(scroll, java.awt.BorderLayout.CENTER);
			
;
		}
		return jPanel;
	}

	private JPanel getTabbedJPanel() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BorderLayout());
		jPanel2.setBackground(SystemColor.scrollbar);
			jPanel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 204, 153), 2), "CURRENT OPENED QUESTIONS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), new Color(0, 70, 213)));
;
			jPanel2.add(getJTabbedPane(), java.awt.BorderLayout.CENTER);
		}
		return jPanel2;
	}
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			// Create a tabbed pane
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBackground(Color.red);
			jTabbedPane.setForeground(new Color(204, 0, 0));
			jTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));	
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
	    DefaultListModel model = new DefaultListModel();
		if (questionsList == null) {
			questionsList = new JList(model);
			
			questionsList.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
			questionsList.setForeground(Color.black);
			questionsList.setBackground(SystemColor.info);
			questionsList.setFixedCellHeight(22);
			questionsList.setFixedCellWidth(200);
			questionsList.setAutoscrolls(true);
			questionsList.addMouseListener(this);
			questionsList.addListSelectionListener(this);
		}
		return questionsList;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBackground(SystemColor.scrollbar);
		}
		return jScrollPane;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		 if ((arg0.getClickCount() == 2) && (arg0.getButton() == MouseEvent.BUTTON1) && (questionList.size() != 0)) 
		 {
			jTabbedPane.removeAll();
			currentSelected = questionsList.getSelectedIndex();
			insertedQestion = questionList.get(currentSelected);
			jTabbedPane.addTab(insertedQestion.getQuestionType(), insertedQestion);
		 }
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
			 if ( arg0.isPopupTrigger() && arg0.getButton() == MouseEvent.BUTTON3 ) 
			 {
				 Point p = arg0.getPoint();
				 int index = questionsList.locationToIndex(p);
				 if(index != -1)
				 {
					 Rectangle rec = questionsList.getCellBounds(index, index);
					 if(rec.contains(p))
					 {
						 questionsList.setSelectedIndex(index);
						 Pmenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
					 }
				 }
			 }
	}

	private void createPopupMenu()
	{
		 Pmenu = new JPopupMenu("List Menu");
		 Pmenu.setUI(new BasicPopupMenuUI());
		 Pmenu.updateUI();
		 Pmenu.setFont(new Font("DejaVu Serif", Font.PLAIN, 8));
		 delete = new JMenuItem("Delete");
		 delete.setBackground(SystemColor.scrollbar);
		 delete.setIcon(new ImageIcon(getClass().getResource("/icons/delete-16x16.gif")));
		 Pmenu.add(delete);
		 delete.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e)
		      {
		    	  deleteQuestion();
		      }
		      });
		 Pmenu.addSeparator();
		 saveToXML = new JMenuItem("Save to XML");
		 saveToXML.setBackground(SystemColor.scrollbar);
		 saveToXML.setIcon(new ImageIcon(getClass().getResource("/icons/icon-save.gif")));
		 Pmenu.add(saveToXML);
		 saveToXML.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e)
		      {
		    	  System.out.println("SAVE TO XML"); 
		      }
		      });
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes mainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			QTILabel = new JLabel();
			QTILabel.setText("");
			QTILabel.setIcon(new ImageIcon(getClass().getResource("/icons/qti.JPG")));
			mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.setBackground(SystemColor.scrollbar);
			mainPanel.add(getMenuMainPanel1(), BorderLayout.WEST);
			mainPanel.add(QTILabel, BorderLayout.EAST);
			
		}
		return mainPanel;
	}

	private void deleteQuestion()
	{
		int selectedPosition = questionsList.getSelectedIndex();
		// if there not exist any question - show info
		if(selectedPosition == -1)
		{
			 JOptionPane.showMessageDialog(null, "Please select concrete question", "WARNING", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			 DefaultListModel  m = (DefaultListModel) questionsList.getModel();
			 m.remove(selectedPosition);
			 questionList.remove(selectedPosition);
			 // if deleted tab is now open - close it
			 if(selectedPosition == currentSelected)
			 {
				 jTabbedPane.removeAll();
				 selected = false;
			 }
			 if(selected)
				 currentSelected--;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == delButton)
		{
			deleteQuestion();
		}
		else if(source == add2jButton)
		{
			// use reflection API to create concrete class
			insertedQestion = ClassLoader.createQuestion(ComboBoxValues.QUESTION_ALIASES.get(questionType), this);
			insertedQestion.addAnswers(answersNumber);
			questionList.add(insertedQestion);
			// add question to JList 
			DefaultListModel  model = (DefaultListModel) questionsList.getModel();
			model.addElement(insertedQestion.getQuestionType());
		    // add question to JTabbedPane
			jTabbedPane.removeAll();
			jTabbedPane.addTab(insertedQestion.getQuestionType(), insertedQestion);
			currentSelected = questionList.size() - 1;
			selected = true;
			questionsList.setSelectedIndex(questionList.size() - 1);
		}
		else if(source == numberComboBox)
		{
			answersNumber = numberComboBox.getSelectedIndex() + 1;
		}
		else if(source == typeComboBox)
		{
			numberComboBox.setEnabled(true);
			questionType = (String)typeComboBox.getSelectedItem();
			if(questionType.equals("Yes No Question"))
			{
				numberComboBox.setSelectedIndex(1);
				numberComboBox.setEnabled(false);
				answersNumber = 2;
			}
			else if(questionType.equals("Open Question") || questionType.equals("Fill in blank Question"))
			{
				numberComboBox.setSelectedIndex(0);
				numberComboBox.setEnabled(false);
				answersNumber = 1;
			}
		}
		else if(source == addButton)
		{
			questionExample = new QuestionExampleDialog(this, "Question example");
			questionExample.setLocationRelativeTo(this);
			questionExample.setVisible(true);
			questionExample.setSize(new Dimension(640, 405));
		}
		else if(source == openItem)
		{
			createJFileChooser();
			openFiles();
		}
		else if(source == saveItem)
		{
			saveFile();
		}
		else if(source == saveSelectedItem)
		{
			for(int sel : questionsList.getSelectedIndices())
			{
				System.out.println(sel);
			}
		}
		else if(source == exitEditorItem)
		{
			this.dispose();
			System.exit(0);
		}
	}

	private void openFiles() {

		int returnVal = fc.showOpenDialog(QTIEditor.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
        	
            File[] file = fc.getSelectedFiles();
            System.out.println(file.length);
            for(File f : file)
            {
            	if(f.isDirectory())
            	{
            		for(File f2 : f.listFiles(new XMLDirectoryFilter()))
                    {
            			System.out.println("FILE " + f2.getAbsolutePath());
                    }
            	}
            	else
            		 System.out.println("FILE" + f.getAbsolutePath());
            		
            }
        } else {
        }

		
	}

	private void saveFile() {


		
	}

	/**
	 * This method initializes menuMainPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMenuMainPanel1() {
		if (menuMainPanel1 == null) {
			numberLabel = new JLabel();
			numberLabel.setText("Number of choices");
			numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
			numberLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			numberLabel.setForeground(Color.black);
			numberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			typeLabel = new JLabel();
			typeLabel.setText("Add Question");
			typeLabel.setForeground(Color.black);
			typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			typeLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			exampleLabel = new JLabel();
			exampleLabel.setText("Example Question");
			exampleLabel.setForeground(Color.black);
			exampleLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			exampleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			exampleLabel.setToolTipText("Example Question");
			addLabel = new JLabel();
			addLabel.setText("Question type");
			addLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			addLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addLabel.setForeground(Color.black);
			delLabel = new JLabel();
			delLabel.setText("Delete Question");
			delLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			delLabel.setForeground(Color.black);
			delLabel.setHorizontalAlignment(SwingConstants.CENTER);
			delLabel.setBackground(Color.black);
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setHgap(15);
			gridLayout.setVgap(1);
			gridLayout.setColumns(5);
			menuMainPanel1 = new JPanel();
			menuMainPanel1.setBackground(SystemColor.scrollbar);
			menuMainPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "MENU", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), new Color(255, 0, 51)));
			menuMainPanel1.setLayout(gridLayout);
			menuMainPanel1.add(delLabel, null);
			menuMainPanel1.add(addLabel, null);
			menuMainPanel1.add(numberLabel, null);
			menuMainPanel1.add(exampleLabel, null);
			menuMainPanel1.add(typeLabel, null);
			menuMainPanel1.add(getDelButton(), null);
			menuMainPanel1.add(getTypeComboBox(), null);
			menuMainPanel1.add(getNumberComboBox(), null);
			menuMainPanel1.add(getAddButton(), null);
			menuMainPanel1.add(getAdd2jButton(), null);
		}
		return menuMainPanel1;
	}

	/**
	 * This method initializes delButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDelButton() {
		if (delButton == null) {
			delButton = new JButton();
			delButton.setText("DELETE");
			delButton.setIcon(new ImageIcon(getClass().getResource("/icons/delete-16x16.gif")));
			delButton.addActionListener(this);
		}
		return delButton;
	}

	/**
	 * This method initializes typeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTypeComboBox() {
		if (typeComboBox == null) {
			typeComboBox = new JComboBox(ComboBoxValues.QUESTION_TYPES);
			typeComboBox.setEditable(false);
			typeComboBox.setSelectedIndex(0);
			typeComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
			typeComboBox.setMaximumRowCount(5);
			typeComboBox.setToolTipText("Choose concrete question type");
			typeComboBox.addActionListener(this);
		}
		return typeComboBox;
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setIcon(new ImageIcon(getClass().getResource("/icons/act_info.gif")));
			addButton.setText("INFO");
			addButton.setHorizontalTextPosition(SwingConstants.TRAILING);
			addButton.addActionListener(this);
		}
		return addButton;
	}

	/**
	 * This method initializes add2jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdd2jButton() {
		if (add2jButton == null) {
			add2jButton = new JButton();
			add2jButton.setIcon(new ImageIcon(getClass().getResource("/icons/add2.gif")));
			add2jButton.setText("ADD");
			add2jButton.addActionListener(this);
		}
		return add2jButton;
	}
	
	/**
	 * This method initializes numberComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getNumberComboBox() {
		if (numberComboBox == null) {
			numberComboBox = new JComboBox(ComboBoxValues.ANSWERS_NUMBER);
			numberComboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
			numberComboBox.setMaximumRowCount(5);
			numberComboBox.setSelectedIndex(3);
			numberComboBox.setPreferredSize(new Dimension(27, 20));
			numberComboBox.addActionListener(this);
		}
		return numberComboBox;
	}

	/**
	 * This method initializes openItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getOpenItem() {
		if (openItem == null) {
			openItem = new JMenuItem();
			openItem.setText("Open");
			openItem.setHorizontalAlignment(SwingConstants.LEFT);
			openItem.setHorizontalTextPosition(SwingConstants.LEFT);
			openItem.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			openItem.setPreferredSize(new Dimension(199, 19));
			openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK, false));
			openItem.addActionListener(this);
		}
		return openItem;
	}
	
	public JList getQuestionsList(){
		return questionsList;
	}

	public int getCurrentSelected() {
		return currentSelected;
	}

	/**
	 * This method initializes saveSelectedItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveSelectedItem() {
		if (saveSelectedItem == null) {
			saveSelectedItem = new JMenuItem();
			saveSelectedItem.setText("Save SELECTED");
			saveSelectedItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, false));
			saveSelectedItem.addActionListener(this);
		}
		return saveSelectedItem;
	}

	/**
	 * This method initializes newQuestionItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getNewQuestionItem() {
		if (newQuestionItem == null) {
			newQuestionItem = new JMenuItem();
			newQuestionItem.setText("New");
			newQuestionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK, false));
			newQuestionItem.addActionListener(this);
		}
		return newQuestionItem;
	}

	/**
	 * This method initializes exitEditorItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitEditorItem() {
		if (exitEditorItem == null) {
			exitEditorItem = new JMenuItem();
			exitEditorItem.setText("Exit");
			exitEditorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK, false));
			exitEditorItem.addActionListener(this);
		}
		return exitEditorItem;
	}
	
	private void createJFileChooser()
	{
		if(fc == null)
		{
			// JFileChooser initialization
			fc = new JFileChooser();
			fc.setMultiSelectionEnabled(true);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setDragEnabled(true);
			try {
	            UIManager.setLookAndFeel(LAF);
	            SwingUtilities.updateComponentTreeUI(fc);
	          } catch (Exception exception) {
	            JOptionPane.showMessageDialog(null,
	                "Can't change look and feel",
	                "Invalid PLAF",
	                JOptionPane.ERROR_MESSAGE);
	          }
	          fc.addChoosableFileFilter(new XMLFileFilter());
		}
	}

	public static void main(String[] args)
	{
		QTIEditor f = new QTIEditor();
		try {
            UIManager.setLookAndFeel(LAF);
            SwingUtilities.updateComponentTreeUI(f);
          } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,
                "Can't change look and feel",
                "Invalid PLAF",
                JOptionPane.ERROR_MESSAGE);
          }
		f.setVisible(true);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}	
}  //  @jve:decl-index=0:visual-constraint="10,10"
