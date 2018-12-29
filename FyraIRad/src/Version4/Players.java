package Version4;

import java.awt.Color;

public enum Players {
	player1(Color.red), player2(Color.yellow);
	
	private Color currentColor;
	
	private Players(Color newCurrentColor) {
		this.currentColor = newCurrentColor;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}

}
