package pl.qti.gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;



public class TableOfAnswers extends JPanel implements TableModelListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JScrollPane tableScrollPane = null;
	private JTable answersTable = null;
	private JLabel tableInfoLabel = null;
	private JLabel tableLabelLeft = null;
	private JLabel tableLabelRigth = null;
	private JButton addButton, delButton;
	private MyTableModel tableModel;
	private TableColumn column ;
	private JPanel tableSouthPanel;
	private int count;
	

	/**
	 * This is the default constructor
	 */
	public TableOfAnswers() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		tableLabelRigth = new JLabel(); 
		tableLabelRigth.setText("             ");
		tableLabelLeft = new JLabel();
		tableLabelLeft.setText("             ");
		tableInfoLabel = new JLabel();
		tableInfoLabel.setText("Define all the answers in the following table");
		tableInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		tableInfoLabel.setPreferredSize(new Dimension(208, 34));
		tableInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setSize(531, 251);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(tableInfoLabel, BorderLayout.NORTH);
		this.add(tableLabelLeft, BorderLayout.EAST);
		this.add(tableLabelRigth, BorderLayout.WEST);
		this.add(getSouthPanel(), BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(500, 250));
		//this.setBorder(BorderFactory.createTitledBorder(null, "ANSWERS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.black));
		this.setMinimumSize(new Dimension(500, 250));
		this.setMaximumSize(new Dimension(500, 250));
		this.setBackground(new Color(221, 236, 251));
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (tableScrollPane == null) {
			tableScrollPane = new JScrollPane();
			tableScrollPane.setViewportView(getJTable());
		}
		return tableScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
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
		    column.setPreferredWidth(5);
		    column.setResizable(false);
		    column = answersTable.getColumnModel().getColumn(1);
		    column.setPreferredWidth(400);
		    column = answersTable.getColumnModel().getColumn(2);
		    column.setPreferredWidth(26);
		    column.setResizable(false);
		}
		return answersTable;
	}

	/**
	 * This method initializes SouthPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSouthPanel() {
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
			if (addButton == null) {
				addButton = new JButton();
				addButton.setText("   Add Element  ");
				addButton.addActionListener(this);
			}
			return addButton;
	}

	private JButton getAddButton() {
		if (delButton == null) {
			delButton = new JButton();
			delButton.setText("Delete Element");
			delButton.addActionListener(this);
		}
		return delButton;
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

        public void setValueAt(Object value, int row, int col) {
            dataVector.get(row).set(col, value);
            if(col !=2)
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
		    dataVector.remove(row);
		    fireTableRowsDeleted(row, row);
		  }

    }

	@Override
	public void tableChanged(TableModelEvent e) {

		if(e.getType() == TableModelEvent.UPDATE)
			System.out.println("UPDATE");
		
		if(e.getType() == TableModelEvent.DELETE)
			System.out.println("DELETE");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == addButton)
		{
		    tableModel.insertRow(answersTable.getRowCount(),new Object[]  {++count,"", new Boolean(false) });
		}
		else
		{
			if(answersTable.getSelectedRow() != -1)
			{
				tableModel.removeRow(answersTable.getSelectedRow());
			}
		}
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
