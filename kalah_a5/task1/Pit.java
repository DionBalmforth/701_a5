package kalah;

public class Pit {
    enum Type {
        STORE,
        HOUSE
    }

    protected int seeds;
    protected Type type;

    public Pit(int seeds){
        this.seeds = seeds;
    }

    public int getSeeds(){
        return this.seeds;
    }

    public void addSeed(int newSeeds){
        this.seeds += newSeeds;
    }

    public Type getType(){
        return this.type;
    }
}
