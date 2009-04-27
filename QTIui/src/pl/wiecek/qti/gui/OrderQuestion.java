package pl.wiecek.qti.gui;

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

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class OrderQuestion extends AbstractQuestionPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel MainPanel;
	private JPanel CenterPanel;
	private JPanel OrderPanel;
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
	
	
	public OrderQuestion(QTIEditor editor) {
		super(editor);
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
			MainPanel.add(new TableOfAnswers(), BorderLayout.NORTH);
			MainPanel.add(getCenterPanel(), BorderLayout.CENTER);
			MainPanel.setBackground(new Color(221, 236, 251));
		}
		return MainPanel;
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
	//----------------------------------------------------------
	
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
			//DefaultListModel  model2 = (DefaultListModel) jList.getModel();
			listModel.addElement("Test 1");
			listModel.addElement("Test 2");
			listModel.addElement("Test 3");
			
			orderList.setFont(new Font("Dialog", Font.BOLD, 12));
			orderList.setForeground(SystemColor.activeCaption);
			orderList.setBackground(SystemColor.control);
			orderList.setAutoscrolls(true);
			//orderList.addMouseListener(this);
		}
		return orderList;
	}

	//----------------------------------------------------------
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
	public void saveToXML() {
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
	}
	
	public void changePosition(int up_down)
	{
		position = orderList.getSelectedIndex();
		temp = (String)listModel.getElementAt(position);
		listModel.set(position, listModel.getElementAt(position + up_down));
		listModel.setElementAt(temp, position + up_down);
		orderList.setSelectedIndex(position + up_down);
	}

}
