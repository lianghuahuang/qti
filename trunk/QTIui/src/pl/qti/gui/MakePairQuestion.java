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
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


public class MakePairQuestion extends AbstractQuestionPanel implements ActionListener, TableModelListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel TablePanel;
	private JTable answersTable;
	private JTable pairTable;
	private JLabel answersTableInfoLabel;
	private JLabel answersTableLabelLeft;
	private JLabel answersTableLabelRigth;
	private JButton answersAddButton, answersDelButton;
	private JScrollPane answersTableScrollPane;
	private JPanel answersTableSouthPanel;
	
	private JLabel pairTableInfoLabel;
	private JLabel pairTableLabelLeft;
	private JLabel pairTableLabelRigth;
	private JButton pairAddButton, pairDelButton;
	private JScrollPane pairTableScrollPane;
	private JPanel pairTableSouthPanel;
	
	private JScrollPane jScrollPane = null;
	
	private TableColumn answersColumn;
	private TableColumn pairColumn, pairColumn_1, pairColumn_2;
	private AnswersTableModel answersTableModel;
	private PairTableModel pairTableModel = null;
 	private JPanel CenterPanel;
 	private int count;
 	private String tempValue;
 	private String removed;
 	private ArrayList<String> answers = new ArrayList<String>();
 	private MyComboBoxEditor boxEditor= new MyComboBoxEditor(answers);
	
	public MakePairQuestion(QTIEditor editor)
	{
		super(editor);
		super.add(getJScrollPane(), BorderLayout.CENTER);
	}
	
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getMainPanel());
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
		}
		return jScrollPane;
	}
	
	private JPanel getMainPanel() {
		if (MainPanel == null) {
			MainPanel = new JPanel();
			MainPanel.setLayout(new BorderLayout());
			MainPanel.add(getTableOfAnswers(), BorderLayout.NORTH);
			MainPanel.add(getTableOfPairsPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
	}



	private JPanel getTableOfPairsPanel() {
		if(CenterPanel == null)
		{
			CenterPanel = new JPanel();
			pairTableLabelRigth = new JLabel(); 
			pairTableLabelRigth.setText("             ");
			pairTableLabelLeft = new JLabel();
			pairTableLabelLeft.setText("             ");
			pairTableInfoLabel = new JLabel();
			pairTableInfoLabel.setText("Make pair");
			pairTableInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			pairTableInfoLabel.setPreferredSize(new Dimension(208, 34));
			pairTableInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			CenterPanel.setSize(531, 251);
			CenterPanel.setLayout(new BorderLayout());
			CenterPanel.add(getPairTableScrollPane(), BorderLayout.CENTER);
			CenterPanel.add(pairTableInfoLabel, BorderLayout.NORTH);
			CenterPanel.add(pairTableLabelLeft, BorderLayout.EAST);
			CenterPanel.add(pairTableLabelRigth, BorderLayout.WEST);
			CenterPanel.add(getPairTableSouthPanel(), BorderLayout.SOUTH);
			CenterPanel.setPreferredSize(new Dimension(500, 250));
			CenterPanel.setMinimumSize(new Dimension(500, 250));
			CenterPanel.setMaximumSize(new Dimension(500, 250));
			CenterPanel.setBackground(new Color(221, 236, 251));
			answers.add("  ");
		}
		return CenterPanel;
	}



	private JScrollPane getPairTableScrollPane() {
		if (pairTableScrollPane == null) {
			pairTableScrollPane = new JScrollPane();
			pairTableScrollPane.setViewportView(getPairTable());
		}
		return pairTableScrollPane;
	}

	private JTable getPairTable() {
		if (pairTable == null) {
			pairTableModel = new PairTableModel();
			pairTable = new JTable(pairTableModel);
			pairTable.setRowHeight(20);
			pairTable.setBackground(Color.white);
			pairTable.setForeground(Color.black);
			pairTable.setGridColor(Color.black);
			pairTable.setFont(new Font("Dialog", Font.PLAIN, 12));
			pairTable.getModel().addTableModelListener(this);
			pairColumn_1 = pairTable.getColumnModel().getColumn(0);
			pairColumn_1.setPreferredWidth(250);
			pairColumn_1.setCellEditor(boxEditor);
			pairColumn_2 = pairTable.getColumnModel().getColumn(1);
			pairColumn_2.setPreferredWidth(250);
			pairColumn_2.setCellEditor(boxEditor);
			pairColumn = pairTable.getColumnModel().getColumn(2);
			pairColumn.setPreferredWidth(95);
			pairColumn.setMaxWidth(95);
			pairColumn.setResizable(false);
			pairColumn = pairTable.getColumnModel().getColumn(3);
			pairColumn.setPreferredWidth(95);
			pairColumn.setMaxWidth(95);
			pairColumn.setResizable(false);
		}
		return pairTable;
	}

	private JPanel getPairTableSouthPanel() {
		if (pairTableSouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			pairTableSouthPanel = new JPanel();
			pairTableSouthPanel.setLayout(flowLayout);
			pairTableSouthPanel.setPreferredSize(new Dimension(137, 53));
			pairTableSouthPanel.add(getPairAddButton(), null);
			pairTableSouthPanel.add(getPairDelButton(), null);
			pairTableSouthPanel.setBackground(new Color(221, 236, 251));
		}
		return pairTableSouthPanel;
	}

	private JButton getPairDelButton() {
		if (pairDelButton == null) {
			pairDelButton = new JButton();
			pairDelButton.setText("Delete Pair");
			pairDelButton.addActionListener(this);
		}
		return pairDelButton;
	}

	private JButton getPairAddButton() {
		if (pairAddButton == null) {
			pairAddButton = new JButton();
			pairAddButton.setText("  Add Pair  ");
			pairAddButton.addActionListener(this);
		}
		return pairAddButton;
	}

	private JPanel getTableOfAnswers() {
		if(TablePanel == null)
		{
			TablePanel = new JPanel();
			answersTableLabelRigth = new JLabel(); 
			answersTableLabelRigth.setText("             ");
			answersTableLabelLeft = new JLabel();
			answersTableLabelLeft.setText("             ");
			answersTableInfoLabel = new JLabel();
			answersTableInfoLabel.setText("Define all the answers in the following table");
			answersTableInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			answersTableInfoLabel.setPreferredSize(new Dimension(208, 34));
			answersTableInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			TablePanel.setSize(531, 251);
			TablePanel.setLayout(new BorderLayout());
			TablePanel.add(getTableScrollPane(), BorderLayout.CENTER);
			TablePanel.add(answersTableInfoLabel, BorderLayout.NORTH);
			TablePanel.add(answersTableLabelLeft, BorderLayout.EAST);
			TablePanel.add(answersTableLabelRigth, BorderLayout.WEST);
			TablePanel.add(getTableSouthPanel(), BorderLayout.SOUTH);
			TablePanel.setPreferredSize(new Dimension(500, 250));
			TablePanel.setMinimumSize(new Dimension(500, 250));
			TablePanel.setMaximumSize(new Dimension(500, 250));
			TablePanel.setBackground(new Color(221, 236, 251));
		}
		return TablePanel;
	}



	private JPanel getTableSouthPanel() {
		if (answersTableSouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			answersTableSouthPanel = new JPanel();
			answersTableSouthPanel.setLayout(flowLayout);
			answersTableSouthPanel.setPreferredSize(new Dimension(137, 53));
			answersTableSouthPanel.add(getAddButton(), null);
			answersTableSouthPanel.add(getDelButton(), null);
			answersTableSouthPanel.setBackground(new Color(221, 236, 251));
		}
		return answersTableSouthPanel;
	}

	private JButton getDelButton() {
		if (answersDelButton == null) {
			answersDelButton = new JButton();
			answersDelButton.setText("Delete Element");
			answersDelButton.addActionListener(this);
		}
		return answersDelButton;
	}

	private JButton getAddButton() {
		if (answersAddButton == null) {
			answersAddButton = new JButton();
			answersAddButton.setText("   Add Element  ");
			answersAddButton.addActionListener(this);
		}
		return answersAddButton;
	}

	private JScrollPane getTableScrollPane() {
		if (answersTableScrollPane == null) {
			answersTableScrollPane = new JScrollPane();
			answersTableScrollPane.setViewportView(getAnswersTable());
		}
		return answersTableScrollPane;
	}



	private JTable getAnswersTable() {
		if (answersTable == null) {
			answersTableModel = new AnswersTableModel();
			answersTable = new JTable(answersTableModel);
			answersTable.setRowHeight(20);
			answersTable.setBackground(Color.white);
			answersTable.setForeground(Color.black);
			answersTable.setGridColor(Color.black);
			answersTable.setFont(new Font("Dialog", Font.PLAIN, 12));
			answersTable.getModel().addTableModelListener(this);
			answersColumn = answersTable.getColumnModel().getColumn(0);
		    answersColumn.setPreferredWidth(45);
		    answersColumn.setResizable(false);
		    answersColumn.setMaxWidth(45);
		    answersColumn = answersTable.getColumnModel().getColumn(1);
		    answersColumn.setPreferredWidth(400);
		    answersColumn = answersTable.getColumnModel().getColumn(2);
		    answersColumn.setPreferredWidth(95);
		    answersColumn.setMaxWidth(95);
		    answersColumn.setResizable(false);
		}
		return answersTable;
	}

	@Override
	public void addAnswers(int count) {
	}
	@Override
	public void addAnswers(ArrayList<AnswerPanel> answers) {
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
		return "Make Pair Question";
	}
	@Override
	public void removeAnswerAt(int index) {
	}
	@Override
	public void saveToXML() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	   if(e.getSource() == answersAddButton)
		{
		   answersTableModel.insertRow(answersTable.getRowCount(),new Object[]  {++count,"", new Boolean(false) });
		}
		
		else if(e.getSource() == answersDelButton)
		{
			int selection = answersTable.getSelectedRow();
			if(selection != -1)
			{
				answersTableModel.removeRow(selection);
				int rowCount = answersTable.getRowCount();
				for(int i = selection ; i < rowCount ;i++)
				{
					answersTableModel.setValueAt(i + 1, i, 0);
				}
				count = rowCount;
				boxEditor = new MyComboBoxEditor(answers);
	        	pairTable.getColumnModel().getColumn(0).setCellEditor(boxEditor);
	        	pairTable.getColumnModel().getColumn(1).setCellEditor(boxEditor);
			}
		}
		else if(e.getSource() == pairDelButton)
		{
			int selection = pairTable.getSelectedRow();
			if(selection != -1)
				pairTableModel.removeRow(selection);
		}
		else if(e.getSource() == pairAddButton)
		{
			System.out.println(pairTableModel.pairDataVector.size());
			if(answers.size() != 0)
			  pairTableModel.insertRow(pairTable.getRowCount(),new Object[]  {"", "", new Boolean(false) , 0.0});
		}
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO tableChanged
	}
	
	
	// ANSWERS TABLE MODEL
	
	class AnswersTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"id.", "ELEMENTS", "FIX POSITION"};
		
		Vector<Vector> dataVector = new Vector<Vector>();


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return dataVector.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return dataVector.get(row).get(col);
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col == 0) {
                return false;
            } else {
                return true;
            }
        }

        @SuppressWarnings("unchecked")
		public void setValueAt(Object value, int row, int col) {
        	
        	if(col != 2 && col != 0)
        	{
        		String new_value = (String)value;
	        	tempValue = (String)dataVector.get(row).get(col);
	        	if(!(tempValue.trim().equals("")))
	        	{
	        		for(int i = 0; i < answers.size(); i++)
	        		{
	        			if((answers.get(i)).equals(tempValue))
	        			{
	        				if(new_value.trim().equals(""))
	        				{
	        					answers.remove(i);
	        					break;
	        				}
	        				else
	        				{
	        					answers.add(i, new_value);
	        					break;
	        				}
	        			}
	        		}
	        	}
	        	else
	        	{
	        		if(!(new_value.trim().equals("")))
	        			answers.add(new_value);
	        	}
	        	
        	}
        	boxEditor = new MyComboBoxEditor(answers);
        	pairTable.getColumnModel().getColumn(0).setCellEditor(boxEditor);
        	pairTable.getColumnModel().getColumn(1).setCellEditor(boxEditor);
            dataVector.get(row).set(col, value);
            fireTableCellUpdated(row, col);
        }

        public void insertRow(int row, Vector<Object> rowData)
        {
          dataVector.add(row, rowData);
          fireTableRowsInserted(row, row);
        }
        
        public void insertRow(int row, Object[] rowData)
        {
          insertRow(row, convertToVector(rowData));
        }
        
		public Vector<Object> convertToVector(Object[] data)
        {
          if (data == null)
            return null;
          Vector<Object> vector = new Vector<Object>(data.length);
          for (int i = 0; i < data.length; i++)
            vector.add(data[i]);
          return vector;
        }

		public void removeRow(int row)
		  {
			removed = (String)(dataVector.remove(row).get(1));
		    answers.remove(removed);
		    int index = 0;
		    while(index < pairTableModel.getRowCount())
		    {
		    	if(((String)pairTableModel.getValueAt(index, 0)).equals(removed) || ((String)pairTableModel.getValueAt(index, 1)).equals(removed))
		    	{
		    		pairTableModel.removeRow(index);
		    		index--;
		    	}
		    	index++;
		    }
		    fireTableRowsDeleted(row, row);
		  }
    }
	
	// PAIR TABLE MODEL
	
	class PairTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 2L;

		private String[] columnNames = {"ELEMENTS", "PAIR", "CORRECT", "SCORE"};
		
		Vector<Vector> pairDataVector = new Vector<Vector>();


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return pairDataVector.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return pairDataVector.get(row).get(col);
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
			return true;
        }

        @SuppressWarnings("unchecked")
		public void setValueAt(Object value, int row, int col) {
        	
            pairDataVector.get(row).set(col, value);
            fireTableCellUpdated(row, col);
        }

        public void insertRow(int row, Vector<Object> rowData)
        {
          pairDataVector.add(row, rowData);
          fireTableRowsInserted(row, row);
        }
        
        public void insertRow(int row, Object[] rowData)
        {
          insertRow(row, convertToVector(rowData));
        }
        
		public Vector<Object> convertToVector(Object[] data)
        {
          if (data == null)
            return null;
          Vector<Object> vector = new Vector<Object>(data.length);
          for (int i = 0; i < data.length; i++)
            vector.add(data[i]);
          return vector;
        }

		public void removeRow(int row)
		  {
			System.out.println("USUWAM: " +  row);
		    pairDataVector.remove(row);
		    fireTableRowsDeleted(row, row);
		  }
    }
	
	// Cell Editor
	public class MyComboBoxEditor extends DefaultCellEditor {
		
		private static final long serialVersionUID = 1L;

		public MyComboBoxEditor(ArrayList<String> items) {
            super(new JComboBox(items.toArray()));
        }
    }
	
	// Cell Renderer
	
	public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyComboBoxRenderer(ArrayList<String> items) {
            super(items.toArray());
        }
    
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
    
            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }




}
