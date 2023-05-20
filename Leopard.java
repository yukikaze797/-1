package Model.Animal;

public class Leopard extends Animals {
    public Leopard(int number){
        this.setRank(5*number);
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
