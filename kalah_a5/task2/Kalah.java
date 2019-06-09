package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.ArrayList;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private ArrayList<Player> players = new ArrayList<kalah.Player>();

	public static void main(String[] args) {
		new Kalah().play(new MockIO());

	}
	public void play(IO io) {
		GameState playerState = GameState.ContinueTurn;
		String userResponse;
		int playerID = 1;

		players.add(new Player(1));
		players.add(new Player(2));

		//build playing board
		Board playingBoard = new Board(io, players);

		//building display
		Display display = new Display(io);
		display.setBoardDisplay(new DisplayHorizontalBoard(playingBoard));
		display.setGameInformation(new DisplayGameInformation(playingBoard));

		//build
		Input input = new Input(io);


		playingBoard.UpdatePlayer(players.get(playerID - 1));

		while(true){
			//display board and first turn message
			display.displayBoard();

			while(true) {
				try {
					userResponse = input.getPlayerInput(playerID);
					break;
				} catch (Exception E) {
					io.println("" + E);
					display.displayInvalidMessage();
				}
			}
			//quit if q
			if (userResponse.equals("q")){
				display.gameOver();
				display.displayBoard();
				break;
			}

			//play round
			playerState = playingBoard.play(Integer.parseInt(userResponse));

			if (playerState == GameState.TurnEnded){
				playerID = (playerID == 1)? 2: 1;
				playingBoard.UpdatePlayer(players.get(playerID -1));
			}else if (playerState == GameState.InvalidMove){
				display.displayInvalidMessage();
			}

			if(playingBoard.gameOverCheck()){
				display.displayBoard();
				display.gameOver();
				display.displayBoard();
				display.displayScore();
				break;
			}
		}
	}
}
