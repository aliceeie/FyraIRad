package Version3;

import javax.swing.JFrame;

public class DisplayWindow {
	
	private JFrame window = new JFrame();
	
	public DisplayWindow() {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(300, 100, 730, 660);
	}
	
	public void showGame(GameBoard gameBoard) {
		window.setContentPane(gameBoard);
//		window.getContentPane().add(gameBoard);						//Visar vart gameBoard
	    System.out.println("gameBoard");
		window.setVisible(true);
	}
}
