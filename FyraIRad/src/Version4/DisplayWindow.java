package Version4;

import javax.swing.JFrame;

public class DisplayWindow {
	
	private JFrame window = new JFrame("FyraIRad");
	
	public DisplayWindow(int boardWidth, int boardHeight) {
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(400, 100, boardWidth + 30, boardHeight + 60);
	}
	
	public void showNewComponent(GameComponent component, String title) {
		window.setVisible(false);
		window.setTitle("FyraIRad - " + title);
		window.setContentPane(component);
	    System.out.println(component.toString() + " visas.");
		window.setVisible(true);
	}
	
}
