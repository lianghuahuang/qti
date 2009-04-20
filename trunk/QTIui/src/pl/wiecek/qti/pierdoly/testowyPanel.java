package pl.wiecek.qti.pierdoly;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class testowyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JTextArea jTextArea = null;
	/**
	 * This is the default constructor
	 */
	public testowyPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.insets = new Insets(0, 0, 110, 0);
		gridBagConstraints1.gridy = 0;
		gridBagConstraints1.ipadx = 592;
		gridBagConstraints1.gridx = 0;
		this.setLayout(new GridBagLayout());
		this.setSize(700, 100);
		this.add(getJPanel(), gridBagConstraints1);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 7;
			gridBagConstraints2.weightx = 3.0;
			gridBagConstraints2.weighty = 3.0;
			gridBagConstraints2.insets = new Insets(6, 28, 0, 45);
			gridBagConstraints2.gridwidth = 5;
			gridBagConstraints2.gridheight = 18;
			gridBagConstraints2.ipadx = 4;
			gridBagConstraints2.ipady = 152;
			gridBagConstraints2.gridx = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.insets = new Insets(9, 3, 0, 0);
			gridBagConstraints.gridwidth = 5;
			gridBagConstraints.gridheight = 7;
			gridBagConstraints.ipadx = 7;
			gridBagConstraints.ipady = 3;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Question");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, gridBagConstraints);
			jPanel.add(getJTextArea(), gridBagConstraints2);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

}  //  @jve:decl-index=0:visual-constraint="10,134"
