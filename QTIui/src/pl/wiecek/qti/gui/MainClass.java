package pl.wiecek.qti.gui;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends Frame {
  FileDialog fc;

  MainClass() {
    super("MainClass");
    setSize(200, 200);

    fc = new FileDialog(this, "Choose a file", FileDialog.LOAD);
    fc.setDirectory("C:\\");

    Button b;
    add(b = new Button("Browse...")); // Create and add a Button
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fc.setVisible(true);
        String fn = fc.getFile();
        if (fn == null)
          System.out.println("You cancelled the choice");
        else
          System.out.println("You chose " + fn);
      }
    });
  }

  public static void main(String[] a) {
    new MainClass().setVisible(true);
  }
}
