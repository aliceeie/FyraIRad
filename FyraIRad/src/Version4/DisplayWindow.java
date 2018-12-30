package Version4;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class DisplayWindow {
	
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		//Hämtar storleken på skärmen som används
	private int screenHeight = screenSize.height;
	private int screenWidth = screenSize.width;
	private JFrame window = new JFrame("FyraIRad");
	
	public DisplayWindow(int boardWidth, int boardHeight) {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds((screenWidth-boardWidth)/2, (screenHeight-boardHeight)/2, boardWidth + 50, boardHeight + 100);	//Visar fönstret mitt på skärmen i lagom storlek
	}
	
	public void showNewComponent(GameComponent component, String title) {
		window.setVisible(false);
		window.setTitle("FyraIRad - " + title);
		window.setContentPane(component);
	    System.out.println(component.toString() + " visas.");
		window.setVisible(true);
	}
}
