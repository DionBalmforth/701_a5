package main;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class DisplayVerticalBoard implements IDisplayBoard{
    private IO io;
    private Board board;
    private int housesPerPlayer;
    private ArrayList<Player> players;

    public DisplayVerticalBoard(Board board){
        this.board = board;
        housesPerPlayer = board.getHousesPerPlayer();
        setPlayers();
    }

    public void printBoard(){
        printBreak();
        printStreet();
        printBreak();
    }

    public void setIO(IO io){
        this.io = io;
    }

    private void setPlayers(){
        this.players = board.getPlayers();
    }

    private void printBreak(){
        io.println("+---------------+");
    }

    private void printStreet(){

        io.println(String.format("|       | P%d %2d |",
                players.get(1).getPlayerID(),
                board.getStoreSeeds(players.get(1))));
        printBreak();

        for (int i = 0; i < housesPerPlayer; i++) {
            io.println(String.format("| %d[%2d] | %d[%2d] |",
                    i+1,
                    board.getHouseSeeds(players.get(0), i),
                    housesPerPlayer-i,
                    board.getHouseSeeds(players.get(1), housesPerPlayer - (1 + i))));
        }

        printBreak();
        io.println(String.format("| P%d %2d |       |",
                players.get(0).getPlayerID(),
                board.getStoreSeeds(players.get(0))));
    }
}
