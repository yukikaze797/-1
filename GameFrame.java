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
                    JFrame playFrame = new PlayFrame(gameFrame.getSave());
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
                        PlayFrame playFrameC =new PlayFrame(board);
                        frameC.dispose();
                        playFrameC.setVisible(true);
                       }
                       System.out.println("continue");
                       //
                    //现在Load功能有问题
                }else if(command.equals("load")){
                    System.out.println("load");
                        GameFrame frameL = gameFrame;
                        ChessBoard boardL = frameL.getSaveData();
                        if(boardL==null){
                        }else{
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
                                    gameFrame.dispose();
                        }
                Window windowS = SwingUtilities.getWindowAncestor(MainMenu.this);
                if (windowS instanceof PlayFrame) {
                    JFrame frame = (JFrame) windowS;
                    frame =(PlayFrame) frame;
                    frame.dispose();
                }
                }
            }
        }
    }}
    //读存档
    /*public ChessBoard LoadSave(File file){
        //无棋子的棋盘
      ChessBoard board = new ChessBoard();
      int[][] arr = new int[9][7];
      int [] rank2={-1,-1,-1,-1,-1,-1,1};
      //读取记录数字的存档
        //棋盘上的棋子以对应rank记录
        //非陷阱棋子文本文件转为数组
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int j = 0; j < values.length; j++) {
                    arr[i][j] = Integer.parseInt(values[j]);
                }
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e){
           return null;
        } catch (IOException e) {
            return null;
        }
        //收集陷阱格的棋子
        try{
            BufferedReader reader = new BufferedReader(new FileReader());//trap有7个数字
            if(reader.readLine()!=null){
            String[] strings= reader.readLine().split(",");
            reader.close();
            //陷阱格6格
            Animals[] TrapAnimal =new Animals[6];
            for(int i =0;i<strings.length;i++){
                rank2[i]=Integer.parseInt(strings[i]);
                if(i<6){
                switch (rank2[i]){
                    case 0:
                        continue;
                    case 1:
                        TrapAnimal[i]=new Rat(1);
                    case 2:
                        TrapAnimal[i]=new Cat(1);
                    case 3:
                        TrapAnimal[i]=new Dog(1);
                    case 4:
                        TrapAnimal[i]=new Wolf(1);
                    case 5:
                        TrapAnimal[i]=new Leopard(1);
                    case 6:
                        TrapAnimal[i]=new Tiger(1);
                    case 7:
                        TrapAnimal[i]=new Lion(1);
                    case 8:
                        TrapAnimal[i]=new Elephant(1);
                    case -1:
                        TrapAnimal[i]=new Rat(-1);
                    case -2:
                        TrapAnimal[i]=new Cat(-1);
                    case -3:
                        TrapAnimal[i]=new Dog(-1);
                    case -4:
                        TrapAnimal[i]=new Wolf(-1);
                    case -5:
                        TrapAnimal[i]=new Leopard(-1);
                    case -6:
                        TrapAnimal[i]=new Tiger(-1);
                    case -7:
                        TrapAnimal[i]=new Lion(-1);
                    case -8:
                        TrapAnimal[i]=new Elephant(-1);
                    default:
                        continue;
                }
             }else{
                    board.setPlayerCode(rank2[6]);
                }
       }
            for(int i=0;i<TrapAnimal.length-1;i++){
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
            }}else{
                reader.close();
                return  new ChessBoard(new Person(-1),new Person(1));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //整理棋盘，放置非陷阱格棋子
        for(int i =0;i<9;i++){
            for(int j=0;j<7;j++){
                switch (arr[i][j]){
                    case 10:
                        continue;
                    case -8:
                        board.setCellAnimal(i,j,new Elephant(-1));
                    case -7:
                        board.setCellAnimal(i,j,new Lion(-1));
                    case -6:
                        board.setCellAnimal(i,j,new Tiger(-1));
                    case -5:
                        board.setCellAnimal(i,j,new Leopard(-1));
                    case -4:
                        board.setCellAnimal(i,j,new Wolf(-1));
                    case -3:
                        board.setCellAnimal(i,j,new Dog(-1));
                    case -2:
                        board.setCellAnimal(i,j,new Cat(-1));
                    case -1:
                        board.setCellAnimal(i,j,new Rat(-1));
                    case 8:
                        board.setCellAnimal(i,j,new Elephant(1));
                    case 7:
                        board.setCellAnimal(i,j,new Lion(1));
                    case 6:
                        board.setCellAnimal(i,j,new Tiger(1));
                    case 5:
                        board.setCellAnimal(i,j,new Leopard(1));
                    case 4:
                        board.setCellAnimal(i,j,new Wolf(1));
                    case 3:
                        board.setCellAnimal(i,j,new Dog(1));
                    case 2:
                        board.setCellAnimal(i,j,new Cat(1));
                    case 1:
                        board.setCellAnimal(i,j,new Rat(1));
                    case 0:
                      continue;
                }
            }
        }
return board;
        }*/
        public ChessBoard getSaveData(){
        return save;
        }
        //存档测试


    }
