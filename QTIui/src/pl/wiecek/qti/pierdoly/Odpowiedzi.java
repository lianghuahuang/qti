package pl.wiecek.qti.pierdoly;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import java.awt.Color;

public class Odpowiedzi extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private JRadioButton jRadioButton = null;
	private JLabel jLabel1 = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	/**
	 * This is the default constructor
	 */
	public Odpowiedzi() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
		flowLayout.setVgap(9);
		flowLayout.setHgap(15);
		jLabel1 = new JLabel();
		jLabel1.setText("JLabel");
		jLabel = new JLabel();
		jLabel.setText("JLabel");
		this.setLayout(flowLayout);
		this.setSize(670, 43);
		this.setBackground(new Color(102, 102, 255));
		this.add(getJButton(), null);
		this.add(jLabel, null);
		this.add(getJRadioButton(), null);
		this.add(jLabel1, null);
		this.add(getJButton1(), null);
		this.add(getJButton2(), null);
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Button");
		}
		return jButton;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Add Question");
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Example");
		}
		return jButton2;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
