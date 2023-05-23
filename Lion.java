package Model.Animal;

public class Lion extends Animals {
    public Lion(int number){
        this.setName("lion")
        this.setRank(7*number);
    }
    @Override
    public int getRank() {
        return super.getRank();
    }

    @Override
    public void setRank(int rank) {
        super.setRank(rank);
    }
}
