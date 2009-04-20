package pl.wiecek.qti.pierdoly;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBarStepIndeterminate {
	public static void main(String args[]) throws InterruptedException {
	    JFrame frame = new JFrame("Stepping Progress");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    final JProgressBar aJProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
	    aJProgressBar.setStringPainted(true);
	    aJProgressBar.setString("PIES");
	    aJProgressBar.setIndeterminate(true);
	    Thread.sleep(200);

	    aJProgressBar.setIndeterminate(false);
	    aJProgressBar.setString("CHUJ");
	    frame.add(aJProgressBar, BorderLayout.NORTH);
	    frame.setSize(200, 200);
	    frame.setVisible(true);
	  }

}
