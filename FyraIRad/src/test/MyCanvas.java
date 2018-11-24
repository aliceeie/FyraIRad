package test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {

	public void paint(Graphics g) {
		for(int i= 0; i<5; i++){
			g.setColor(Color.CYAN);
			g.fillRect(50*i, 10, 30, 30);
		}
		 
  }
}

