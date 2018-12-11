package Version1;

<<<<<<< HEAD
=======
import java.awt.Color;
>>>>>>> 4647702eeaae6118ab966660425c7a66fab9ed16
import java.awt.Graphics;

public class Game {
	
	private Graphics g;
	private DisplayWindow window;
	private GameBoard board;
<<<<<<< HEAD
	
	public void start() {
		initialize();
=======
	private Player player1;
	private Player player2;
	
	public void start() {
		initialize();
		player1 = new Player(Color.red);
>>>>>>> 4647702eeaae6118ab966660425c7a66fab9ed16
	}
	
	private void initialize() {
		window = new DisplayWindow();
		board = new GameBoard();
		
		window.show(g);
	}
	
<<<<<<< HEAD
	
	
	
	
=======
	private void turn(){
		//if 
	}
>>>>>>> 4647702eeaae6118ab966660425c7a66fab9ed16
	
}
