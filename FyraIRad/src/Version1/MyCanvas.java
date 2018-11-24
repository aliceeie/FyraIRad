package Version1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

class MyCanvas extends JComponent {

	public void paint(Graphics g) {
		
		
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);
		
		Circle[][] arrayOfCircles = new Circle[7][6];
		
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y] = new Circle(Color.white,20 + 100*x, 20 + 100*y, 80);
				arrayOfCircles[x][y].paintCircle(g);
			}
		}
		
		
  }
}

