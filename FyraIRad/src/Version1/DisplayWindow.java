package Version1;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;

public class DisplayWindow {
	
	  public void show(Graphics g) {
	    JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(300, 100, 730, 660);
		window.getContentPane().add(new GameBoard());
//		window.getContentPane().add(new Tile());
	    window.setVisible(true);
	  }
	}
