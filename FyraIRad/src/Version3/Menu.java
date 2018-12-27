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
	private String[] menuItems= new String[3];
	private String opponent;
	private int selectedItem = 0;
	
	public Menu() {
		initializeKeyListener();
		
	}
	/**
	 * selectedItem = 0 -> play
	 * selectedItem = 1 -> choose opponent
	 * selectedItem = 2 -> rules
	 */
	private void selectedButton() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		int x;
		
		//Bakgrundsrektangeln
		g.setColor(Color.blue);
		g.fillRect(10, 10, boardWitdh, boardHeight);
		
		Font font0 = new Font("arial", Font.BOLD, 70);
		Font font1 = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 40);
		
		g.setColor(Color.white);
		//Rubriken
		g.setFont(font0);
		g.drawString("FOUR IN A ROW", (boardWitdh-(textWidth("FOUR IN A ROW", font0)))/2, 100);
		
		g.setFont(font2);
		g.drawString("You're playing against " + "...", (boardWitdh-(textWidth("You're playing against " + "...", font2)))/2, 160);
		
		//Rutor med text
		paintRectangle(g, "Play", font1, 220, 0);
		paintRectangle(g, "Choose opponent", font1, 320, 1);
		paintRectangle(g, "Rules", font1, 420, 2);

	}
	/**
	 * Malar ut rektanglar med text inuti centrerade på spelplanen
	 * 
	 */
	private void paintRectangle(Graphics g, String text, Font font, int y, int item) {
		if(item == selectedItem) {
			g.setColor(Color.darkGray);
			g.fillRect(((boardWitdh-textWidth(text, font))/2)-10, y-10, textWidth(text, font)+20, textHeight(text, font)+20);			
		}
		
		g.setColor(Color.white);
		g.drawRect(((boardWitdh-textWidth(text, font))/2)-10, y-10, textWidth(text, font)+20, textHeight(text, font)+20);
		g.setFont(font);
		g.drawString(text, (boardWitdh-(textWidth(text, font)))/2, y-10+(textHeight(text, font)));
	}
	
	/**
	 * returnerar hojden pa texten som skickas med den font som ocksa skickas med
	 */
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
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_DOWN) {
			if (selectedItem == 2) {
				selectedItem = 0;
			} else {
				selectedItem += 1;
			}
			System.out.println("NER" + selectedItem);
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			if (selectedItem == 0)
				Game.changeState();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
