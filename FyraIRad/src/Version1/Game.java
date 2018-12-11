package Version1;

import java.awt.Color;
import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	private GameBoard board;
	private Player player1;
	private Player player2;
	
	public void start() {
		initialize();
		player1 = new Player(Color.red);
	}
	
	private void initialize() {
		window = new DisplayWindow();
		board = new GameBoard();
		
		window.show(g);
	}
	
	private void turn(){
		//if 
	}
	
}
