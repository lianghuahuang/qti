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
import java.awt.SystemColor;
import java.awt.Color;

public class ProgressBar extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private JProgressBar jProgressBar = null;

	/**
	 * @param owner
	 */
	public ProgressBar(Frame owner) {
		super(owner, "Saving in progress...");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(371, 80);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(266, 65));
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
			jProgressBar.setBounds(new Rectangle(21, 14, 321, 25));
			jProgressBar.setString("saving in progress...");
			jProgressBar.setBackground(SystemColor.activeCaption);
			jProgressBar.setForeground(Color.red);
			jProgressBar.setStringPainted(true);
			jProgressBar.setIndeterminate(true);

		}
		return jProgressBar;
	}

}  //  @jve:decl-index=0:visual-constraint="33,115"
