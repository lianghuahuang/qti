package pl.wiecek.qti.gui;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;

public class QuestionExampleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel imageLabel = null;
	private JButton okButton = null;
	private JLabel infoLabel = null;

	/**
	 * @param owner
	 */
	public QuestionExampleDialog(Frame owner, String tittle) {
		super(owner, tittle);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(636, 410);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			infoLabel = new JLabel();
			infoLabel.setBounds(new Rectangle(15, 9, 329, 30));
			infoLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			infoLabel.setText("This question will look like this:");
			imageLabel = new JLabel();
			imageLabel.setBounds(new Rectangle(11, 46, 610, 273));
			imageLabel.setIcon(new ImageIcon(getClass().getResource("/icons/MultipleChoice_item.jpg")));
			imageLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(imageLabel, null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(infoLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setBounds(new Rectangle(264, 336, 90, 29));
			okButton.setText("OK");
		}
		return okButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
