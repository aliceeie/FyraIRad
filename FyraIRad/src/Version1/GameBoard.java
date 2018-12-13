package Version1;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
/**
 * A game board that contains 6x7 blocks.
 */
class GameBoard extends JComponent implements Drawable, ActionListener, KeyListener {
	
	private Circle whiteCircle;
	private Circle playersCircle;
	
	private ArrayList<ArrayList<Circle>> arrayOfTiles;
	private ArrayList<Circle> yLed; 
	private Player player;
	
	private Timer timer = new Timer (4,this);
	private int selectedRow;	
	
	private Graphics graphics;
	
	
	
	public GameBoard() {
		arrayOfTiles = new ArrayList<ArrayList<Circle>>(7);	//Sparar alla nya cirklar
		yLed = new ArrayList<Circle>(6); 					//Sparar alla cirklar i varje rad
		createWhiteCircles();
		selectedRow = 0;
		player = new Player(Color.red);
		initializeKeyListener();
	}
	
/*	private void createWhiteCircles() {
		for(int y = 0; y < 6; y++){
			arrayOfTiles.add(yLed);														//Lagger till alla kolumners cirklar i arrayOfTiles
			for(int x = 0; x < 7; x++){
				whiteCircle = new Circle(Color.white, 20 + 100*x, 20 + 100*y, 80);
				yLed.add(whiteCircle);													//Lagger till 7 vita cirklar i yLed
			}
		}		
	} */
	
	private void createWhiteCircles() {
		for(int y = 0; y < 6; y++){
			for(int x = 0; x < 7; x++){
				whiteCircle = new Circle(Color.white, 20 + 100*x, 20 + 100*y, 80);
				yLed.add(whiteCircle);													//Lagger till 6 vita cirklar i yLed
				System.out.println(whiteCircle);										
			}
			for(int x = 6; x < 7; x++){
				Circle pinkCircle = new Circle(Color.pink, 20 + 100*x, 20 + 100*y, 80);
				yLed.add(pinkCircle);													//Lagger till 6 rosa cirklar i yLed for att testa logiken
			}
			System.out.println("===========================");
			System.out.println(yLed);
			arrayOfTiles.add(yLed);														//Lagger till alla kolumners cirklar i arrayOfTiles
		}		
	}
	
	

	private void initializeKeyListener() {				//Skapar en ny Key Listener
		timer = new Timer (10,this);
		timer.start();
		addKeyListener(this);
        setFocusable (true);
        setFocusTraversalKeysEnabled(false);
	}
	
	public int placeOccupied (int placeToBeChecked) {
		int x = placeToBeChecked;
		System.out.println("x: " + x);
		while(x >=0 && !(arrayOfTiles.get(selectedRow).get(x).getColor().equals(Color.white))) {	//Om platsen dar vi ska satta brickan inte ar vit maste vi satta den pa en plats over
			System.out.println("arrayoftiles lÃ¤ngd: " + arrayOfTiles.size());
			System.out.println("selected row: " + selectedRow);
			System.out.println("Platsens fÃ¤rg: " + arrayOfTiles.get(selectedRow).get(x).getColor());
			
			x--;
			
			System.out.println("PLACE: " + x );
		}
		return x;
	}

	
	public void playersTile(){
		int yStart = 5;										//Borjar pa 5:e platsen i arrayen (längst ner)
		int y;
		System.out.println("x: " + yStart);
		y = placeOccupied(yStart);							//Platsen i y-led dar spelpjasen laggs
		
		System.out.println("platsen Y: " + y);
		System.out.println("SPELARENS Fï¿½RG " + player.getColor());

		
		playersCircle = new Circle(player.getColor(), 20 + 100*selectedRow, 20 + 100*y, 80);		//Skapar en ny cirkel som har spelarens farg pa den plats spelaren har valt
		arrayOfTiles.get(selectedRow).remove(y);
		arrayOfTiles.get(selectedRow).add(y, playersCircle);
		
		
	//	yLed.add(y, playersCircle);
	//	arrayOfTiles.add(selectedRow, yLed);
	}

	@Override
	public void paint(Graphics g) {
		this.graphics = g;
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);
		
		g.setColor(Color.cyan);
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);
		
		for(ArrayList<Circle> a : arrayOfTiles) {
			for(Circle c : yLed) {
				c.paint(g);
			}
		}
		if (!(playersCircle == null))
			playersCircle.paint(g);
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
			System.out.println("Ett hopp åt höger!");
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (selectedRow == 0) {
				selectedRow = 6;
			} else {
				selectedRow -= 1;
			}
			System.out.println("Ett hopp åt vänster!");
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			System.out.println("Ett drag registrerat! ====================");
			playersTile();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {				//Här ska spellogiken och repaint köras
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_DOWN) {					//Här ska turen gå över till andra spelaren
			System.out.println("Släppte nerknappen!");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

