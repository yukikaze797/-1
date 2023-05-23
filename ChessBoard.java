package Model.Chess;

import Model.Animal.*;

public class ChessBoard {
    private Cell [][] GameBoard= new Cell[9][7];
    public ChessBoard(Person RED, Person BLUE){
        //棋盘创建，红方在上，蓝方在下
        for(int i=0;i<9;i++){
            for(int j=0;j<7;j++){
                GameBoard[i][j]=new LandCell();
            }}
        //RIVERCELL
        GameBoard [3][1] = new RiverCell();
        GameBoard [3][2] = new RiverCell();
        GameBoard [3][4] = new RiverCell();
        GameBoard [3][5] = new RiverCell();
        GameBoard [4][1] = new RiverCell();
        GameBoard [4][2] = new RiverCell();
        GameBoard [4][4] = new RiverCell();
        GameBoard [4][5] = new RiverCell();
        GameBoard [5][1] = new RiverCell();
        GameBoard [5][2] = new RiverCell();
        GameBoard [5][4] = new RiverCell();
        GameBoard [5][5] = new RiverCell();
        //TRAPCELL
        GameBoard [0][2] = new TrapCell(-1);
        GameBoard [0][4] = new TrapCell(-1);
        GameBoard [1][3] = new TrapCell(-1);
        GameBoard [7][3] = new TrapCell(1);
        GameBoard [8][2] = new TrapCell(1);
        GameBoard [8][4] = new TrapCell(1);
        //HOMECELL
        GameBoard [0][3] = new HomeCell(-1);
        GameBoard [8][3] = new HomeCell(1);
        //设置棋子
        //红方
        GameBoard [0][0].setAnimal(new Lion(-1));
        GameBoard [0][6].setAnimal(new Tiger(-1));
        GameBoard [1][1].setAnimal(new Dog(-1));
        GameBoard [1][5].setAnimal(new Cat(-1));
        GameBoard [2][0].setAnimal(new Rat(-1));
        GameBoard [2][2].setAnimal(new Leopard(-1));
        GameBoard [2][4].setAnimal(new Wolf(-1));
        GameBoard [2][6].setAnimal(new Elephant(-1));
        //蓝方
        GameBoard [8][6].setAnimal(new Lion(1));
        GameBoard [8][0].setAnimal(new Tiger(1));
        GameBoard [7][5].setAnimal(new Dog(1));
        GameBoard [7][1].setAnimal(new Cat(1));
        GameBoard [6][6].setAnimal(new Rat(1));
        GameBoard [6][4].setAnimal(new Leopard(1));
        GameBoard [6][2].setAnimal(new Wolf(1));
        GameBoard [6][0].setAnimal(new Elephant(1));
    }
    public void setGameBoard(Cell[][] gameBoard) {
        GameBoard = gameBoard;
    }

    public Cell[][] getGameBoard() {
        return GameBoard;
    }
    public void setCellAnimal(int row, int col, Animals animals){
        GameBoard [row][col] .setAnimal(animals);
    }
    //红方剩余棋子
    public int RedAnimal(){
        int count = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0;j<7;j++){
                if(GameBoard[i][j].isHasChess() && GameBoard[i][j].getAnimal().getRank()<0){
                    count +=1;
                }else if(GameBoard[i][j].isHasChess() &&GameBoard[i][j].getAnimal().getRank()==0 && GameBoard[i][j].getBelong()==1){
                    count +=1;
                }else{
                }
            }
        }
        return count;
    }
    //蓝方剩余棋子
    public int BlueAnimal(){
        int count = 0;
        for(int i = 0;i<9;i++){
            for(int j = 0;j<7;j++){
                if(GameBoard[i][j].isHasChess() && GameBoard[i][j].getAnimal().getRank()>0){
                    count +=1;
                }else if(GameBoard[i][j].isHasChess() && GameBoard[i][j].getAnimal().getRank()==0 && GameBoard[i][j].getBelong()==-1){
                    count +=1;
                }else{
                }
            }
        }
        return count;
    }
//胜负判断
    public int WinTest(){
        if(GameBoard[0][3].isHasChess() && GameBoard[0][3].getAnimal().getRank() > 0){
        return 1;
        }else if(GameBoard[8][3].isHasChess() && GameBoard[8][3].getAnimal().getRank() < 0){
        return -1;
        } else if (this.RedAnimal()==0) {
            return 1;
        }else if(this.BlueAnimal()==0){
            return -1;
        }else{
            return 0;
        }
    }
}
