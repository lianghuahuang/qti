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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;


public class MakePairQuestion extends AbstractQuestionPanel implements ActionListener, TableModelListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel TablePanel;
	private JTable answersTable;
	private JLabel tableInfoLabel;
	private JLabel tableLabelLeft;
	private JLabel tableLabelRigth;
	private JButton addButton, delButton;
	private JScrollPane tableScrollPane;
	private JPanel tableSouthPanel;
	private TableColumn column;
	private MyTableModel tableModel;
 	private JPanel CenterPanel;
 	private int count;

	
	public MakePairQuestion(QTIEditor editor)
	{
		super(editor);
		super.add(getMainPanel(), BorderLayout.CENTER);
	}
	
	private JPanel getMainPanel() {
		if (MainPanel == null) {
			MainPanel = new JPanel();
			MainPanel.setLayout(new BorderLayout());
			MainPanel.add(getTableOfAnswers(), BorderLayout.NORTH);
			MainPanel.add(getCenterPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
	}



	private JPanel getCenterPanel() {
		if(CenterPanel == null)
		{
			CenterPanel = new JPanel();
		}
		return CenterPanel;
	}



	private JPanel getTableOfAnswers() {
		if(TablePanel == null)
		{
			TablePanel = new JPanel();
			tableLabelRigth = new JLabel(); 
			tableLabelRigth.setText("             ");
			tableLabelLeft = new JLabel();
			tableLabelLeft.setText("             ");
			tableInfoLabel = new JLabel();
			tableInfoLabel.setText("Define all the answers in the following table");
			tableInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			tableInfoLabel.setPreferredSize(new Dimension(208, 34));
			tableInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			TablePanel.setSize(531, 251);
			TablePanel.setLayout(new BorderLayout());
			TablePanel.add(getTableScrollPane(), BorderLayout.CENTER);
			TablePanel.add(tableInfoLabel, BorderLayout.NORTH);
			TablePanel.add(tableLabelLeft, BorderLayout.EAST);
			TablePanel.add(tableLabelRigth, BorderLayout.WEST);
			TablePanel.add(getTableSouthPanel(), BorderLayout.SOUTH);
			TablePanel.setPreferredSize(new Dimension(500, 250));
			TablePanel.setMinimumSize(new Dimension(500, 250));
			TablePanel.setMaximumSize(new Dimension(500, 250));
			TablePanel.setBackground(new Color(221, 236, 251));
		}
		return TablePanel;
	}



	private JPanel getTableSouthPanel() {
		if (tableSouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			tableSouthPanel = new JPanel();
			tableSouthPanel.setLayout(flowLayout);
			tableSouthPanel.setPreferredSize(new Dimension(137, 53));
			tableSouthPanel.add(getAddButton(), null);
			tableSouthPanel.add(getDelButton(), null);
			tableSouthPanel.setBackground(new Color(221, 236, 251));
		}
		return tableSouthPanel;
	}

	private JButton getDelButton() {
		if (delButton == null) {
			delButton = new JButton();
			delButton.setText("Delete Element");
			delButton.addActionListener(this);
		}
		return delButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("   Add Element  ");
			addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane getTableScrollPane() {
		if (tableScrollPane == null) {
			tableScrollPane = new JScrollPane();
			tableScrollPane.setViewportView(getAnswersTable());
		}
		return tableScrollPane;
	}



	private JTable getAnswersTable() {
		if (answersTable == null) {
			tableModel = new MyTableModel();
			answersTable = new JTable(tableModel);
			answersTable.setRowHeight(20);
			answersTable.setBackground(Color.white);
			answersTable.setForeground(Color.black);
			answersTable.setGridColor(Color.black);
			answersTable.setFont(new Font("Dialog", Font.PLAIN, 12));
			answersTable.getModel().addTableModelListener(this);
		    column = answersTable.getColumnModel().getColumn(0);
		    column.setPreferredWidth(45);
		    column.setResizable(false);
		    column.setMaxWidth(45);
		    column = answersTable.getColumnModel().getColumn(1);
		    column.setPreferredWidth(400);
		    column = answersTable.getColumnModel().getColumn(2);
		    column.setPreferredWidth(95);
		    column.setMaxWidth(95);
		    column.setResizable(false);
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
	   if(e.getSource() == addButton)
		{
		    tableModel.insertRow(answersTable.getRowCount(),new Object[]  {++count,"", new Boolean(false) });
		}
		
		else if(e.getSource() == delButton)
		{
			int selection = answersTable.getSelectedRow();
			if(selection != -1)
			{
				tableModel.removeRow(selection);
				int rowCount = answersTable.getRowCount();
				for(int i = selection ; i < rowCount ;i++)
				{
					tableModel.setValueAt(i + 1, i, 0);
				}
				count = rowCount;
			}
		}
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Table Model
	class MyTableModel extends AbstractTableModel {
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
//	        	tempValue = (String)dataVector.get(row).get(col);
//	        	
//	        	if(!(tempValue.trim().equals("")))
//	        	{
//	        		for(int i = 0; i < listModel.size(); i++)
//	        		{
//	        			if(((String)listModel.get(i)).equals(tempValue))
//	        			{
//	        				if(((String)value).trim().equals(""))
//	        					listModel.remove(i);
//	        				else
//	        					listModel.set(i, value);
//	        			}
//	        		}
//	        	}
//	        	else
//	        	{
//	        		if(!((String)value).trim().equals(""))
//	        			//listModel.addElement(value);
//	        	}
        	}
        	
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
			//listModel.removeElement(tableModel.getValueAt(row, 1));
		    dataVector.remove(row);
		    fireTableRowsDeleted(row, row);
		  }

    }


}
