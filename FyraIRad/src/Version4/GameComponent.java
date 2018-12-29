package Version4;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public abstract class GameComponent extends JComponent implements Drawable, ActionListener, KeyListener {

	protected Timer timer;
	
	@Override
	public abstract void paint(Graphics g);
	
	@Override
	public abstract void keyPressed(KeyEvent e);

	@Override
	public abstract void keyReleased(KeyEvent e);
	
	public abstract int getNewState();

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void actionPerformed(ActionEvent e) {			//Vad som händer då klockan slår
		repaint();
	}
	
	protected void initializeKeyListener() {				//Skapar en ny Key Listener som uppdateras med klockan
		timer = new Timer (10,this);
		timer.start();
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
}
