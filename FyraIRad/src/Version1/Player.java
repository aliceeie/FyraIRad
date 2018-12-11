package Version1;

import java.awt.Color;

public class Player {
	private Color color;
	
	public Player(Color color){			//Skickar med vilken färg spelaren vill ha på sin spelpjäs
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}