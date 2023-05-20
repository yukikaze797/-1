package Model.Chess;

import Model.Animal.Animals;
import Model.Animal.Person;

public class ChessBoard {
    private Cell [][] GameBoard= new Cell[9][7];
    public ChessBoard(Person RED, Person BLUE){
        //填数组
        for(int i=0;i<9;i++){
            for(int j=0;j<7;j++){
                GameBoard[i][j]=new LandCell();
                }
        //RIVERCELL
        GameBoard [1][3] = new RiverCell();
        GameBoard [2][3] = new RiverCell();
        GameBoard [1][4] = new RiverCell();
        GameBoard [2][4] = new RiverCell();
        GameBoard [1][5] = new RiverCell();
        GameBoard [2][5] = new RiverCell();
        GameBoard [5][3] = new RiverCell();
        GameBoard [4][3] = new RiverCell();
        GameBoard [4][4] = new RiverCell();
        GameBoard [4][5] = new RiverCell();
        GameBoard [5][4] = new RiverCell();
        GameBoard [5][5] = new RiverCell();
        //TRACKCELL
        GameBoard [2][0] = new TrapCell(-1);
        GameBoard [4][0] = new TrapCell(-1);
        GameBoard [3][1] = new TrapCell(-1);
        GameBoard [2][8] = new TrapCell(1);
        GameBoard [4][8] = new TrapCell(1);
        GameBoard [3][7] = new TrapCell(1);
        //HOMECELL
        GameBoard [3][0] = new TrapCell(-1);
        GameBoard [3][8] = new TrapCell(1);
    }

    public void setGameBoard(Cell[][] gameBoard) {
        GameBoard = gameBoard;
    }

    public Cell[][] getGameBoard() {
        return GameBoard;
    }
    public void setCell(int row, int col, Animals animals){
        GameBoard [row][col] .setAnimal(animals);
    }
}
