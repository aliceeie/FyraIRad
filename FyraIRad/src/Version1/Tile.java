package Version1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Tile extends JComponent implements Drawable{		//Vet inte om vi vill ha spelbrickorna i en separat klass eller i GameBoard

	public Tile(int selectedRow) {								//Konstruktör
		
		
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
	}
	
}
