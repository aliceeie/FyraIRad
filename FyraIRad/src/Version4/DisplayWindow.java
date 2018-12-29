package Version4;

import javax.swing.JFrame;

public class DisplayWindow {
	
	private JFrame window = new JFrame("FyraIRad");
	
	public DisplayWindow(int boardWidth, int boardHeight) {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(400, 100, boardWidth + 30, boardHeight + 60);
	}
	
	public void showGame(GameBoard gameBoard) {
		window.setVisible(false);
		window.setTitle("FyraIRad - Two player mode");
		window.setContentPane(gameBoard);
	    System.out.println("gameBoard visas.");
		window.setVisible(true);
		
	}
	
	public void showMenu(Menu menu) {
		window.setVisible(false);
		window.setTitle("FyraIRad - Main menu");
		window.setContentPane(menu);
	    System.out.println("menu visas.");
		window.setVisible(true);
	}
	
	public void showHighscore(Highscore highscore) {
		window.setVisible(false);
		window.setTitle("FyraIRad - Highscore");
		window.setContentPane(highscore);
	    System.out.println("highscore visas.");
		window.setVisible(true);
	}
}
