package Version1;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
	
	private Color color;
	private int radius = 0;
	private int x = 0;
	private int y = 0;
	
	public Circle(Color color, int x, int y, int radius){
		this.color = color;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public void paintCircle(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}

}
