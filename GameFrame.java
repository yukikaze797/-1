package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;
import Model.Animal.*;
import Model.Chess.Cell;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

public class GameFrame extends JFrame {
    private int saveTest;
    public ChessBoard save = new ChessBoard(new Person(-1),new Person(1));;//已经有的存档,savTest=0使用默认存档
    public ChessBoard oriSave = new ChessBoard(new Person(-1),new Person(1));//默认存档

    public ChessBoard getOriSave() {
        return oriSave;
    }

    public ChessBoard getSave() {
        return save;
    }

    public int getSaveTest() {
        return saveTest;
    }

    public void setOriSave(ChessBoard oriSave) {
        this.oriSave = oriSave;
    }

    public void setSaveTest(int saveTest) {
        this.saveTest = saveTest;
    }

    public void setSave(ChessBoard save) {
        this.save = save;
    }

    //游戏最大的结构的构造器
    public GameFrame(ChessBoard chessBoard) {
        JFrame GameBoard = new JFrame("斗兽棋");
        GameBoard.setSize(800, 800);
        if (save == null) {
            saveTest = 0;
        } else {
            saveTest = 1;
        }
        GameBoard.setContentPane(new MainMenu(saveTest,this));
        GameBoard.setVisible(true);
    }

    public static class MainMenu extends JPanel {
        private GameFrame gameFrame;
        private JButton Start = new JButton("开始游戏");
        private JButton Exit = new JButton("退出游戏");
        private JButton Continue = new JButton("继续游戏");
        private JButton Load = new JButton("读取存档");


        //具体的主菜单 0为无存档时的界面，1为有存档时的界面
        public MainMenu(int number,GameFrame gameFrame) {
            this.gameFrame = gameFrame;
            if (number == 0) {
                this.add(Start);
                Start.addActionListener(new ButtonListener());
                Start.setActionCommand("start");
                this.add(Exit);
                Exit.addActionListener(new ButtonListener());
                Start.setActionCommand("exit");
            } else {
                this.add(Start);
                Start.addActionListener(new ButtonListener());
                Start.setActionCommand("start");
                this.add(Continue);
                Continue.addActionListener(new ButtonListener());
                Continue.setActionCommand("continue");
                this.add(Load);
                Load.addActionListener(new ButtonListener());
                Load.setActionCommand("load");
                this.add(Exit);
                Exit.addActionListener(new ButtonListener());
                Exit.setActionCommand("exit");
            }
        }

