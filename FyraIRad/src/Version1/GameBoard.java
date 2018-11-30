package Version1;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
/**
 * A game board that contains 6x7 blocks.
 */
class GameBoard extends JComponent implements Drawable, ActionListener, KeyListener {
	
	private Circle[][] arrayOfCircles;
	private Timer timer = new Timer (4,this);
	private int selectedRow;
	
	public GameBoard() {
		arrayOfCircles = new Circle[7][6];
		createCircles();
		initializeKeyListener();
		selectedRow = 0;
	}
	
	private void createCircles() {
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y] = new Circle(Color.white,20 + 100*x, 20 + 100*y, 80);
			}
		}
	}
	
	private void initializeKeyListener() {
		timer = new Timer (10,this);
		timer.start();
		addKeyListener(this);
        setFocusable (true);
        setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);
		
		g.setColor(Color.cyan);
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);
		
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y].paint(g);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (selectedRow == 6) {
				selectedRow = 0;
			} else {
				selectedRow += 1;
			}
			System.out.println("Ett hopp åt höger!");
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (selectedRow == 0) {
				selectedRow = 6;
			} else {
				selectedRow -= 1;
			}
			System.out.println("Ett hopp åt vänster!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

