package Version2;

import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	private GameBoard gameBoard;
	
	public void start() {
		initialize();
	}
	
	private void initialize() {
		gameBoard = new GameBoard();
		window = new DisplayWindow(gameBoard);		
		window.show();
	}
}
