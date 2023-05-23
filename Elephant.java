package Model.Animal;

public class Elephant extends Animals {
    public Elephant(int number){
        this.setName("elephant")
        this.setRank(8*number);
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
