package Version2;

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
	private Player player;
	private int selectedRow;

	public GameBoard() {
		arrayOfCircles = new Circle[7][6];	//Sparar alla cirklar
		createWhiteCircles();
		selectedRow = 0;
		player = new Player(Color.red);		//Rod spelare borjar
		initializeKeyListener();
	}

	private Player turn (Player player) {					//Turen gar over till den andra spelaren
		System.out.println("FARG: " + player.getColor());
		if(player.getColor().equals(Color.red)) {			//Fran rod till gul
			this.player = new Player(Color.yellow);
			return this.player;
		}else {
			this.player = new Player(Color.red);			//Fran gul till rod
			return this.player;	
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
        setFocusable (true);
        setFocusTraversalKeysEnabled(false);
	}
	
	public int isPlaceOccupied (int y) {
		while(y > 0 && !(arrayOfCircles[selectedRow][y].getColor().equals(Color.white))){		//Om platsen dar vi ska satta brickan inte ar vit maste vi satta den pa en plats ovanfor (lagre y-varde)
			y--;
		}
		return y;
	}

	/**
	 * Skapar en ny cirkel med spelarens farg och sparar den pa ratt plats i arrayOfCircles	
	 */
	public void playersTile(){
		int yStart = 5;																			//Borjar pa 5:e platsen i arrayen (längst ner)
		int y = isPlaceOccupied(yStart);														//Platsen i y-led dar spelpjasen laggs
	
		playersCircle = new Circle(player.getColor(), 20 + 100*selectedRow, 20 + 100*y, 80);	//Skapar en ny cirkel som har spelarens farg pa den plats spelaren har valt
		
		System.out.println("X: " + selectedRow);
		System.out.println("Y: " + y);

		arrayOfCircles[selectedRow][y] = playersCircle;											//Platsen med selectedRow som x-varde och y som y-varde ar den plats som spelarens cirkel sparas på
	}
	
	/**
	 * Denna klass ansvarar for spellogiken och kollar om det finns en vinnare
	 */
	private void logic(){
		for(int y=0; y<6 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter en vinnare i x-led
				if (arrayOfCircles[x][y].getColor() == playersCircle.getColor() && 
						arrayOfCircles[x+1][y].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+2][y].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+3][y].getColor() == playersCircle.getColor()) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
				}
			}
		}
		for(int y=0; y<3 ; y++) {
			for(int x=0; x<7; x++) {														//Letar efter vinnare i y-led
				if (arrayOfCircles[x][y].getColor() == playersCircle.getColor() && 
						arrayOfCircles[x][y+1].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x][y+2].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x][y+3].getColor() == playersCircle.getColor()) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
				}
			}
		}
		for(int y=3; y<6 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter vinnare diagonalt uppåt
				if (arrayOfCircles[x][y].getColor() == playersCircle.getColor() && 
						arrayOfCircles[x+1][y-1].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+2][y-2].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+3][y-3].getColor() == playersCircle.getColor()) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
				}
			}
		}
		for(int y=0; y<3 ; y++) {
			for(int x=0; x<4; x++) {														//Letar efter vinnare diagonalt neråt
				if (arrayOfCircles[x][y].getColor() == playersCircle.getColor() && 
						arrayOfCircles[x+1][y+1].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+2][y+2].getColor() == playersCircle.getColor() &&
						arrayOfCircles[x+3][y+3].getColor() == playersCircle.getColor()) {
					System.out.println("VINNARE !!");										//och spelet ska avslutas
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);
		
		g.setColor(Color.cyan);
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);
		
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
		if (keyCode == KeyEvent.VK_DOWN) {					
			System.out.println("Slappte nerknappen!");
			playersTile();									//Har läggs spelpjasen ut
			logic();
			turn(player);									//Har gar turen over till andra spelaren
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

