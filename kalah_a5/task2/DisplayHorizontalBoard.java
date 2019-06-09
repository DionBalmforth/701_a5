package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class DisplayHorizontalBoard implements IDisplayBoard{
    private IO io;
    private Board board;
    private int housesPerPlayer;
    private ArrayList<Player> players;

    public DisplayHorizontalBoard(Board board){
        this.board = board;
        housesPerPlayer = board.getHousesPerPlayer();
        setPlayers();
    }

    public void printBoard(){
        printCaseBreak();
        printTopStreet();
        printCenterBreak();
        printBottomStreet();
        printCaseBreak();
    }

    public void setIO(IO io){
        this.io = io;
    }

    private void setPlayers(){
        this.players = board.getPlayers();
    }

    private void printCaseBreak(){
        String localString = "+----+";
        for (int i = 0; i < housesPerPlayer; i++){
            if (i != 0 && i != housesPerPlayer){
                localString += "+";
            }
            localString += "-------";
        }
        localString += "+----+";

        io.println(localString);
    }

    private void printCenterBreak(){
        String localString = "|    |";
        for (int i = 0; i < housesPerPlayer; i++){
            if (i != 0 && i != housesPerPlayer){
                localString += "+";
            }
            localString += "-------";
        }
        localString += "|    |";

        io.println(localString);
    }

    private void printTopStreet(){
        String localString = String.format("| P%d ", players.get(1).getPlayerID());
        for (int i = 0; i < housesPerPlayer; i++){
            localString += String.format("| %d[%2d] ", housesPerPlayer-i, board.getHouseSeeds(players.get(1), housesPerPlayer - (1 + i)));
        }
        localString += String.format("| %2d |", board.getStoreSeeds(players.get(0)));

        io.println(localString);
    }

    private void printBottomStreet(){
        String localString = String.format("| %2d ", board.getStoreSeeds(players.get(1)));
        for (int i = 0; i < housesPerPlayer; i++){
            localString += String.format("| %d[%2d] ", i+1, board.getHouseSeeds(players.get(0), i));
        }
        localString += String.format("| P%d |", players.get(0).getPlayerID());

        io.println(localString);
    }
}
