package Version4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
import javax.swing.Timer;

public abstract class GameComponent extends JComponent implements Drawable, ActionListener, KeyListener {

	protected static int boardWidth;
	protected static int boardHeight;
	protected String title;
	protected Timer timer;
	//Skapar ett antal typsnitt som senare anvands
	protected Font fontB70 = new Font("arial", Font.BOLD, 70);
	protected Font fontP50 = new Font("arial", Font.PLAIN, 50);
	protected Font fontP40 = new Font("arial", Font.PLAIN, 40);
	protected Font fontP30 = new Font("arial", Font.PLAIN, 30);
	
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
	
	
	
	
	/**
	 * Malar ut rektanglar med text inuti centrerade p� spelplanen
	 * 
	 */
	protected static void paintRectangle(Graphics g, String text, Font font, int y, int item, int selectedItem, int boardWidth) {
		if(item == selectedItem) {
			g.setColor(Color.darkGray);
			g.fillRect(((boardWidth-textWidth(text, font))/2)-10, y-10, textWidth(text, font)+20, textHeight(text, font)+20);			
		}
		
		g.setColor(Color.white);
		g.drawRect(((boardWidth-textWidth(text, font))/2)-10, y-10, textWidth(text, font)+20, textHeight(text, font)+20);
		g.setFont(font);
		g.drawString(text, (boardWidth-(textWidth(text, font)))/2, y-10+(textHeight(text, font)));
	}
	
	/**
	 * Returnerar hojden pa texten som skickas med den font som ocksa skickas med
	 */
	protected static int textHeight(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		return textheight;
	}
	/**
	 * Returnerar bredden pa texten som skickas med den font som ocksa skickas med
	 */
	protected static int textWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		return textwidth;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
