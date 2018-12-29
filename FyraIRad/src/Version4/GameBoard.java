package Version4;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * A game board that contains 6x7 blocks.
 */
public class GameBoard extends GameComponent {
	
	private Circle[][] arrayOfCircles;
	private STATE State;
	private boolean noWinner;
	private int[] infoWinnerLocation;
	private Players currentPlayer;
	private int selectedRow;

	public GameBoard(Players currentPlayer) {
		initializeKeyListener();
		arrayOfCircles = new Circle[7][6];
		createWhiteCircles();
		noWinner = true;
		this.currentPlayer = currentPlayer;
		this.State = STATE.GAME;
		selectedRow = 0;
		infoWinnerLocation = new int[4];
	}
	
	private enum STATE {
		MENU, GAME, COMPGAME					//Meny-, tv�spelare-, enspelarel�ge
	};
	
	private void changeTurn() {
		if(currentPlayer == Players.player1) {
			currentPlayer = Players.player2;
		} else {
			currentPlayer = Players.player1;
		}
	}
		
	private void createWhiteCircles() {						//Skapar vita cirklar som fungerar som bakgrund innan de fylls med rod eller gul farg
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y] = new Circle(Color.white, 20 + 100*x, 20 + 100*y, 80);
			}
		}
	}	
	
	private int getNextEmptyPlace(int row) {
		int y = 5;
		while(y > 0 && !(arrayOfCircles[row][y].getColor().equals(Color.white))){		//Om platsen dar vi ska satta brickan inte ar vit maste vi satta den pa en plats ovanfor (lagre y-varde)
			y--;
		}
		return y;
	}
	
	public boolean markCircle() {
		int column = getNextEmptyPlace(selectedRow);
		if(!arrayOfCircles[selectedRow][column].setColor(currentPlayer.getCurrentColor())) {		//markerar crikeln med rätt färg
			return false;				//Avbryter om en kolumn är full
		}
		System.out.println(currentPlayer + " la en bricka: Row: " + selectedRow +
				". Column: " + column + ". Color: " + currentPlayer.getCurrentColor());
		return true;
	}
	
	public boolean checkForWinner() {
		return (checkHorizontal() || checkVertically() || 
				checkRightDiagonal() || checkLeftDiagonal());	//Returnerar true om det finns en vinnare någonstans
	}
	
	private boolean checkHorizontal() {
		return checkGeneral(0, 6, 0, 4, 0, 1);
	}
	
	private boolean checkVertically() {
		return checkGeneral(0, 3, 0, 7, 1, 0);
	}
	
	private boolean checkRightDiagonal() {
		return checkGeneral(3, 6, 0, 4, -1, 1);
	}
	
	private boolean checkLeftDiagonal() {
		return checkGeneral(0, 3, 0, 4, 1, 1);
	}
	
	private boolean checkGeneral(int yStart, int yStop, int xStart, int xStop,
								int yAlter, int xAlter) {
		for(int y = yStart; y < yStop; y++) {
			for(int x = xStart; x < xStop; x++) {
				Color thisColor = arrayOfCircles[x][y].getColor();
				if (!thisColor.equals(Color.white) &&
						arrayOfCircles[x+xAlter][y+yAlter].getColor().equals(thisColor) &&
						arrayOfCircles[x+2*xAlter][y+2*yAlter].getColor().equals(thisColor) &&
						arrayOfCircles[x+3*xAlter][y+3*yAlter].getColor().equals(thisColor)) {
					System.out.println("VINNARE !!");
					
					noWinner = false;
					
					infoWinnerLocation[0] = arrayOfCircles[x][y].getX();
					infoWinnerLocation[1] = arrayOfCircles[x][y].getY();
					infoWinnerLocation[2] = arrayOfCircles[x+3*xAlter][y+3*yAlter].getX();
					infoWinnerLocation[3] = arrayOfCircles[x+3*xAlter][y+3*yAlter].getY();
					changeTurn(); 		//För att vinnande spelare ska bli rätt när det skriv ut
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);						//Malar rektangeln som är spelplanen
		
		if(noWinner) {
			paintSelectedRow(g);
			paintCircles(g);
		}
		else {
			paintHighlightWinner(g, infoWinnerLocation[0]+40, infoWinnerLocation[1]+40,
					infoWinnerLocation[2]+40, infoWinnerLocation[3]+40);
			paintCircles(g);
			paintWinnerText(g);
		}
	}
	
	private void paintCircles(Graphics g) {
		for(int y = 0; y < 6; y++){							//Malar alla cirklar pa spelplanen (oavsett vilken farg de har)
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y].paint(g);
			}
		}
	}
	
	private void paintSelectedRow(Graphics g) {
		if (currentPlayer.getCurrentColor() == Color.red) {
			g.setColor(Color.cyan);
		}
		else {
			g.setColor(Color.lightGray);
		}
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);	//Målar ut den rektangeln som visar den aktuellt valda kolumnen
	}
	
	private void paintHighlightWinner(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.cyan);
		g2.setStroke(new BasicStroke(80));
		g2.draw(new Line2D.Float(x1, y1, x2, y2));		//Målar rektangel där vinsten är
		
	}
	
	private void paintWinnerText(Graphics g) {			//Malar ut en text som sager vem som vunnit			
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(100, 200, 500, 180);
		Font font = new Font("SansSerif", Font.PLAIN, 100);
		g2.setFont(font);
		g2.setColor(currentPlayer.getCurrentColor());
		g2.drawString("Du vann!", 150, 320);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (State == STATE.GAME) {				//Om vi ar i tvåspellaget
			gameKeyPressed(keyCode);
		}
		else if (State == STATE.COMPGAME) {
			compGameKeyPressed(keyCode);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(noWinner) {
			if(State == STATE.GAME)
				gameKeyReleased(keyCode);
			if(State == STATE.COMPGAME)
				compGameKeyReleased(keyCode);
		}
	}
	
	private void gameKeyPressed(int keyCode) {
		if (currentPlayer == Players.player1) {				//Player1 spelar med piltangenterna
			if (keyCode == KeyEvent.VK_RIGHT) {
				if (selectedRow == 6) {
					selectedRow = 0;
				} else {
					selectedRow += 1;
				}
				System.out.println("Ett hopp at hoger!");
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				if (selectedRow == 0) {
					selectedRow = 6;
				} else {
					selectedRow -= 1;
				}
				System.out.println("Ett hopp at vanster!");
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				System.out.println("=====================================\nEtt drag registrerat!");
			}
		}
		if (currentPlayer == Players.player2) {				//Player1 spelar med asd-tangenterna
			if (keyCode == KeyEvent.VK_D) {
				if (selectedRow == 6) {
					selectedRow = 0;
				} else {
					selectedRow += 1;
				}
				System.out.println("Ett hopp at hoger!");
			}
			if (keyCode == KeyEvent.VK_A) {
				if (selectedRow == 0) {
					selectedRow = 6;
				} else {
					selectedRow -= 1;
				}
				System.out.println("Ett hopp at vanster!");
			}
			if (keyCode == KeyEvent.VK_S) {
				System.out.println("=====================================\nEtt drag registrerat!");
			}
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.out.println("ESC key was pressed.");
			State = STATE.MENU;
		}
	}
	
	private void compGameKeyPressed(int keyCode) {
		if (currentPlayer == Players.player1) {
			if (keyCode == KeyEvent.VK_RIGHT) {
				if (selectedRow == 6) {
					selectedRow = 0;
				} else {
					selectedRow += 1;
				}
				System.out.println("Ett hopp at hoger!");
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				if (selectedRow == 0) {
					selectedRow = 6;
				} else {
					selectedRow -= 1;
				}
				System.out.println("Ett hopp at vanster!");
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				System.out.println("=====================================\nEtt drag registrerat!");
			}
			if (keyCode == KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
			}
		}
	}
	
	private void gameKeyReleased(int keyCode) {
		if (currentPlayer == Players.player1) {	
			if (keyCode == KeyEvent.VK_DOWN) {				
				System.out.println("Slappte nerknappen!");
				if(noWinner)
					markCircle();
					changeTurn();
			}
		}
		else if (currentPlayer == Players.player2) {
			if (keyCode == KeyEvent.VK_S) {				
				System.out.println("Slappte nerknappen!");
				if(noWinner)
					markCircle();
					changeTurn();
			}
		}
	}
	
	private void compGameKeyReleased(int keyCode) {
		if (currentPlayer == Players.player1) { 
			if (keyCode == KeyEvent.VK_DOWN) {				
				System.out.println("Slappte nerknappen!");
				markCircle();
				if(noWinner)
					changeTurn();
			}
		}
		else if (currentPlayer == Players.player2) {

			//H�r ska datorn g�ra sitt drag
			
		}
	}

	@Override
	public int getNewState() {
		State = STATE.GAME;
		System.out.println("Väntar på vinst...");
		while(State == STATE.GAME) {
			//Väntar på att någon vinner
			if(noWinner) {
				checkForWinner();
			}
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

