package Model.Animal;

public class Dog extends Animals {
    public Dog(int number){
        this.setRank(3*number);
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
