package test;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

class MyCanvas extends JComponent {

	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(50, 10, 30, 30);
	}
}

