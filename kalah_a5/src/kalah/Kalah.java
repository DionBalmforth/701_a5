package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.ArrayList;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private ArrayList<Player> players = new ArrayList<Player>();

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		GameState playerState = GameState.ContinueTurn;
		String input;
		int playerIndex = 0;

		players.add(new Player(1));
		players.add(new Player(2));

		//build playing board
		Board playingBoard = new Board(io, players);
		Display display = new Display(playingBoard, io);

		playingBoard.UpdatePlayer(players.get(playerIndex));

		while(true){
			//display board and first turn message
			display.displayBoard();

			while(true) {
				try {
					input = display.getPlayerMove(playerIndex);
					break;
				} catch (Exception E) {
					display.displayInvalidMessage();
				}
			}
			//quit if q
			if (input.equals("q")){
				display.gameOver();
				display.displayBoard();
				break;
			}

			//play round
			playerState = playingBoard.play(Integer.parseInt(input));

			if (playerState == GameState.TurnEnded){
				playerIndex = (playerIndex == 0)? 1: 0;
				playingBoard.UpdatePlayer(players.get(playerIndex));
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
