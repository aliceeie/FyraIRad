package Version2;

import java.awt.Color;

public class Player {
	private Color color;
	
	public Player(Color color){			//Skickar med vilken farg spelaren vill ha pa sin spelpjas
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}