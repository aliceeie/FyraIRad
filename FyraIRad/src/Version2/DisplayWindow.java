package Version2;

import javax.swing.JFrame;

import java.awt.Graphics;

public class DisplayWindow {
	
	private JFrame window = new JFrame();
	
	public DisplayWindow(GameBoard gameBoard) {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(300, 100, 730, 660);
		window.getContentPane().add(gameBoard);
	}
	
	public void show() {
	    window.setVisible(true);
	}
}
