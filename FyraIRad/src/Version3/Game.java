package Version3;

import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	private GameBoard gameBoard;
	private boolean noWinner;
	
	public void start() {
		initialize();
		
		while(noWinner) {
			//System.out.println("Checking for winner...");
			checkForWinner();
		}
		System.out.println("Någon har vunnit, spelet är slut!");
	}
	
	private void initialize() {
		gameBoard = new GameBoard();
		window = new DisplayWindow(gameBoard);		
		window.show();
		noWinner = true;
	}
	
	private void checkForWinner() {
		noWinner = !gameBoard.checkForWinner();
	}
}
