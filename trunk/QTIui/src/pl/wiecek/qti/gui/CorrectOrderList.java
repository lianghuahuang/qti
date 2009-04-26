package pl.wiecek.qti.gui;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class CorrectOrderList extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JList jList = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private DefaultListModel model =  new DefaultListModel();
	private String temp;
	private int position;
	private JPanel SouthPanel = null;
	private JButton UpButton = null;
	private JButton DownButton = null;
	private JLabel InfoLabel = null;

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
		InfoLabel = new JLabel();
		InfoLabel.setText("Choose correct order");
		InfoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		InfoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		InfoLabel.setPreferredSize(new Dimension(129, 35));
		InfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1 = new JLabel();
		jLabel1.setText("            ");
		jLabel = new JLabel();
		jLabel.setText("            ");
		this.setSize(551, 290);
		this.setLayout(new BorderLayout());
		this.add(getJList(), BorderLayout.CENTER);
		this.add(jLabel, BorderLayout.EAST);
		this.add(jLabel1, BorderLayout.WEST);
		//model = (DefaultListModel)jList.getModel();
		this.add(getSouthPanel(), BorderLayout.SOUTH);
		this.add(InfoLabel, BorderLayout.NORTH);
		this.setBackground(new Color(221, 236, 251));
		this.setBorder(BorderFactory.createTitledBorder(null, "ORDER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(0, 70, 213)));
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList(model);
			//DefaultListModel  model2 = (DefaultListModel) jList.getModel();
			model.addElement("SUCHY");
			model.addElement("PIES");
			model.addElement("BUNDA");
			
			jList.setFont(new Font("Dialog", Font.BOLD, 12));
			jList.setForeground(Color.red);
			jList.setBackground(SystemColor.info);
			jList.setAutoscrolls(true);
			jList.addMouseListener(this);
		}
		return jList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == UpButton)
		{
			if((jList.getSelectedIndex() == 0) | (jList.getSelectedIndex() == -1))
				return;
			else
			{
				System.out.println("UP");
				changePosition(-1);
			}
		}
		else if(source == DownButton)
		{
			if((jList.getSelectedIndex() == model.getSize() - 1) | (jList.getSelectedIndex() == -1))
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
		position = jList.getSelectedIndex();
		temp = (String)model.getElementAt(position);
		model.set(position, model.getElementAt(position + up_down));
		model.setElementAt(temp, position + up_down);
		jList.setSelectedIndex(position + up_down);
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
		if (SouthPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(24);
			flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout.setVgap(12);
			SouthPanel = new JPanel();
			SouthPanel.setLayout(flowLayout);
			SouthPanel.setPreferredSize(new Dimension(137, 53));
			SouthPanel.add(getUpButton(), null);
			SouthPanel.add(getDownButton(), null);
			SouthPanel.setBackground(new Color(221, 236, 251));
		}
		return SouthPanel;
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
