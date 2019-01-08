package Version4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Highscore extends GameComponent {

	private STATE State;
	private static int[][] highscoreMove = new int[2][5];			//Variabel f�r att spara b�sta antal drag

	
	public Highscore(int boardWidth, int boardHeight, String  link2p, String link1p) {
		title = "Highscore";
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		State = STATE.HIGHSCORE;
		initializeKeyListener();
		load(link2p, 0);
		load(link1p, 1);
	}
	
	private enum STATE {
		MENU, HIGHSCORE					//Meny- eller highscorel�ge �r de states som �r m�jliga att n�
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
		
		//V�nstra kolumnen
		y = boardWidth/2;
		g.setFont(fontP50);
		g.drawString("Two player mode", (y-(textWidth("Two player mode", fontP50)))/2, boardHeight*3/8);
		g.setFont(fontP30);
		g.drawString("Number of moves", (y-(textWidth("Number of moves", fontP30)))/2, boardHeight*7/16);
		
		for(int x=0; x<5; x++) {
			g.drawString(x+1 + ". " + Integer.toString(highscoreMove[0][x]), 30, boardHeight*(x+9)/16);
		}
		
		//Linje i mitten
		g.drawLine(boardWidth/2, boardHeight*5/16, boardWidth/2, boardHeight*15/16);
		
		//H�gra kolumnen
		g.setFont(fontP50);
		g.drawString("Single player mode", (y+(y-(textWidth("Single player mode", fontP50)))/2), boardHeight*3/8);
		g.setFont(fontP30);
		g.drawString("Number of moves", (y+(y-(textWidth("Number of moves", fontP30)))/2), boardHeight*7/16);

		for(int x=0; x<5; x++) {
			g.drawString(x+1 + ". " + Integer.toString(highscoreMove[1][x]), y+20, boardHeight*(x+9)/16);
		}
		
	}
	
	public void setNewHighscore(int newHighscore, int mode, String link){
		if (newHighscore >= highscoreMove[mode][4]) {
			return;
		}
		int x=4;
		while (x>=0){
			if (newHighscore >= highscoreMove[mode][x]){
				break;				
			}
			x--;
		}
		x++;
		if (x != 4) {
			System.out.println("det funkar" + x);
			for (int i = 3; i >= x; i--) {
				highscoreMove[mode][i+1] = highscoreMove[mode][i];
			}
			highscoreMove[mode][x] = newHighscore;
			save(link, mode);
			load(link, mode);
		}
	}

	public void load(String link, int mode) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(link));
			while (reader.ready()) {
				for (int x=0; x<5; x++){
					highscoreMove[mode][x] = Integer.parseInt(reader.readLine());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(String link, int mode) {
		try {
			File file = new File(link);
			file.delete();
			FileOutputStream fos = new FileOutputStream(file);
			file.createNewFile();
			
			for(int i = 0; i<5; i++){
				String content = Integer.toString(highscoreMove[mode][i]) + "\n";
				byte[] contentInBytes = content.getBytes();					//Gör om content-texten till bytes för att kunna skicka in det i outputStreamen
				fos.write(contentInBytes);				
			}
			fos.flush();												//Skickar outputStreamen till filen
			fos.close();												//Stänger outputStreamen
				
		} catch (Exception e) {
			e.printStackTrace();
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
