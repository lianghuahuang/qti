package pl.wiecek.qti.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AnswerAddPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel nrLabel;
	private JCheckBox shuffleBox;
	private JLabel shuffleLabel;
	private JButton addChoice;

	public AnswerAddPanel() {
		super();
		initialize();
	}

	private void initialize() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(5);
		nrLabel = new JLabel("Answer");
		nrLabel.setPreferredSize(new Dimension(500, 50));
		nrLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nrLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		nrLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		this.setLayout(flowLayout);
		this.setBackground(new Color(221, 236, 251));
		this.setMaximumSize(new Dimension(1680,55));
		this.setMinimumSize(new Dimension(800,55));
		this.add(nrLabel, null);
		shuffleBox = new JCheckBox();
		shuffleBox.setBackground(new Color(221, 236, 251));
		this.add(shuffleBox, null);
		shuffleLabel = new JLabel("Shuffle");
		shuffleLabel.setPreferredSize(new Dimension(50, 50));
		shuffleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		shuffleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(shuffleLabel, null);
	    addChoice = new JButton("Add choice");
		this.add(addChoice, null);
		this.setLocation(0, 0);
	}
	public void setListener(ActionListener listener)
	{
		addChoice.addActionListener(listener);
	}
}
