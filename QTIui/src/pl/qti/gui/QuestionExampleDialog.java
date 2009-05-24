package pl.qti.gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import pl.qti.utils.ComboBoxValues;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionExampleDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel imageLabel = null;
	private JLabel infoLabel = null;
	private int h;
	private int w;

	/**
	 * @param owner
	 */
	public QuestionExampleDialog(Frame owner, String questionType) {
		super(owner, questionType);
		initialize(questionType);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(String questionType) {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane(questionType));
		this.setSize(h, w + 70);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane(String questionType) {
		if (jContentPane == null) {
			infoLabel = new JLabel();
			infoLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			infoLabel.setText("  This question will look like this:");
			imageLabel = new JLabel();
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(ComboBoxValues.getImageAlias(questionType))));
			//ImageIcon icon = new ImageIcon(getClass().getResource(ComboBoxValues.getImageAlias(questionType)));
			w = icon.getIconHeight();
			h = icon.getIconWidth();
			imageLabel.setIcon(icon);
			imageLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(imageLabel, BorderLayout.CENTER);
			jContentPane.add(infoLabel, BorderLayout.NORTH);
		}
		return jContentPane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
		//System.exit(1);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
