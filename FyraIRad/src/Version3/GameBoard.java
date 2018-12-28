package Version3;

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

import javax.swing.JComponent;
import javax.swing.Timer;
/**
 * A game board that contains 6x7 blocks.
 */
class GameBoard extends JComponent implements Drawable, ActionListener, KeyListener {
	
	private Circle[][] arrayOfCircles;
	private Timer timer;
	private Players currentPlayer;
	private int selectedRow;
	private boolean winnerDetected;
	private int[] infoWinnerLocation;
	
	private STATE State;
	private int selectedItem = 0;
	

	public GameBoard() {
		State = STATE.MENU;					//Startar i menylage
		initializeKeyListener();
		arrayOfCircles = new Circle[7][6];	//Sparar alla cirklar
		createWhiteCircles();
		selectedRow = 0;
		currentPlayer = Players.player1;	//Just nu b�rjar alltid player1 (r�d)
		winnerDetected = false;
		infoWinnerLocation = new int[4];
	}
	
	private enum STATE {
		MENU, GAME, COMPGAME, HIGHSCORE					//Meny-, tv�spelare-, enspelare- eller highscorel�ge
	};
	
	private void changeTurn() {
		if (currentPlayer == Players.player1) {
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

	private void initializeKeyListener() {					//Skapar en ny Key Listener som uppdateras med klockan
		timer = new Timer (10,this);
		timer.start();
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
	public int getNextEmptyPlace() {
		int y = 5;
		while(y > 0 && !(arrayOfCircles[selectedRow][y].getColor().equals(Color.white))){		//Om platsen dar vi ska satta brickan inte ar vit maste vi satta den pa en plats ovanfor (lagre y-varde)
			y--;
		}
		return y;
	}
	
	private void markCircle(int row) {
		int column = getNextEmptyPlace();
		if(!arrayOfCircles[row][column].setColor(currentPlayer.getCurrentColor())) {		//markerar crikeln med rätt färg
			changeTurn();		//Om en kolumn är full ändras inga färger och byter turen för att det inte ska räknas som ett drag
			return;				//Avbryter om en kolumn är full
		}
		
		System.out.println(currentPlayer + " la en bricka: Row: " + row +
				". Column: " + column + ". Color: " + currentPlayer.getCurrentColor());
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
					
					winnerDetected = true;
					
					infoWinnerLocation[0] = arrayOfCircles[x][y].getX();
					infoWinnerLocation[1] = arrayOfCircles[x][y].getY();
					infoWinnerLocation[2] = arrayOfCircles[x+3*xAlter][y+3*yAlter].getX();
					infoWinnerLocation[3] = arrayOfCircles[x+3*xAlter][y+3*yAlter].getY();
					
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
		
		
		if (State == STATE.MENU) {					 //Om vi ar i menylaget
			Menu.setSelectedItem(selectedItem);
			Menu.render(g);
		} 
		else if (State == STATE.GAME || State == STATE.COMPGAME){
			if(winnerDetected) {
				paintHighlightWinner(g, infoWinnerLocation[0]+40, infoWinnerLocation[1]+40,
						infoWinnerLocation[2]+40, infoWinnerLocation[3]+40);
			} else {
				if (currentPlayer.getCurrentColor() == Color.red)
					g.setColor(Color.cyan);
				else 
					g.setColor(Color.lightGray);
				g.fillRect(20 + 100*selectedRow, 20, 80, 580);	//Målar ut den rektangeln som visar den aktuellt valda kolumnen
			}
			
			for(int y = 0; y < 6; y++){							//Malar alla cirklar pa spelplanen (oavsett vilken farg de har)
				for(int x = 0; x < 7; x++){
					arrayOfCircles[x][y].paint(g);
				}
			}
			if(winnerDetected) {
				paintWinnerMenu(g);
			}
		}
		else if (State == STATE.HIGHSCORE) {
			
			//H�r ska highscoren ritas ut p� ett bra s�tt 
			g.setColor(Color.white);
			Font font2 = new Font("arial", Font.PLAIN, 40);
			g.setFont(font2);
			g.drawString("H�r ska highscoren skrivas ut", 100, 200);
			
		}
	}
	
	private void paintHighlightWinner(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.cyan);
		g2.setStroke(new BasicStroke(80));
		g2.draw(new Line2D.Float(x1, y1, x2, y2));		//Målar rektangel där vinsten är
		
	}
	
	private void paintWinnerMenu(Graphics g) {			//Malar ut en text som sager vem som vunnit			
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
		
		if (State == STATE.MENU)		//Om vi ar i menylaget
			menuKeyPressed(keyCode);
		else if (State == STATE.GAME)		//Om vi ar i spellaget
			gameKeyPressed(keyCode);
		else if (State == STATE.COMPGAME)
			compGameKeyPressed(keyCode);

	}
		/**
		 * Tar hand om alla inmatningar d� vi �r i meny-l�get
		 * 
		 */
		private void menuKeyPressed(int keyCode) {
			if (keyCode == KeyEvent.VK_DOWN) {
				if (selectedItem == 2) {
					selectedItem = 0;
				} else {
					selectedItem += 1;
				}
				System.out.println("NER" + selectedItem);
			}
			if (keyCode == KeyEvent.VK_UP) {
				if (selectedItem == 0) {
					selectedItem = 2;
				} else {
					selectedItem -= 1;
				}
				System.out.println("NER" + selectedItem);
			}
			if (keyCode == KeyEvent.VK_ENTER) {
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
		/**
		 * Tar hand om alla inmatningar d� vi �r i tv�spelarl�get
		 * 
		 */
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
				State = STATE.MENU;
			}
		}
		/**
		 * Tar hand om alla inmatningar d� vi �r i en-spelare-mot-datorn-spell�get
		 * 
		 */
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {				//Vad som hander da klockan slar
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (State == STATE.GAME)
			gameKeyReleased(keyCode);
		if (State == STATE.COMPGAME)
			compGameKeyReleased(keyCode);
	}
	
		private void gameKeyReleased(int keyCode) {
			if (currentPlayer == Players.player1) {	
				if (keyCode == KeyEvent.VK_DOWN) {				
					System.out.println("Slappte nerknappen!");
					markCircle(selectedRow);
					if(!winnerDetected)
						changeTurn();
				}
			}
			else if (currentPlayer == Players.player2) {
				if (keyCode == KeyEvent.VK_S) {				
					System.out.println("Slappte nerknappen!");
					markCircle(selectedRow);
					if(!winnerDetected)
						changeTurn();
				}
			}
		}
		private void compGameKeyReleased(int keyCode) {
			if (currentPlayer == Players.player1) { 
				if (keyCode == KeyEvent.VK_DOWN) {				
					System.out.println("Slappte nerknappen!");
					markCircle(selectedRow);
					if(!winnerDetected)
						changeTurn();
				}
			}
			else if (currentPlayer == Players.player2) {
	
				//H�r ska datorn g�ra sitt drag
				
			}
		}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

