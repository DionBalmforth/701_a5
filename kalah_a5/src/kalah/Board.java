package kalah;

import java.util.HashMap; // import the HashMap class
import java.util.Map; // import the HashMap class
import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class Board {
    private IO io;
    private int seeds = 4;
    private Player playerTurn;
    private int quantityOfHouses = 6;
    private Map<Player, Integer> playerMap = new HashMap<>();
    private CircularArrayList<Pit> playerPits = new CircularArrayList<Pit>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private GameState currentState;

    public Board(IO io, ArrayList<Player> players) {
        this.io = io;
        this.players = players;

        buildPits(players);
    }

    public GameState play(int houseMove){
        playerMove(houseMove);
        updateScores();

        return this.currentState;
    }

    public void UpdatePlayer(Player playerTurn){
        this.playerTurn = playerTurn;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public int playerHouseNumber(){
        return this.quantityOfHouses;
    }

    public int getScoreForPlayer(int index){
        return players.get(index).getScore();
    }

    public boolean gameOverCheck(){

        for (int i = playerMap.get(playerTurn); i < (playerMap.get(playerTurn) + quantityOfHouses); i++) {
            if (playerPits.get(i).getSeeds() != 0) {
                return false;
            } else if (i == (playerMap.get(playerTurn) + quantityOfHouses) - 1) {
                this.currentState = GameState.GameOver;
                return true;
            }
        }
        return false;
    }

    //return all player pits
    public CircularArrayList<Pit> getPits(){
        return playerPits;
    }

    private void updateScores(){
        players.get(0).setScore(getScore(players.get(0)));
        players.get(1).setScore(getScore(players.get(1)));
    }

    private int getScore(Player playerToCheck){
        int holdScore = 0;
        for (int i = playerMap.get(playerToCheck); i < playerMap.get(playerToCheck) + quantityOfHouses +1; i++){
            holdScore += playerPits.get(i).getSeeds();
        }
        return holdScore;
    }

    private void buildPits(ArrayList<Player> players){
        for (int x = 0; x < players.size(); x++) {
            this.playerMap.put(players.get(x), playerPits.size());
            for (int i = 0; i < this.quantityOfHouses; i++) {
                this.playerPits.add(new House(this.seeds, i + 1));
            }
            this.playerPits.add(new Store(0));
        }
    }

    private void playerMove(int houseMove){
        int holdSeeds;
        int index = houseMove - 1 + this.playerMap.get(this.playerTurn);

        if (playerPits.get(index).getSeeds() == 0){
            this.currentState = GameState.InvalidMove;
            return;
        }

        //get number of seeds and next index in array
        if (playerPits.get(index).getType() == Pit.Type.HOUSE && index < playerPits.size()) {
            holdSeeds = ((House) playerPits.get(index)).removeSeeds();
            index++;

        }else {
            return;
        }

        //disperse players seed
        while (holdSeeds > 0) {
            if (canDropSeed(index)) {
                playerPits.get(index).addSeed(1);
                holdSeeds--;
            }
            index++;
        }

        //set to last index a seed was dropped at
        index--;

        setState(index % playerPits.size());

        return;
    }

    private boolean canDropSeed(int index){
        int holdIndex = index % playerPits.size();

        if(playerPits.get(holdIndex).getType() == Pit.Type.HOUSE){
            return true;
        }else if (this.playerMap.get(playerTurn) <= holdIndex &&
                holdIndex <= this.playerMap.get(playerTurn) + this.quantityOfHouses){ //check if current players store
            return true;
        }else {
            return false;
        }
    }

    private void setState(int lastIndex) {
        Pit holdPit = playerPits.get(lastIndex);
        int oppositeSeedIndex = lastIndex + ((12) - ((lastIndex - playerMap.get(playerTurn)) % quantityOfHouses) * 2);

        //check the seed on the opposite row
        int checkSeed  = playerPits.get(oppositeSeedIndex).getSeeds();

        //check if landed on a store
        if (holdPit.getType() == Pit.Type.STORE) {
            this.currentState = GameState.ContinueTurn;
            return;
        } else if (playerMap.get(playerTurn) <= lastIndex % playerPits.size() &&
                lastIndex % playerPits.size() <= playerMap.get(playerTurn) + quantityOfHouses) {
            if (playerPits.get(lastIndex).getSeeds() == 1 && checkSeed != 0) {
                //if last move was on their empty house move current and opposite seeds to store

                int holdSeed = ((House) playerPits.get(lastIndex)).removeSeeds();
                holdSeed += ((House) playerPits.get(oppositeSeedIndex)).removeSeeds();
                playerPits.get(playerMap.get(playerTurn) + quantityOfHouses).addSeed(holdSeed);
            }
        }

        this.currentState = GameState.TurnEnded;
    }
}
