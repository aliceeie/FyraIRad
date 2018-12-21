package Version3;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.Timer;
/**
 * A game board that contains 6x7 blocks.
 */
class GameBoard extends JComponent implements Drawable, ActionListener, KeyListener {
	
	private Circle playersCircle;
	private Circle[][] arrayOfCircles;
	private Timer timer = new Timer (4,this);
	private Players turn;
	private int selectedRow;
	private boolean makeNewMove;

	public GameBoard() {
		arrayOfCircles = new Circle[7][6];	//Sparar alla cirklar
		createWhiteCircles();
		selectedRow = 0;
		turn = Players.player1;
		initializeKeyListener();
		makeNewMove = true;
	}
	
	private void changeTurn() {
		if (turn == Players.player1) {
			  turn = Players.player2;
		  }else {
			  turn = Players.player1;
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
		if(!arrayOfCircles[row][column].setColor(turn.getCurrentColor())) {		//markerar crikeln med rätt färg
			changeTurn();		//Om en kolumn är full ändras inga färger och byter turen för att det inte ska räknas som ett drag
			return;				//Avbryter om en kolumn är full
		}
		
		System.out.println(turn + " la en bricka: Row: " + row +
				". Column: " + column + ". Color: " + turn.getCurrentColor());
	}
	
	public boolean checkForWinner() {
		makeNewMove = true;
		return (checkHorizontal() || checkVertically() || checkDiagonals());		//Returnerar true om det finns en vinnare någonstans
	}
	
	private boolean checkHorizontal() {
		for(int y=0; y<6 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter en vinnare i x-led
				Color thisColor = arrayOfCircles[x][y].getColor();
				if (!thisColor.equals(Color.white) &&
						arrayOfCircles[x+1][y].getColor().equals(thisColor) &&
						arrayOfCircles[x+2][y].getColor().equals(thisColor) &&
						arrayOfCircles[x+3][y].getColor().equals(thisColor)) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkVertically() {
		for(int y=0; y<3 ; y++) {
			for(int x=0; x<7; x++) {														//Letar efter vinnare i y-led
				Color thisColor = arrayOfCircles[x][y].getColor();
				if (!thisColor.equals(Color.white) && 
						arrayOfCircles[x][y+1].getColor().equals(thisColor) &&
						arrayOfCircles[x][y+2].getColor().equals(thisColor) &&
						arrayOfCircles[x][y+3].getColor().equals(thisColor)) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkDiagonals() {
		for(int y=3; y<6 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter vinnare diagonalt upp�t
				Color thisColor = arrayOfCircles[x][y].getColor();
				if (!thisColor.equals(Color.white) && 
						arrayOfCircles[x+1][y-1].getColor().equals(thisColor) &&
						arrayOfCircles[x+2][y-2].getColor().equals(thisColor) &&
						arrayOfCircles[x+3][y-3].getColor().equals(thisColor)) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
					return true;
				}
			}
		}
		for(int y=0; y<3 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter vinnare diagonalt ner�t
				Color thisColor = arrayOfCircles[x][y].getColor();
				if (!thisColor.equals(Color.white) &&
						arrayOfCircles[x+1][y+1].getColor().equals(thisColor) &&
						arrayOfCircles[x+2][y+2].getColor().equals(thisColor) &&
						arrayOfCircles[x+3][y+3].getColor().equals(thisColor)) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);							//Malar rektangeln som är spelplanen
		
		g.setColor(Color.cyan);
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);			//Målar ut den rektangeln som visar den aktuellt valda kolumnen
		
		for(int y = 0; y < 6; y++){								//Malar alla cirklar pa spelplanen (oavsett vilken farg de har)
			for(int x = 0; x < 7; x++){
				arrayOfCircles[x][y].paint(g);
			}
		}
	}	

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//if(makeNewMove) {
			if (keyCode == KeyEvent.VK_DOWN) {				
				System.out.println("Slappte nerknappen!");
				markCircle(selectedRow);
				changeTurn();
				makeNewMove = false;
			}
		//}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

