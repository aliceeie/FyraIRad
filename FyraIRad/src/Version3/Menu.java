package Version3;

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

public class Menu extends JComponent implements Drawable, ActionListener, KeyListener{
	private Timer timer;
	private int boardWitdh = 700;
	private int boardHeight = 600;
	
	public Menu() {
		initializeKeyListener();
		
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		int x;
		
		//Bakgrundsrektangeln
		g.setColor(Color.blue);
		g.fillRect(10, 10, boardWitdh, boardHeight);
		
		Font font0 = new Font("arial", Font.BOLD, 70);
		Font font1 = new Font("arial", Font.PLAIN, 50);
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(font0);
		g.drawString("FYRA I RAD", (boardWitdh-(textWidth("FYRA I RAD", font0)))/2, 100);
		
		//Rutor med text
		paintRectangle(g, "Play", font1, 100+textHeight("F", font0));						//FIXA HÖJDEN
		paintRectangle(g, "Choose opponent", font1, 300);
		paintRectangle(g, "Rules", font1, 400);
		paintRectangle(g, "Exit", font1, 500);

	}
	/**
	 * Malar ut rektanglar med text inuti centrerade på spelplanen
	 * 
	 */
	private void paintRectangle(Graphics g, String text, Font font, int y) {
		g.drawRect(((boardWitdh-textWidth(text, font))/2)-10, y-10, textWidth(text, font)+20, textHeight(text, font)+20);
		g.setFont(font);
		g.drawString(text, (boardWitdh-(textWidth(text, font)))/2, y-10+(textHeight(text, font)));
	}
	
	private int textHeight(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		return textheight;
	}
	
	private int textWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		return textwidth;
	}
	
	private void initializeKeyListener() {
		timer = new Timer (10,this);
		timer.start();
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}

}
