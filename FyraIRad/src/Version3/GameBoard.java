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
	private Timer timer; // = new Timer (4,this);
	private Players currentPlayer;					//Bytte namn pa turn till currentPlayer
	private int selectedRow;
	private boolean winnerDetected;
	private int[] infoWinnerLocation;

	public GameBoard() {
		initializeKeyListener();		
		arrayOfCircles = new Circle[7][6];	//Sparar alla cirklar
		createWhiteCircles();
		selectedRow = 0;
		currentPlayer = Players.player1;
		winnerDetected = false;
		infoWinnerLocation = new int[4];
	}
	
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
		
		if(winnerDetected) {
			paintHighlightWinner(g, infoWinnerLocation[0]+40, infoWinnerLocation[1]+40,
					infoWinnerLocation[2]+40, infoWinnerLocation[3]+40);
		} else {
			g.setColor(Color.cyan);
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
	public void actionPerformed(ActionEvent arg0) {				//Vad som hander da klockan slar
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_DOWN) {				
				System.out.println("Slappte nerknappen!");
				
				markCircle(selectedRow);
				if(!winnerDetected)
					changeTurn();
			}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

