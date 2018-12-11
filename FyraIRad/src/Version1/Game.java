package Version1;

import java.awt.Color;
import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	
	public void start() {
		initialize();	
	}
	
	private void initialize() {
		window = new DisplayWindow();		
		window.show(g);
	}
	
}
