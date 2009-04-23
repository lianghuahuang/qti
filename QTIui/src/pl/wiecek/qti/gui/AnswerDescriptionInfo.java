package pl.wiecek.qti.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AnswerDescriptionInfo extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel choiceText;
	private JLabel correct;
	private JLabel score;

	public AnswerDescriptionInfo() {
		super();
		initialize();
	}

	private void initialize() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(5);
		choiceText = new JLabel("Choice text");
		
		choiceText.setPreferredSize(new Dimension(395, 20));
		choiceText.setHorizontalAlignment(SwingConstants.LEFT);
		choiceText.setHorizontalTextPosition(SwingConstants.LEFT);
		this.setLayout(flowLayout);
		this.setBackground(new Color(221, 236, 251));
		this.setMaximumSize(new Dimension(1680,25));
		this.setMinimumSize(new Dimension(800,25));
		this.add(choiceText, null);
		correct = new JLabel("Choice");
		correct.setPreferredSize(new Dimension(33, 20));
		this.add(correct, null);
		score = new JLabel("Score");
		score.setPreferredSize(new Dimension(191, 20));
		this.add(score, null);
		this.setLocation(0, 55);
	}
}
