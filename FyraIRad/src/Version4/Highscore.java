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
	private static int selectedItem;
	private STATE State;
	
	public Highscore(int boardWidth, int boardHeight) {
		title = "Highscore";
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
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(font0);
		g.drawString("HIGHSCORE", (boardWidth-(textWidth("HIGHSCORE", font0)))/2, 100);
		
		//Underrubriken
		g.setFont(font2);
		g.drawString("Exit to main menu with ESC-key", (boardWidth-(textWidth("Exit to main menu with ESC-key", font2)))/2, 160);
		
		//Rutor med text
		paintRectangle(g, "Two player mode", font1, boardHeight*3/8, -1, selectedItem, boardWidth);
		paintRectangle(g, "Single player mode", font1, boardHeight*5/8, -1, selectedItem, boardWidth);
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
