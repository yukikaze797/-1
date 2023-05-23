package View;

import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;


public class PlayFrame extends JFrame {
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
        private JFrame frame = new JFrame("游戏中");
        private JPanel panel1;
        private JPanel panel2;
        private int gridx = 80;
        private int gridy = 80;

        public PlayFrame(ChessBoard board) {
            frame.setSize(600, 600);
            MyMouseListener listener =new MyMouseListener();
            JLabel [][] JBoard = Transit(board);
            panel1 = drawBoard(JBoard);
            frame.add(panel1);
            panel1.setLocation(300, 20);
            panel2 = FPanel();

            panel2.setLocation(500, 100);
            frame.add(panel2);


            frame.setVisible(true);
        }
//将存档绘制，在导入存档时使用
       /* public JPanel drawBoard(ChessBoard chessBoard,MyMouseListener listener) {
            JPanel board = new JPanel();
            board.setSize(1000, 950);
            board.setLayout(null);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    JLabel cell = new JLabel();
                    cell.setLocation(j * gridx, i * gridy);
                    cell.setSize(80, 80);
                    cell.addMouseListener(listener);
                    String name;
                    if (chessBoard.getGameBoard()[i][j].isHasChess()) {
                        name = chessBoard.getGameBoard()[i][j].getAnimal().getName();
                    } else {
                        name = "null";
                    }
                    CellType type = chessBoard.getGameBoard()[i][j].getType();
                    if(name.equals("rat")){
                        cell.setIcon(new ImageIcon(PicturePath[0]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("cat")){
                        cell.setIcon(new ImageIcon(PicturePath[1]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("dog")){
                        cell.setIcon(new ImageIcon(PicturePath[2]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("wolf")){
                        cell.setIcon(new ImageIcon(PicturePath[3]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("tiger")){
                        cell.setIcon(new ImageIcon(PicturePath[4]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("leopard")){
                        cell.setIcon(new ImageIcon(PicturePath[5]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("lion")){
                        cell.setIcon(new ImageIcon(PicturePath[6]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else if(name.equals("elephant")){
                        cell.setIcon(new ImageIcon(PicturePath[7]));
                        cell.setBorder(BorderFactory.createLineBorder(Color.black));
                        board.add(cell);
                    }else {
                        if(type==CellType.BLANK){
                            cell.setIcon(new ImageIcon(PicturePath[9]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            board.add(cell);
                        }else if(type==CellType.HOME){
                            cell.setIcon(new ImageIcon(PicturePath[10]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            board.add(cell);
                        }else if(type==CellType.RIVER){
                            cell.setIcon(new ImageIcon(PicturePath[8]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            board.add(cell);
                        }else if(type==CellType.TRAP){
                            ImageIcon icon = new ImageIcon(PicturePath[11]);
                            cell.setIcon(icon);
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            board.add(cell);
                        }else{
                        }
                    }

                }
            }
            return board;
        }*/
    //给panel1用的方法
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
        //给panel2用的方法
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
            private int lastClickedX;
            private int lastClickedY;
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            public int getLastClickedX() {
                return lastClickedX/80;
            }
            public int getLastClickedY(){
                return lastClickedY;
            }
        }
    }