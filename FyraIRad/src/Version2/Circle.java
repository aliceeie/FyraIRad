package Version2;

import java.awt.Graphics;
import java.awt.Color;

public class Circle implements Drawable {
	
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
	
	@Override
	public void paint(Graphics g){
		g.setColor(color);
		g.fillOval(x, y, radius, radius);
	}
	
	public Color getColor() {
		return color;
	}

}
