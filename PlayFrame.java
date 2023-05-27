package View;

import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


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
       /* private int gridx = 80;
        private int gridy = 80;*/
        //用来存档的变量
        private ChessBoard save;

        public PlayFrame(ChessBoard board) {
            if(board==null){
                save = new ChessBoard(new Person(-1),new Person(1));
            }else{
            save = board;
            }
            frame.setSize(600, 600);
            MyLabelListener listener =new MyLabelListener();
            JLabel [][] JBoard = Transit(save,listener);
            panel1 = drawBoard(JBoard);
            panel1.setLocation(300, 20);
            frame.add(panel1);
            panel2 = FPanel();
            frame.add(panel2);
            panel2.setLocation(500, 100);
            frame.setVisible(true);
        }
    //给panel1用的方法
        public JLabel [][] Transit(ChessBoard board,MyLabelListener listener){
            JLabel[][] JBoard = new JLabel[9][7];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    JLabel cell = new JLabel();
                    cell.setSize(80, 80);
                    cell.addMouseListener(listener);
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
            Save.setActionCommand("save");
            Save.setSize(100, 20);
            Save.addActionListener(new ButtonListener());
            JButton huiqi = new JButton("悔棋");
            huiqi.setActionCommand("悔棋");
            huiqi.addActionListener(new ButtonListener());
            JButton Exit = new JButton("退出游戏");
            Exit.setActionCommand("exit");
            Exit.addActionListener(new ButtonListener());
            FPanel.add(Save);
            FPanel.add(huiqi);
            FPanel.add(Exit);
            FPanel.setLayout(new BoxLayout(FPanel, BoxLayout.Y_AXIS));
            FPanel.setPreferredSize(new Dimension(100,600));
            return FPanel;
    }
        //鼠标事件适配器
        public class MyLabelListener extends MouseAdapter {
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
    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "exit":
                    Window windowE = SwingUtilities.getWindowAncestor((Component) e.getSource());
                    Component[] component1 = windowE.getComponents();
                    for (Component component : component1) {
                        if (component instanceof PlayFrame) {
                            PlayFrame p = (PlayFrame) component;
                        }
                    }
                    if (windowE != null) {
                        windowE.dispose();
                    }
                    break;
                case "save":
                    //获取当前窗口
                     //控制台确认按钮已被点击
                        System.out.println("save");
                     Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                     for(Component component: window.getComponents()) {
                         if (component instanceof PlayFrame) {
                             PlayFrame frame1 = (PlayFrame) component;
                             ChessBoard.writeBoardToFile(frame1.getSave().SaveRanks(), frame1.getSave().SaveTrap(), "src/main/java/data/");
                         }
                     }
                      break;
                    default:
                        break;
            }
                    }
            }

    public JPanel getPanel1() {
        return panel1;
    }

    public JPanel getPanel2() {
        return panel2;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public void setSave(ChessBoard save) {
        this.save = save;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ChessBoard getSave() {
        return save;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}



