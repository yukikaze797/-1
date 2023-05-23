package View;

import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
//游戏窗口


public class PlayFrame extends JFrame {
        //储存图片路径
        private String[] PicturePath = {
                "src/main/java/View/picture/rat.jpg",
                "src/main/java/View/picture/cat.jpg",
                "src/main/java/View/picture/dog.jpg",
                "src/main/java/View/picture/wolf.jpg",
                "src/main/java/View/picture/tiger.jpg",
                "src/main/java/View/picture/leopard.jpg",
                "src/main/java/View/picture/lion.jpg",
                "src/main/java/View/picture/elephant.jpg",
                "src/main/java/View/picture/river.jpg",
                "src/main/java/View/picture/land.jpg",
                "src/main/java/View/picture/home.jpg",
                "src/main/java/View/picture/trap.jpg"
        };
        //具体游戏窗口
        private JFrame frame = new JFrame("游戏中");
        //棋盘所在面板
        private JPanel panel1;
        //按钮所在面板
        private JPanel panel2;
        //棋盘格子大小
        private int gridx = 80;
        private int gridy = 80;
        //使用存档生成窗口

        public PlayFrame(ChessBoard board) {
            frame.setSize(600, 600);
            MyMouseListener listener =new MyMouseListener();
            //棋盘可视化的Jlabel数组，之后根据返回的坐标对这个数组更改，通过该数组绘制棋盘
            JLabel [][] JBoard = Transit(board);
            panel1 = drawBoard(JBoard);
            frame.add(panel1);
            panel1.setLocation(300, 20);
            panel2 = FPanel();

            panel2.setLocation(500, 100);
            frame.add(panel2);


            frame.setVisible(true);
        }
    //给panel1用的方法，将存档转为Jlabel数组
        public JLabel [][] Transit(ChessBoard board){
            JLabel[][] JBoard = new JLabel[9][7];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    JLabel cell = new JLabel();
                    cell.setSize(80, 80);
                    cell.addMouseListener(new MyMouseListener());
                    String name;
                    if (board.getGameBoard()[i][j].isHasChess()) {
                        name = board.getGameBoard()[i][j].getAnimal().getName();
                    } else {
                        name = "null";
                    }
                    CellType type =board.getGameBoard()[i][j].getType();
                    if(name.equals("rat")){
                        cell.setIcon(new ImageIcon(PicturePath[0]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("cat")){
                        cell.setIcon(new ImageIcon(PicturePath[1]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("dog")){
                        cell.setIcon(new ImageIcon(PicturePath[2]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("wolf")){
                        cell.setIcon(new ImageIcon(PicturePath[3]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("tiger")){
                        cell.setIcon(new ImageIcon(PicturePath[4]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("leopard")){
                        cell.setIcon(new ImageIcon(PicturePath[5]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("lion")){
                        cell.setIcon(new ImageIcon(PicturePath[6]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else if(name.equals("elephant")){
                        cell.setIcon(new ImageIcon(PicturePath[7]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        JBoard [i][j]=cell;
                    }else {
                        if(type==CellType.BLANK){
                            cell.setIcon(new ImageIcon(PicturePath[9]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(type==CellType.HOME){
                            cell.setIcon(new ImageIcon(PicturePath[10]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(type==CellType.RIVER){
                            cell.setIcon(new ImageIcon(PicturePath[8]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(type==CellType.TRAP){
                            ImageIcon icon = new ImageIcon(PicturePath[11]);
                            cell.setIcon(icon);
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else{
                        }
                }
            }

        }
            return JBoard;
        }
        //根据Jlabel数组绘制棋盘，返回类型为Panel
        public JPanel drawBoard(JLabel[][] JBoard){
            JPanel board = new JPanel();
            board.setSize(560, 720);
            board.setLayout(new GridLayout(9,7));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    board.add(JBoard[i][j]);
                }
            }
            board.setPreferredSize(new Dimension(560,720));
            return board;
        }
        //给panel2用的方法，功能按键面板设置
    public JPanel FPanel(){
            JPanel FPanel =new JPanel();
            FPanel.setSize(100,600);
            JButton Save = new JButton("保存游戏");
            Save.setSize(100, 20);
            JButton huiqi = new JButton("悔棋");
            JButton Exit = new JButton("退出游戏");
            FPanel.add(Save);
            FPanel.add(huiqi);
            FPanel.add(Exit);
            FPanel.setLayout(new BoxLayout(FPanel, BoxLayout.Y_AXIS));
            FPanel.setPreferredSize(new Dimension(100,600));
            return FPanel;
    }
        //鼠标事件适配器
        public class MyMouseListener extends MouseAdapter {
            //获取鼠标单击的位置相对容器坐标
           //默认值为 -1
            private int lastClickedCol =-1;
            private int lastClickedRow =-1;
            @Override
            public void mouseClicked(MouseEvent e) {
                lastClickedCol =(int)Math.floor(e.getComponent().getX()/80);
                lastClickedRow =(int)Math.floor(e.getComponent().getY()/80);
            }

            public int getLastClickedCol() {
                return lastClickedCol;
            }
            public int getLastClickedRow(){
                return lastClickedRow;
            }
        }
    }
