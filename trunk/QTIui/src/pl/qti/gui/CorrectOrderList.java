package pl.qti.gui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class CorrectOrderList extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JList orderList = null;
	private JLabel orderLabelLeft = null;
	private JLabel orderLabelRigth = null;
	private DefaultListModel listModel =  new DefaultListModel();
	private String temp;
	private int position;
	private JPanel orderSouthPanel = null;
	private JButton UpButton = null;
	private JButton DownButton = null;
	private JLabel orderInfoLabel = null;
	private JScrollPane listScrollPane;

	/**
	 * This is the default constructor
	 */
	public CorrectOrderList() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
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
		this.setSize(551, 290);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(orderLabelLeft, BorderLayout.EAST);
		this.add(orderLabelRigth, BorderLayout.WEST);
		this.add(getSouthPanel(), BorderLayout.SOUTH);
		this.add(orderInfoLabel, BorderLayout.NORTH);
		this.setBackground(new Color(221, 236, 251));
		//this.setBorder(BorderFactory.createTitledBorder(null, "CORRECT ORDERING", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), Color.black));
		this.setMaximumSize(new Dimension(1680,250));
		this.setMinimumSize(new Dimension(800,250));
		this.setPreferredSize(new Dimension(700,250));
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (orderList == null) {
			orderList = new JList(listModel);
			//DefaultListModel  model2 = (DefaultListModel) jList.getModel();
			listModel.addElement("Test 1");
			listModel.addElement("Test 2");
			listModel.addElement("Test 3");
			
			orderList.setFont(new Font("Dialog", Font.BOLD, 12));
			orderList.setForeground(SystemColor.activeCaption);
			orderList.setBackground(SystemColor.control);
			orderList.setAutoscrolls(true);
			orderList.addMouseListener(this);
		}
		return orderList;
	}
	
	private JScrollPane getJScrollPane() {
		 listScrollPane = new JScrollPane(getJList());
		 listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 listScrollPane.setPreferredSize(new Dimension(500, 200));
		 listScrollPane.setMinimumSize(new Dimension(500, 198));
		 listScrollPane.setMaximumSize(new Dimension(500, 203));
		 return listScrollPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == UpButton)
		{
			if((orderList.getSelectedIndex() == 0) | (orderList.getSelectedIndex() == -1))
				return;
			else
			{
				System.out.println("UP");
				changePosition(-1);
			}
		}
		else if(source == DownButton)
		{
			if((orderList.getSelectedIndex() == listModel.getSize() - 1) | (orderList.getSelectedIndex() == -1))
				return;
			else
			{
				System.out.println("DOWN");
				changePosition(1);
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes SouthPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSouthPanel() {
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

	/**
	 * This method initializes UpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUpButton() {
		if (UpButton == null) {
			UpButton = new JButton();
			UpButton.setText("   UP ");
			UpButton.addActionListener(this);
		}
		return UpButton;
	}

	/**
	 * This method initializes DownButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDownButton() {
		if (DownButton == null) {
			DownButton = new JButton();
			DownButton.setText("DOWN");
			DownButton.addActionListener(this);
		}
		return DownButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
