package Version4;

import java.awt.Color;

public enum Players {
	player1(Color.red, "Player1", 0), player2(Color.yellow, "Player2", 0);
	
	private Color color;
	private String name;
	private int	moves;
	
	private Players(Color newColor, String newName, int newMoves) {
		this.color = newColor;
		this.name = newName;
		this.moves = newMoves;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
	public void resetMoves() {
		moves = 0;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void addMove() {
		moves++;
	}

}
