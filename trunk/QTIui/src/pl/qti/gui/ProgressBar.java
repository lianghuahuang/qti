package pl.qti.gui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JProgressBar;
import java.awt.Font;

public class ProgressBar extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JLabel infoLabel = null;
	private JProgressBar jProgressBar = null;

	/**
	 * @param owner
	 */
	public ProgressBar(Frame owner) {
		super(owner, "Saving ...");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(340, 138);
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
			infoLabel.setBounds(new Rectangle(109, 15, 126, 20));
			infoLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			infoLabel.setText("Saving question");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(266, 65));
			jContentPane.add(infoLabel, null);
			jContentPane.add(getJProgressBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(35, 49, 258, 39));
			jProgressBar.setString("saving in progress...");
			jProgressBar.setStringPainted(true);
		}
		return jProgressBar;
	}

}  //  @jve:decl-index=0:visual-constraint="33,85"
