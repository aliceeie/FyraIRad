package Version4;

import java.util.Random;

public class FyraIRad{

	private DisplayWindow window;
	private final int boardWidth = 900;
	private final int boardHeight = 600;
	private Menu menu;
	private GameBoard gameBoard;
	private Highscore highscore;
	private STATE State;
	private boolean gameIsRunning = true;
//	private String[] highscoreNames;
//	private int[] highscoreMoves;

	public void start() {
		
		initializeGame();
		
		while(gameIsRunning) {
			
			if (State == STATE.MENU) {
				window.showNewComponent(menu, "Menu");					//Visar menu
				waitForNewState(menu);									//Ligger kvar här tills användaren valt i menu
			} 
			else if (State == STATE.GAME){
				//Skapar ett nytt gameBoard till ett nytt game
				gameBoard = new GameBoard(1, randomizeStartPlayer(), boardWidth, boardHeight);
				window.showNewComponent(gameBoard, "Two player game");				//Visar gameBoard
				waitForNewState(gameBoard);								//Ligger kvar i detta läge tills användaren exits med ESC
			} 
			else if(State == STATE.COMPGAME) {
				//Skapar ett nytt gameBoard till ett nytt game
				gameBoard = new GameBoard(2, randomizeStartPlayer(), boardWidth, boardHeight);
				window.showNewComponent(gameBoard, "Single player game");				//Visar gameBoard
				waitForNewState(gameBoard);								//Ligger kvar i detta läge tills användaren exits med ESC
			} 
			else if (State == STATE.HIGHSCORE) {
				window.showNewComponent(highscore, "Highscore");		//Visar highscore
				waitForNewState(highscore);								//Ligger kvar här tills användaren exits med ESC
			}
		}
		System.out.println("Spelet har avslutas.");	
	}
	
	private enum STATE {
		MENU, GAME, COMPGAME, HIGHSCORE				//Meny-, tv�spelare-, enspelare- eller highscorel�ge
	};
	
	private void initializeGame() {
		State = STATE.MENU;										//Startar i menylage
		window = new DisplayWindow(boardWidth, boardHeight);
		menu = new Menu(boardWidth, boardHeight);
		highscore = new Highscore(boardWidth, boardHeight);
	}
	
	private void waitForNewState(GameComponent currentState) {		//Alla STATE:s extends ("is a") GameComponent, kan ta emot vilket STATE som helst
		switch (currentState.getNewState()) {			//Hämtar data ifrån currentState:s (ex. menu, game osv.) metod getNewState
		case 0:
			State = STATE.MENU;
			break;
		case 1:
			State = STATE.GAME;
			break;
		case 2:
			State = STATE.COMPGAME;
			break;
		case 3:
			State = STATE.HIGHSCORE;
			break;
		default:
			System.out.println("Ojdå, något gick fel...");
			break;
		}
	}
	
	private Players randomizeStartPlayer() {
		Random randomizer = new Random();
		if(randomizer.nextBoolean()) {			//Slumpar mellan true och false
			return Players.player1;
		}
		return Players.player2;
	}

}
