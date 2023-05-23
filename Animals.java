package Model.Animal;

public class Animals {
    private int rank;
    private String name;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
     public String getName() {
        if(this == null){
            return "null";
        }else{
        return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
