package Model.Animal;

public class Cat extends Animals {
    public Cat(int nuber){
        this.setRank(2);
    }
    public int getRank() {
        return super.getRank();
    }

    @Override
    public void setRank(int rank) {
        super.setRank(rank);
    }
}
