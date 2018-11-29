package Version1;

import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	private GameBoard board;
	
	public void start() {
		initialize();
	}
	
	public void initialize() {
		window = new DisplayWindow();
		board = new GameBoard();
		
		window.show(g);
	}

}
