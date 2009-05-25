package pl.qti.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import pl.qti.editor.exceptions.XmlSaveException;
import pl.qti.editor.question.factory.OrderedChoiceFactory;
import pl.qti.editor.questions.SimpleAnswer;

public class OrderQuestion extends AbstractQuestionPanel implements ActionListener, TableModelListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel CenterPanel;
	private JPanel OrderPanel;
	private JPanel TablePanel;
	private JList orderList = null;
	private JLabel orderLabelLeft = null;
	private JLabel orderLabelRigth = null;
	private DefaultListModel listModel =  new DefaultListModel();
	private String temp;
	private JPanel orderSouthPanel = null;
	private JButton UpButton = null;
	private JButton DownButton = null;
	private JLabel orderInfoLabel = null;
	private JScrollPane listScrollPane;
	private JScrollPane tableScrollPane = null;
	private JScrollPane jScrollPane = null;
	private JTable answersTable = null;
	private JLabel tableInfoLabel = null;
	private JLabel tableLabelLeft = null;
	private JLabel tableLabelRigth = null;
	private JButton addButton, delButton;
	private MyTableModel tableModel;
	private TableColumn column ;
	private JPanel tableSouthPanel;
	private int count;
	private int position;
	private String tempValue;
	
	
	
	public OrderQuestion(QTIEditor editor) {
		super(editor);
		super.add(getJScrollPane(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes MainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	
	
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
			MainPanel.add(getCenterPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
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
			//this.setBorder(BorderFactory.createTitledBorder(null, "ANSWERS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.black));
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

	private Component getCenterPanel() {
		if (CenterPanel == null) {
			CenterPanel = new JPanel();
			CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.Y_AXIS));
			CenterPanel.setBackground(new Color(221, 236, 251));
			CenterPanel.add(getCorrectOrderPanel());
		}
		return CenterPanel;
	}
	
	private JPanel getCorrectOrderPanel()
	{
		if(OrderPanel == null)
		{
			OrderPanel = new JPanel();
			orderInfoLabel = new JLabel();
			orderInfoLabel.setText("Choose correct order");
			orderInfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			orderInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			orderInfoLabel.setPreferredSize(new Dimension(129, 35));
			orderInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			orderLabelRigth = new JLabel();
			orderLabelRigth.setText("            ");
			orderLabelLeft = new JLabel();
			orderLabelLeft.setText("            ");
			OrderPanel.setSize(551, 290);
			OrderPanel.setLayout(new BorderLayout());
			OrderPanel.add(getOrderJScrollPane(), BorderLayout.CENTER);
			OrderPanel.add(orderLabelLeft, BorderLayout.EAST);
			OrderPanel.add(orderLabelRigth, BorderLayout.WEST);
			OrderPanel.add(getOrderSouthPanel(), BorderLayout.SOUTH);
			OrderPanel.add(orderInfoLabel, BorderLayout.NORTH);
			OrderPanel.setBackground(new Color(221, 236, 251));
			//this.setBorder(BorderFactory.createTitledBorder(null, "CORRECT ORDERING", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.black));
			OrderPanel.setMaximumSize(new Dimension(1680,250));
			OrderPanel.setMinimumSize(new Dimension(800,250));
			OrderPanel.setPreferredSize(new Dimension(700,250));
		}
		return OrderPanel;
	}
	
	private JPanel getOrderSouthPanel() {
		if (orderSouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			orderSouthPanel = new JPanel();
			orderSouthPanel.setLayout(flowLayout);
			orderSouthPanel.setPreferredSize(new Dimension(137, 53));
			orderSouthPanel.add(getUpButton(), null);
			orderSouthPanel.add(getDownButton(), null);
			orderSouthPanel.setBackground(new Color(221, 236, 251));
		}
		return orderSouthPanel;
	}

	private JButton getDownButton() {
		if (DownButton == null) {
			DownButton = new JButton();
			DownButton.setText("DOWN");
			DownButton.addActionListener(this);
		}
		return DownButton;
	}

	private JButton getUpButton() {
		if (UpButton == null) {
			UpButton = new JButton();
			UpButton.setText("   UP   ");
			UpButton.addActionListener(this);
		}
		return UpButton;
	}

	private JScrollPane getOrderJScrollPane() {
		listScrollPane = new JScrollPane(getOrderJList());
		 listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 listScrollPane.setPreferredSize(new Dimension(500, 200));
		 listScrollPane.setMinimumSize(new Dimension(500, 198));
		 listScrollPane.setMaximumSize(new Dimension(500, 203));
		 return listScrollPane;
	}

	private JList getOrderJList() {
		if (orderList == null) {
			orderList = new JList(listModel);
			orderList.setFont(new Font("Dialog", Font.BOLD, 12));
			orderList.setForeground(SystemColor.activeCaption);
			orderList.setBackground(SystemColor.control);
			orderList.setAutoscrolls(true);
			//orderList.addMouseListener(this);
		}
		return orderList;
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
		return 0;
	}
	@Override
	public String getQuestionType() {
		return "Correcr Order Question";
	}
	@Override
	public void removeAnswerAt(int index) {
	}
	@Override
	public void saveToXML(String path) throws XmlSaveException {
		OrderedChoiceFactory.saveQuestion(getAnswersClass(), getQuestionName(), getQuestionText(), "false", path);
	}
	@Override
	public void addAnswers(ArrayList<AnswerPanel> answers) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == UpButton)
		{
			if((orderList.getSelectedIndex() == 0) | (orderList.getSelectedIndex() == -1))
				return;
			else
				changePosition(-1);
		}
		else if(source == DownButton)
		{
			if((orderList.getSelectedIndex() == listModel.getSize() - 1) | (orderList.getSelectedIndex() == -1))
				return;
			else
				changePosition(1);
		}

		else if(e.getSource() == addButton)
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
	
	public void changePosition(int up_down)
	{
		position = orderList.getSelectedIndex();
		temp = (String)listModel.getElementAt(position);
		listModel.set(position, listModel.getElementAt(position + up_down));
		listModel.setElementAt(temp, position + up_down);
		orderList.setSelectedIndex(position + up_down);
	}
	
	// -------------- TABLE MODEL --------------------------
	
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
	        	tempValue = (String)dataVector.get(row).get(col);
	        	
	        	if(!(tempValue.trim().equals("")))
	        	{
	        		for(int i = 0; i < listModel.size(); i++)
	        		{
	        			if(((String)listModel.get(i)).equals(tempValue))
	        			{
	        				if(((String)value).trim().equals(""))
	        					listModel.remove(i);
	        				else
	        					listModel.set(i, value);
	        			}
	        		}
	        	}
	        	else
	        	{
	        		if(!((String)value).trim().equals(""))
	        			listModel.addElement(value);
	        	}
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
			listModel.removeElement(tableModel.getValueAt(row, 1));
		    dataVector.remove(row);
		    fireTableRowsDeleted(row, row);
		  }

    }

	@Override
	public void tableChanged(TableModelEvent e) {

		if(e.getType() == TableModelEvent.UPDATE)
		{
			//System.out.println("UPDATE");
		}
		
		if(e.getType() == TableModelEvent.DELETE)
		{
			//System.out.println("DELETE");
		}
		
	}
	
	public void setAnswers(ArrayList<SimpleAnswer> list, ArrayList<Integer> orderInserted)
	{
		int row = 0;
		for(SimpleAnswer answer : list)
		{
			tableModel.insertRow(row, new Object[]  {row + 1,answer.getValue(), answer.isFixed() });
			row++;
		}
		
		for(Integer order : orderInserted)
		{
			listModel.addElement(list.get(order).getValue());
		}
		count = row;
	}
	
	public ArrayList<OrderAnswer> getAnswersClass()
	{
		ArrayList<OrderAnswer> list = new ArrayList<OrderAnswer>();
		OrderAnswer answer;
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			String content = (String)tableModel.getValueAt(i, 1);
			answer = new OrderAnswer();
			answer.setContent(content);
			answer.setFixed((Boolean)tableModel.getValueAt(i, 2));

			for(int j = 0; j < listModel.size(); j++)
			{
				if(((String)listModel.getElementAt(j)).equals(content))
				{
					answer.setPosition(j);
					break;
				}
			}
			list.add(answer);
		}
		return list;
	}

}
