package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;


public class DisplayGameInformation implements IDisplayGameInformation{
    private IO io;
    private Board board;
    private ArrayList<Player> players;

    public DisplayGameInformation(Board board){
        this.board = board;
        setPlayers();
    }

    public void printScore(){
        int holdPlayerScore1 = board.getScoreForPlayer(0);
        int holdPlayerScore2 = board.getScoreForPlayer(1);

        io.println("\tplayer " + players.get(0).getPlayerID() + ":" + holdPlayerScore1);
        io.println("\tplayer " + players.get(1).getPlayerID() + ":" + holdPlayerScore2);
        if (holdPlayerScore1 > holdPlayerScore2){
            io.println("Player 1 wins!");
        }else if (holdPlayerScore1 < holdPlayerScore2) {
            io.println("Player 2 wins!");
        }else{
            io.println("A tie!");
        }
    }

    public void printGameOver(){
        io.println("Game over");
    }

    public void printInvalidMessage(){
        io.println("House is empty. Move again.");
    }

    public void setIO(IO io){
        this.io = io;
    }

    private void setPlayers(){
        this.players = board.getPlayers();
    }
}
