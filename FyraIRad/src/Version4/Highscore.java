package Version4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Highscore extends GameComponent {

	private static int boardWidth;
	private static int boardHeight;
	private static int selectedItem;
	private STATE State;
//	private String[] highscoreNames;

	
	public Highscore(int boardWidth, int boardHeight) {
		title = "Highscore";
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		State = STATE.HIGHSCORE;
		selectedItem = 0;
		initializeKeyListener();
	}
	
	private enum STATE {
		MENU, HIGHSCORE					//Meny- eller highscorelï¿½ge är de states som är möjliga att nå
	};
	
	@Override
	public void paint(Graphics g) {
		int y;
		//Bakgrunden
		g.setColor(Color.blue);
		g.fillRect(10, 10, boardWidth, boardHeight);
		
		g.setColor(Color.white);
		
		//Rubriken
		g.setFont(fontB70);
		g.drawString("HIGHSCORE", (boardWidth-(textWidth("HIGHSCORE", fontB70)))/2, 100);
		
		//Underrubriken
		g.setFont(fontP40);
		g.drawString("Exit to main menu with ESC-key", (boardWidth-(textWidth("Exit to main menu with ESC-key", fontP40)))/2, 160);
		
		//Vänstra kolumnen
		y = boardWidth/2;
		g.setFont(fontP50);
		g.drawString("Two player mode", (y-(textWidth("Two player mode", fontP50)))/2, boardHeight*3/8);
		g.setFont(fontP30);
		g.drawString("Name: number of moves", (y-(textWidth("Name: number of moves", fontP30)))/2, boardHeight*7/16);
		
		for(int x=0; x<5; x++) {
			g.drawString(x+1 + ". " + Integer.toString(highscoreMove[x]), 30, boardHeight*(x+9)/16);
		}
		
		//Linje i mitten
		g.drawLine(boardWidth/2, boardHeight*5/16, boardWidth/2, boardHeight*15/16);
		
		//Högra kolumnen
		g.setFont(fontP50);
		g.drawString("Single player mode", (y+(y-(textWidth("Single player mode", fontP50)))/2), boardHeight*3/8);
		g.setFont(fontP30);
		g.drawString("Name: number of moves", (y+(y-(textWidth("Name: number of moves", fontP30)))/2), boardHeight*7/16);

		for(int x=0; x<5; x++) {
			g.drawString(x+1 + ". " + ("---"), y+20, boardHeight*(x+9)/16);
		}
		
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
	public void keyReleased(KeyEvent e) {}

	@Override
	public int getNewState() {
		State = STATE.HIGHSCORE;
		System.out.println("VÃ¤ntar pÃ¥ att val gÃ¶rs...");
		while(State == STATE.HIGHSCORE) {
			//VÃ¤ntar pÃ¥ att anvÃ¤ndaren trycker exit
			try {
				TimeUnit.MILLISECONDS.sleep(100);	//Systemet vÃ¤ntar (sleeps) i 100 millisek. fÃ¶r att programmet hÃ¤nger sig annars...
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
