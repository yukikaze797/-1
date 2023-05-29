package Model.Chess;

import Model.Animal.*;

import javax.swing.*;
import java.io.*;

public class ChessBoard {
    //当前走棋方
    //回合数，偶数蓝方，奇数红方
    private int turns=0;
    private Cell [][] GameBoard= new Cell[9][7];
    //设置有棋子位置的棋盘
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
    //无棋子的棋盘
    public ChessBoard(){
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
    public void setRankOfAnimal(int row,int col,int rank){
        GameBoard[row][col].getAnimal().setRank(rank);
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
    //以rank存档的方法
    public String SaveRanks(){
        int [][] Belongs=new int[9][7];
        File file = new File("src/main/java/resource/save.txt");
        for(int i=0;i<9;i++){
            for(int j=0;j<7;j++){
                if(GameBoard[i][j].isHasChess()){
                    //记录每点的信息，rank=0 仅出现在陷阱格，读存档时再特殊处理
                    Belongs[i][j]=GameBoard[i][j].getAnimal().getRank();
                }else{
                    //空格子记录为10
                    Belongs[i][j]=10;
                }
            }
        }
        //写为字符文本
        String str = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                str += Belongs[i][j] + ",";
            }
            str += "\n";
        }
        return str;
    }
    //储存放在陷阱格的棋子，棋盘当前走棋方也存在这个文件
    public String SaveTrap() {
        //6个陷阱格里动物的名字
        String[] str = new String[6];
        //6个陷阱格+走棋方
        int[] rank = new int[7];
        String data = "";
        //
        if (GameBoard[0][2].getAnimal() == null) {
            str[0] = "null";
        } else {
            str[0] = GameBoard[0][2].getAnimal().getName();
        }
        //
        if (GameBoard[0][4].getAnimal() == null) {
            str[1] = "null";
        } else {
            str[1] = GameBoard[0][4].getAnimal().getName();
        }
        if (GameBoard[1][3].getAnimal() == null) {
            str[2] = "null";
        } else {
            str[2] = GameBoard[1][3].getAnimal().getName();
        }
        if (GameBoard[6][3].getAnimal() == null) {
            str[3] = "null";
        } else {
            str[3] = GameBoard[7][3].getAnimal().getName();
        }
        if (GameBoard[8][2].getAnimal() == null) {
            str[4] = "null";
        } else {
            str[4] = GameBoard[8][2].getAnimal().getName();
        }
        if (GameBoard[8][4].getAnimal() == null) {
            str[5] = "null";
        } else {
            str[5] = GameBoard[8][4].getAnimal().getName();
        }

//陷阱6格
        for (int i = 0; i < 6; i++) {
            if(str[i].equals("rat")){
                    rank[i] = 1;
            }else if(str[i].equals("cat")){
                    rank[i] = 2;
            }else if(str[i].equals("dog")){
                    rank[i] = 3;
            }else if(str[i].equals("wolf")){
                rank[i] = 4;
            }else if(str[i].equals("leopard")){
                rank[i] = 5;
            }else if(str[i].equals("tiger")){
                rank[i] = 6;
            }else if(str[i].equals("lion")){
                rank[i] = 7;
            }else if(str[i].equals("elephant")){
                rank[i] = 8;
            }/*else if(str[i].equals("null")){
                rank[i] = 10;
            }*/else {
                rank[i]=0;
            }
        }
        for(int i=0;i<6;i++){
            //后三个，即蓝方陷阱中的rank=0的动物必为红方棋子
            if (i > 2) {
                rank[i] = -1 * rank[i];
                data += rank[i] + ",";
            }else{
                data += rank[i] + ",";
            }
        }
        data +=turns + ",";
        return data;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int Turns) {
        turns=Turns;
    }
    public static void writeBoardToFile(String rank,String trap, String filePath) {
        // 创建一个JFileChooser对象
        JFileChooser fileChooser = new JFileChooser();

        // 设置文件对话框的标题
        fileChooser.setDialogTitle("选择文件保存位置");

        // 显示文件对话框并获取用户选择结果
        int userSelection = fileChooser.showSaveDialog(null);

        // 如果用户点击了保存按钮
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // 获取用户选择的文件
            File fileToSave = fileChooser.getSelectedFile();

            // 使用try-with-resources语句创建文件写入器和缓冲写入器
            try (FileWriter fw = new FileWriter(fileToSave);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                // 将数组写入文件
                    bw.write(String.valueOf(rank+"\n"+trap));

                System.out.println("棋盘已成功写入文件：" + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
