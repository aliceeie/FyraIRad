package Version3;

public class Game {
	
	private DisplayWindow window;
	private Menu menu;
	private GameBoard gameBoard;
	private boolean noWinner;
	private STATE State;
	
	public void start() {
		State = STATE.MENU;	
		window = new DisplayWindow();
		
		if (State == STATE.MENU) {
			menu = new Menu();
			window.showMenu(menu);
			
		}else if (State == STATE.GAME) {
			initializeGame();
			while(noWinner) {
				//System.out.println("Checking for winner...");
				checkForWinner();
			}
			System.out.println("Nagon har vunnit, spelet ar slut!");
		}
	}
	
	private void initializeGame() {
		gameBoard = new GameBoard();
		window.showGame(gameBoard);
		noWinner = true;
	}
	
	private enum STATE{
		MENU, GAME
	};
	
	private void checkForWinner() {
		noWinner = !gameBoard.checkForWinner();
	}
}
