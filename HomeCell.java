package Model.Chess;

public class HomeCell extends Cell{
    public HomeCell(int belong){
        this.setBelong(belong);
        setType(CellType.HOME);
    }
}
