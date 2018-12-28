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

public class Menu {
	private static int boardWitdh = 700;		//Skapar variabler for att gora klassen mer generell
	private static int boardHeight = 600;
	public static int selectedItem = 0; 
	
	/**
	 * selectedItem = 0 -> play
	 * selectedItem = 1 -> choose opponent
	 * selectedItem = 2 -> rules
	 */
	public static void setSelectedItem(int selectedItem) {
		Menu.selectedItem = selectedItem;
	}
	
	public static void render(Graphics g) {
		//Skapar ett antal typsnitt som senare anvands
		Font font0 = new Font("arial", Font.BOLD, 70);
		Font font1 = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 40);
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(font0);
		g.drawString("FOUR IN A ROW", (boardWitdh-(textWidth("FOUR IN A ROW", font0)))/2, 100);
		
		//Vem motstandaren ar
		g.setFont(font2);
		g.drawString("Select with enter-key", (boardWitdh-(textWidth("Select with enter-key", font2)))/2, 160);
		
		//Rutor med text
		paintRectangle(g, "Play against friend", font1, 220, 0);
		paintRectangle(g, "Play against computer", font1, 320, 1);
		paintRectangle(g, "Highscore", font1, 420, 2);

	}
	/**
	 * Malar ut rektanglar med text inuti centrerade p� spelplanen
	 * 
	 */
	private static void paintRectangle(Graphics g, String text, Font font, int y, int item) {
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
	private static int textHeight(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		return textheight;
	}
	/**
	 * returnerar bredden pa texten som skickas med den font som ocksa skickas med
	 */
	private static int textWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		return textwidth;
	}

}