        public class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("start")) {
                    JFrame playFrame = new PlayFrame(gameFrame);
                    System.out.println("start");
                    Window windowS=SwingUtilities.getWindowAncestor(MainMenu.this);
                    windowS.dispose();
                    //
                    //
                }else if(command.equals("exit")){
                        Window windowE = SwingUtilities.getWindowAncestor(MainMenu.this);
                        if (windowE instanceof JFrame) {
                            JFrame frame = (JFrame) windowE;
                            frame.dispose();
                        }
                        System.out.println("exit");
                        //
                    //
                }else if(command.equals("continue")){
                       GameFrame frameC = gameFrame;
                       ChessBoard board = frameC.getSaveData();
                       if(board==null){
                       } else {
                        PlayFrame playFrameC =new PlayFrame(gameFrame);
                        frameC.dispose();
                        playFrameC.setVisible(true);
                       }
                       System.out.println("continue");
                       //
                    //
                }else if(command.equals("load")){
                    System.out.println("load");
                                JFileChooser fileChooser = new JFileChooser();
                                int result = fileChooser.showOpenDialog(null);
                                if (result == JFileChooser.APPROVE_OPTION) {
                                    File selectedFile = fileChooser.getSelectedFile();
                                    try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                                        String line;
                                        while ((line = reader.readLine()) != null) {
                                            System.out.println(line);
                                        }
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                    ChessBoard boardL=LoadSave(selectedFile);
                                    if(boardL!=null){
                                        gameFrame.setSave(boardL);
                                        PlayFrame LoadPlay=new PlayFrame(gameFrame);

                                    gameFrame.dispose();
                Window windowS = SwingUtilities.getWindowAncestor(MainMenu.this);
                if (windowS instanceof PlayFrame) {
                    JFrame frame = (JFrame) windowS;
                    frame =(PlayFrame) frame;
                    frame.dispose();
                }
                }else{
                           JFrame error = new JFrame();
                           error.setTitle("存档错误");
                           error.setSize(700, 100);
                           JLabel hint =new JLabel("错误的存档格式或存档内容");
                           hint.setFont(new Font("错误的存档格式或存档内容",Font.BOLD,32));
                           error.add(hint);
                           error.setVisible(true);
   }
}
            }
        }
    }}
    //读存档
    public static ChessBoard LoadSave(File file){
        //无棋子的棋盘
      ChessBoard board = new ChessBoard();
      //数字棋盘
      int[][] arr = new int[10][7];
      //用于检测最后生成的棋盘是否合法
        ArrayList<Animals> BlueAnimalTest = new ArrayList<>();
        ArrayList<Animals> RedAnimals = new ArrayList<>();
        //棋盘上的棋子以对应rank记录
        //非陷阱棋子文本文件转为数组
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()){continue;}else{
                String[] values = line.split(",");
                if(values.length!=7){return null;}
                for (int j = 0; j < values.length; j++) {
                    arr[i][j] = Integer.parseInt(values[j]);
                }
                i++;
            }
            }
            reader.close();
        } catch (FileNotFoundException e){
           return null;
        } catch (IOException e) {
            return null;
        }catch (NumberFormatException e){
            return null;
        }
        //收集陷阱格的棋子
            //陷阱格6格，走棋方占一个储存位置
            Animals[] TrapAnimal =new Animals[6];
        //获取陷阱格动物的信息
            for(int i =0;i<arr[9].length;i++){
                if(i<6){
                switch (arr[9][i]){
                    case 0:
                        continue;
                    case 1:
                        TrapAnimal[i]=new Rat(1);
                        break;
                    case 2:
                        TrapAnimal[i]=new Cat(1);
                        break;
                    case 3:
                        TrapAnimal[i]=new Dog(1);
                        break;
                    case 4:
                        TrapAnimal[i]=new Wolf(1);
                        break;
                    case 5:
                        TrapAnimal[i]=new Leopard(1);
                        break;
                    case 6:
                        TrapAnimal[i]=new Tiger(1);
                        break;
                    case 7:
                        TrapAnimal[i]=new Lion(1);
                        break;
                    case 8:
                        TrapAnimal[i]=new Elephant(1);
                        break;
                    case -1:
                        TrapAnimal[i]=new Rat(-1);
                        break;
                    case -2:
                        TrapAnimal[i]=new Cat(-1);
                        break;
                    case -3:
                        TrapAnimal[i]=new Dog(-1);
                        break;
                    case -4:
                        TrapAnimal[i]=new Wolf(-1);
                        break;
                    case -5:
                        TrapAnimal[i]=new Leopard(-1);
                        break;
                    case -6:
                        TrapAnimal[i]=new Tiger(-1);
                        break;
                    case -7:
                        TrapAnimal[i]=new Lion(-1);
                        break;
                    case -8:
                        TrapAnimal[i]=new Elephant(-1);
                        break;
                    default:
                        continue;
                }
             }else{
                    board.setTurns(arr[9][6]);
                }
            }
            //在陷阱格放置动物
            for(int i=0;i<TrapAnimal.length-1;i++){
                if(TrapAnimal[i]!=null){
                if(TrapAnimal[i].getRank()<0){
                    RedAnimals.add(TrapAnimal[i]);
                }else{
                    BlueAnimalTest.add(TrapAnimal[i]);
                }
                TrapAnimal[i].setRank(0);
                if(i == 0){
                    board.setCellAnimal(0,2,TrapAnimal[i]);
                } else if (i==1) {
                    board.setCellAnimal(0,4,TrapAnimal[i]);
                }else if(i==2){
                    board.setCellAnimal(1,3,TrapAnimal[i]);
                }else if(i==3){
                    board.setCellAnimal(7,3,TrapAnimal[i]);
                }else  if(i==4){
                    board.setCellAnimal(8,2,TrapAnimal[i]);
                }else {
                    board.setCellAnimal(8,4,TrapAnimal[i]);
                }
                }
            }
        //整理棋盘，放置非陷阱格棋子
        for(int i =0;i<9;i++){
            for(int j=0;j<7;j++){
                switch (arr[i][j]){
                    case 10:
                        continue;
                    case -8:
                        board.setCellAnimal(i,j,new Elephant(-1));
                        break;
                    case -7:
                        board.setCellAnimal(i,j,new Lion(-1));
                        break;
                    case -6:
                        board.setCellAnimal(i,j,new Tiger(-1));
                        break;
                    case -5:
                        board.setCellAnimal(i,j,new Leopard(-1));
                        break;
                    case -4:
                        board.setCellAnimal(i,j,new Wolf(-1));
                        break;
                    case -3:
                        board.setCellAnimal(i,j,new Dog(-1));
                        break;
                    case -2:
                        board.setCellAnimal(i,j,new Cat(-1));
                        break;
                    case -1:
                        board.setCellAnimal(i,j,new Rat(-1));
                        break;
                    case 8:
                        board.setCellAnimal(i,j,new Elephant(1));
                        break;
                    case 7:
                        board.setCellAnimal(i,j,new Lion(1));
                        break;
                    case 6:
                        board.setCellAnimal(i,j,new Tiger(1));
                        break;
                    case 5:
                        board.setCellAnimal(i,j,new Leopard(1));
                        break;
                    case 4:
                        board.setCellAnimal(i,j,new Wolf(1));
                        break;
                    case 3:
                        board.setCellAnimal(i,j,new Dog(1));
                        break;
                    case 2:
                        board.setCellAnimal(i,j,new Cat(1));
                        break;
                    case 1:
                        board.setCellAnimal(i,j,new Rat(1));
                        break;
                    case 0:
                      continue;
                }
                if(board.getGameBoard()[i][j].getAnimal().getRank()<0){
                    RedAnimals.add(board.getGameBoard()[i][j].getAnimal());
                }else if(board.getGameBoard()[i][j].getAnimal().getRank()>0){
                    BlueAnimalTest.add(board.getGameBoard()[i][j].getAnimal());
                }
            }
        }
        if(RedAnimals.size()>8|BlueAnimalTest.size()>8){
            return null;
        }
        //检测红方是否存在重名棋子
        for(int i =0;i < RedAnimals.size();i++){
            for (int j=i+1;j<RedAnimals.size();j++){
                if(RedAnimals.get(i).equals(RedAnimals.get(j))){
                    return null;
                }
            }
        }
        //检测蓝方是否存在重名棋子
        for(int i =0;i < BlueAnimalTest.size();i++){
            for (int j=i+1;j<BlueAnimalTest.size();j++){
                if(BlueAnimalTest.get(i).equals(BlueAnimalTest.get(j))){
                    return null;
                }
            }
        }
        //检测河流内的格子
        for(int i =3;i < 6;i++){
            for(int j =1; j<6;j++){
                if(j!=3){
                    if( board.getGameBoard()[i][j].getAnimal()!=null && Math.abs(board.getGameBoard()[i][j].getAnimal().getRank())!=1){
                        return null;
                    }
                }
            }
        }

return board;
        }
        public ChessBoard getSaveData(){
        return save;
        }
        //存档测试


    }
