package Version4;

import java.awt.Color;

public enum Players {
	player1(Color.red, "Player1"), player2(Color.yellow, "Player2");
	
	private Color color;
	private String name;
	
	private Players(Color newColor, String newName) {
		this.color = newColor;
		this.name = newName;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}

}
