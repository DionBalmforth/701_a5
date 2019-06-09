package kalah;

public class Player {
    private int id;
    private int score = 0;

    public Player(int id){
        this.id = id;
    }

    public int getPlayerID(){
        return this.id;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }
}
