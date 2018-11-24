package Version1;

import javax.swing.JFrame;

public class DisplayWindow {
	  public static void main(String[] a) {
	    JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(10, 10, 720, 660);
	    window.getContentPane().add(new Spelplan());
	    window.setVisible(true);
	  }
	}
