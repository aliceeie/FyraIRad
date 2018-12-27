package Version3;

import javax.swing.JFrame;

import java.awt.Graphics;

public class DisplayWindow {
	
	private JFrame window = new JFrame();
	
	public DisplayWindow() {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(300, 100, 730, 660);
	}
	
	public void showGame(GameBoard gameBoard) {
		window.getContentPane().add(gameBoard);						//Visar vart gameBoard
	    window.setVisible(true);
	}
	
	public void showMenu(Menu menu) {
		window.getContentPane().add(menu);
	    window.setVisible(true);
	}
}
