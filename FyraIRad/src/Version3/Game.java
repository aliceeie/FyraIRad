package Version3;

public class Game {

	private DisplayWindow window;
	private GameBoard gameBoard;
	private boolean noWinner;

	public void start() {
		window = new DisplayWindow();
		initializeGame();
		while (noWinner) {
			// System.out.println("Checking for winner...");
			checkForWinner();
		}
		System.out.println("Nagon har vunnit, spelet ar slut!");
	}

	private void initializeGame() {
		gameBoard = new GameBoard();
		window.showGame(gameBoard);
		noWinner = true;
	}

	private void checkForWinner() {
		noWinner = !gameBoard.checkForWinner();
	}
}
