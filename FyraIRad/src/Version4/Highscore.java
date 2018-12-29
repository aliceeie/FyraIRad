package Version4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;

public class Highscore extends GameComponent {

	private static int boardWidth;
	private static int boardHeight;
	public static int selectedItem;
	private STATE State;
	
	public Highscore(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		State = STATE.HIGHSCORE;
		selectedItem = 0;
		initializeKeyListener();
	}
	
	private enum STATE {
		MENU, HIGHSCORE					//Meny- eller highscorel�ge
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
		Font font3 = new Font("arial", Font.PLAIN, 30);
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(font0);
		g.drawString("HIGHSCORE", (boardWidth-(textWidth("HIGHSCORE", font0)))/2, 100);
		
		//Underrubriken
		g.setFont(font2);
		g.drawString("Exit to main menu with esc-key", (boardWidth-(textWidth("Exit to main menu with ESC-key", font2)))/2, 160);
		
		//Rutor med text
		paintRectangle(g, "Two player mode", font1, boardHeight*3/8, -1);
		paintRectangle(g, "Single player mode", font1, boardHeight*5/8, -1);
		g.setFont(font3);
		g.drawString("Namn: antal brickor lagda till vinst", (boardWidth-(textWidth("Namn: antal brickor lagda till vinst", font3)))/2, boardHeight*3/8 + 100);
		g.drawString("Namn: antal brickor lagda till vinst", (boardWidth-(textWidth("Namn: antal brickor lagda till vinst", font3)))/2, boardHeight*5/8 + 100);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.out.println("ESC key was pressed.");
			State = STATE.MENU;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
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

	@Override
	public int getNewState() {
		State = STATE.HIGHSCORE;
		System.out.println("Väntar på att val görs...");
		while(State == STATE.HIGHSCORE) {
			//Väntar på att användaren trycker exit
			try {
				TimeUnit.MILLISECONDS.sleep(100);	//Systemet väntar (sleeps) i 100 millisek. för att programmet hänger sig annars...
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(State == STATE.MENU) {
			return 0;
		}
		return -1;
	}

}
