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
	
	public GameBoard() {
		arrayOfTiles = new ArrayList<ArrayList<Circle>>();	//Sparar alla nya cirklar
		yLed = new ArrayList<Circle>(); 					//Sparar alla cirklar i varje rad
		createWhiteCircles();
		selectedRow = 0;
		player = new Player(Color.red);
		initializeKeyListener();
	}
	
	private void createWhiteCircles() {
		for(int y = 0; y < 6; y++){
			arrayOfTiles.add(yLed);														//L�gger till alla kolumners cirklar i arrayOfTiles
			for(int x = 0; x < 7; x++){
				whiteCircle = new Circle(Color.white,20 + 100*x, 20 + 100*y, 80);
				yLed.add(whiteCircle);													//L�gger till 7 vita cirklar i yLed
			}
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
		for(int x = placeToBeChecked; x>=0 ; x--) {
			if(arrayOfTiles.get(selectedRow).get(x).getColor().equals(Color.white)) {				//Om platsen d�r vi ska s�tta brickan inte �r vit m�ste vi s�tta den p� en plats �ver
				System.out.println("PLACE: " + x );
				return x;
			}
		}
		System.out.println("PLACEet: " + placeToBeChecked );
		return placeToBeChecked;				//H�R M�STE VI RETURNERA FELLMEDDELANDE "hela stapeln �r full"
	}
	
	public void playersTile(){
		int y = 5;										//B�rjar p� 5:e platsen i arrayen (l�ngst ner)
		int place = 0;

		place = placeOccupied(y);					//Platsen i y-led d�r spelpj�sen l�ggs
		
		System.out.println("SPELARENS F�RG " + player.getColor());
		playersCircle = new Circle(player.getColor(), 20 + 100*selectedRow, 20 + 100*place, 80);		//Skapar en ny cirkel som har spelarens f�rg p� den plats spelaren har valt
		yLed.add(y, playersCircle);
		arrayOfTiles.add(selectedRow, yLed);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(10, 10, 700, 600);
		
		g.setColor(Color.cyan);
		g.fillRect(20 + 100*selectedRow, 20, 80, 580);
		
		for(ArrayList<Circle> a : arrayOfTiles) {
			for(Circle c : yLed) {
				c.paint(g);
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
			System.out.println("Ett hopp �t h�ger!");
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (selectedRow == 0) {
				selectedRow = 6;
			} else {
				selectedRow -= 1;
			}
			System.out.println("Ett hopp �t v�nster!");
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			playersTile();
			System.out.println("Ett drag registrerat!");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_DOWN) {					//H�r ska turen g� �ver till andra spelaren
			System.out.println("Sl�ppte nerknappen!");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}

