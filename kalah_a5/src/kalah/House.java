package kalah;

public class House extends Pit{
    private int houseNum;

    public House(int seeds, int houseNum){
        super(seeds);

        this.houseNum = houseNum;
        this.type = Type.HOUSE;

    }

    public int getHouseNum(){
        return  this.houseNum;
    }

    public int removeSeeds(){
        int holdSeeds = this.seeds;
        this.seeds = 0;
        return holdSeeds;
    }
}
