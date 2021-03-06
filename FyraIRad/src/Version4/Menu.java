package Version4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Menu extends GameComponent {
	
	private static int selectedItem;
	private STATE State;
	
	public Menu(int boardWidth, int boardHeight) {
		title = "Menu";
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
	private void setSelectedItem(int selectedItem) {
		Menu.selectedItem = selectedItem;
	}
	
	private enum STATE {
		MENU, GAME, COMPGAME, HIGHSCORE					//Meny-, tv�spelare-, enspelare- eller highscorel�ge
	};
	
	@Override
	public void paint(Graphics g) {
		//Bakgrunden
		g.setColor(Color.blue);
		g.fillRect(10, 10, boardWidth, boardHeight);
		
		//Rubriken
		g.setColor(Color.white);
		g.setFont(fontB70);
		g.drawString("FOUR IN A ROW", (boardWidth-(textWidth("FOUR IN A ROW", fontB70)))/2, 100);
		
		//Underrubriken
		g.setFont(fontP40);
		g.drawString("Select with enter-key", (boardWidth-(textWidth("Select with enter-key", fontP40)))/2, 160);
		
		//Rutor med text
		paintRectangle(g, "Play against friend", fontP50, 220, 0, selectedItem, boardWidth);
		paintRectangle(g, "Play against computer", fontP50, 320, 1, selectedItem, boardWidth);
		paintRectangle(g, "Highscore", fontP50, 420, 2, selectedItem, boardWidth);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		menuKeyPressed(keyCode);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
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