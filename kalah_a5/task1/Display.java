package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class Display {
    private Board board;
    private IO io;

    private ArrayList<Player> players = new ArrayList<Player>();

    public Display(Board board, IO io){
        this.io = io;
        this.board = board;
        this.players = this.board.getPlayers();
    }

    public void displayBoard() {
        CircularArrayList<Pit>pits = board.getPits();
        io.println("+---------------+");
        io.println(String.format("|       | P%d %2d |", 2, pits.get(pits.size() -1).getSeeds()));
        io.println("+-------+-------+");
        for (int i = 0; i < 6; i++) {
            io.println(String.format("| %d[%2d] | %d[%2d] |", i+1, pits.get(i).getSeeds(), 6-i, pits.get(pits.size() - (2+i)).getSeeds()));
        }
        io.println("+-------+-------+");
        io.println(String.format("| P%d %2d |       |", 1,  pits.get(6).getSeeds()));
        io.println("+---------------+");
    }

    public void displayScore(){
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

    public void gameOver(){
        io.println("Game over");
    }

    public void displayInvalidMessage(){
        io.println("House is empty. Move again.");
    }

    public  String getPlayerMove(int playerIndex){
        return io.readFromKeyboard("Player P" + players.get(playerIndex).getPlayerID() + "'s turn - Specify house number or 'q' to quit: ");
    }
}
