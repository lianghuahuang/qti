package pl.wiecek.qti.gui;

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



public class TableTest extends JPanel implements TableModelListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton addButton, delButton;
	private MyTableModel model;
	private TableColumn column ;
	private JPanel SouthPanel;
	private int count;
	

	/**
	 * This is the default constructor
	 */
	public TableTest() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel2 = new JLabel();
		jLabel2.setText("JLabel");
		jLabel1 = new JLabel();
		jLabel1.setText("JLabel");
		jLabel = new JLabel();
		jLabel.setText("ANSWERS");
		jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setSize(531, 251);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(jLabel, BorderLayout.NORTH);
		this.add(jLabel1, BorderLayout.EAST);
		this.add(jLabel2, BorderLayout.WEST);
		this.add(getSouthPanel(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			model = new MyTableModel();
			jTable = new JTable(model);
			jTable.setRowHeight(30);
			jTable.setBackground(new Color(204, 255, 204));
			jTable.setForeground(Color.black);
			jTable.setGridColor(new Color(255, 0, 51));
			jTable.setFont(new Font("Dialog", Font.PLAIN, 14));
			jTable.getModel().addTableModelListener(this);
		    column = jTable.getColumnModel().getColumn(0);
		    column.setPreferredWidth(5);
		    column = jTable.getColumnModel().getColumn(1);
		    column.setPreferredWidth(400);
		    column = jTable.getColumnModel().getColumn(2);
		    column.setPreferredWidth(26);
		}
		return jTable;
	}

	/**
	 * This method initializes SouthPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSouthPanel() {
		if (SouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			SouthPanel = new JPanel();
			SouthPanel.setLayout(flowLayout);
			SouthPanel.setPreferredSize(new Dimension(137, 53));
			SouthPanel.add(getAddButton(), null);
			SouthPanel.add(getDelButton(), null);
			SouthPanel.setBackground(new Color(221, 236, 251));
		}
		return SouthPanel;
	}
	
	private JButton getDelButton() {
			if (addButton == null) {
				addButton = new JButton();
				addButton.setText("   ADD ");
				addButton.addActionListener(this);
			}
			return addButton;
	}

	private JButton getAddButton() {
		if (delButton == null) {
			delButton = new JButton();
			delButton.setText("   DEL ");
			delButton.addActionListener(this);
		}
		return delButton;
	}

	// Table Model
	class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Nr", "ELEMENT", "FIX POSITION"};
		
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
		    model.insertRow(jTable.getRowCount(),new Object[]  {++count,"", new Boolean(false) });
		}
		else
		{
			if(jTable.getSelectedRow() != -1)
			{
				model.removeRow(jTable.getSelectedRow());
			}
		}
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
