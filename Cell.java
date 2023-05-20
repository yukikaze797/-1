package Model.Chess;

import Model.Animal.Animals;

public class Cell {
    private boolean HasChess;//有无棋子
    private Animals animal;//该格子上棋子的种类，没有则为空
    private CellType type;//格子的种类
    private int belong;//格子所属阵营 0为中立，1为红方，-1为蓝方

    public void setHasChess(boolean hasChess) {
        HasChess = hasChess;
    }

    public boolean isHasChess() {
        return HasChess;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public CellType getColor() {
        return type;
    }

    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }
    public void setAnimals(Animals animal){
        this.animal=animal;
    }
    public Animals getAnimals(){
        return this.animal;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }
}
