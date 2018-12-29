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
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Menu extends GameComponent {
	
	private static int boardWidth;		//Skapar variabler for att gora klassen mer generell
	private static int boardHeight;
	public static int selectedItem;
	private STATE State;
	
	public Menu(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		selectedItem = 0;
		State = STATE.MENU;
		initializeKeyListener();
	}
	
	/**
	 * selectedItem = 0 -> play two players
	 * selectedItem = 1 -> play one player
	 * selectedItem = 2 -> check highscore
	 */
	public void setSelectedItem(int selectedItem) {
		Menu.selectedItem = selectedItem;
	}
	
	public int getSelectedItem() {
		return selectedItem;
	}
	
	private enum STATE {
		MENU, GAME, COMPGAME, HIGHSCORE					//Meny-, tv�spelare-, enspelare- eller highscorel�ge
	};
	
	@Override
	public void paint(Graphics g) {
		//Bakgrunden
		g.setColor(Color.blue);
		g.fillRect(10, 10, boardWidth, boardHeight);
		
		//Skapar ett antal typsnitt som senare anvands
		Font font0 = new Font("arial", Font.BOLD, 70);
		Font font1 = new Font("arial", Font.PLAIN, 50);
		Font font2 = new Font("arial", Font.PLAIN, 40);
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(font0);
		g.drawString("FOUR IN A ROW", (boardWidth-(textWidth("FOUR IN A ROW", font0)))/2, 100);
		
		//Underrubriken
		g.setFont(font2);
		g.drawString("Select with enter-key", (boardWidth-(textWidth("Select with enter-key", font2)))/2, 160);
		
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
	private static int textHeight(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		return textheight;
	}
	/**
	 * Returnerar bredden pa texten som skickas med den font som ocksa skickas med
	 */
	private static int textWidth(String text, Font font) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		return textwidth;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		menuKeyPressed(keyCode);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	/**
	 * Tar hand om alla inmatningar d� vi �r i meny-l�get
	 */
	private void menuKeyPressed(int keyCode) {
		if (keyCode == KeyEvent.VK_DOWN) {
			if (selectedItem == 2) {
				selectedItem = 0;
			} else {
				selectedItem += 1;
			}
			System.out.println("NER till " + selectedItem);
		}
		if (keyCode == KeyEvent.VK_UP) {
			if (selectedItem == 0) {
				selectedItem = 2;
			} else {
				selectedItem -= 1;
			}
			System.out.println("UPP till " + selectedItem);
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			System.out.println("Enter key was pressed.");
			if (selectedItem == 0) {
				State = STATE.GAME;
			}
			else if (selectedItem == 1) {
				State = STATE.COMPGAME;
			}
			else if (selectedItem == 2) {
				State = STATE.HIGHSCORE;
			}
		}
	}
	
	public int getNewState() {
		State = STATE.MENU;
		System.out.println("Väntar på att val görs...");
		while(State == STATE.MENU) {
			//Väntar på att användaren gör ett val i menyn
			try {
				TimeUnit.MILLISECONDS.sleep(100);	//Systemet väntar (sleeps) i 100 millisek. för att programmet hänger sig annars...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(State == STATE.GAME) {
			return 1;
		}
		if(State == STATE.COMPGAME) {
			return 2;
		}
		if(State == STATE.HIGHSCORE) {
			return 3;
		}
		return -1;
	}
	
}
