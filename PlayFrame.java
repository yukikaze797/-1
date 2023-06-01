package View;

import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;



public class PlayFrame extends JFrame {
        private String[] PicturePath = {
                "src/main/java/View/picture/rat.jpg",//0
                "src/main/java/View/picture/cat.jpg",//1
                "src/main/java/View/picture/dog.jpg",//2
                "src/main/java/View/picture/wolf.jpg",//3
                "src/main/java/View/picture/tiger.jpg",//4
                "src/main/java/View/picture/leopard.jpg",//5
                "src/main/java/View/picture/lion.jpg",//6
                "src/main/java/View/picture/elephant.jpg",//7
                "src/main/java/View/picture/river.jpg",//8
                "src/main/java/View/picture/land.jpg",//9
                "src/main/java/View/picture/home.jpg",//10
                "src/main/java/View/picture/trap.jpg",//11
                "src/main/java/View/picture/rat逆转.jpg",//12
                "src/main/java/View/picture/cat2.jpg",//13
                "src/main/java/View/picture/dog旋转.jpg",//14
                "src/main/java/View/picture/wolf旋转.jpg",//15
                "src/main/java/View/picture/leopard旋转.jpg",//16
                "src/main/java/View/picture/tiger旋转.jpg",//17
                "src/main/java/View/picture/lion旋转.jpg",//18
                "src/main/java/View/picture/旋转大象.jpg"//19
        };
        private JFrame frame = new JFrame("游戏中");
        private JPanel panel1;
        private JPanel panel2;
        //现在鼠标点击的位置
       public int x = -1;
      public int y = -1;
      //上一次鼠标点击的位置
    public int x0=-1;
    public int y0=-1;
        //该窗口下用来存档的变量save,实际上就是正在操作的棋盘
        private ChessBoard save;
        private GameFrame gameFrame;
        private boolean selected;
        //全局回放replay
    private ArrayList<ChessBoard> rep=new ArrayList<>(2);
    //用来悔棋的replay
    private ArrayList<ChessBoard> rep1=new ArrayList<>();

        public PlayFrame(GameFrame gameFrame) {
            ChessBoard board=gameFrame.getSaveData();
            if(board==null){
                save = new ChessBoard(new Person(-1),new Person(1));
            }else{
            save = board;
            }
            rep.add(save);
            rep1.add(save);
            frame.setSize(1280, 820);
            MyLabelListener listener =new MyLabelListener();
            JLabel [][] JBoard = Transit(save,listener);
            panel1 = drawBoard(JBoard);
            panel1.setLocation(400, 50);
            panel1.addMouseListener(new PanelListener());
            panel2 = FPanel();
            panel2.setLocation(50, 100);
            frame.add(panel1);
            frame.add(panel2);
            frame.setVisible(true);
        }
        public PlayFrame(ChessBoard board){
            if(board==null){
                save = new ChessBoard(new Person(-1),new Person(1));
            }else{
                save = board;
            }
            frame.setSize(1280, 800);
            MyLabelListener listener =new MyLabelListener();
            JLabel [][] JBoard = Transit(save,listener);
            JPanel mainPanel = new JPanel(new BorderLayout());
            panel1 = drawBoard(JBoard);
            panel1.setLocation(400, 50);
            panel2 = FPanel();
            panel2.setLocation(50, 100);
            frame.add(panel1);
            frame.add(panel2);
            frame.setVisible(true);
        }
    //给panel1用的方法
        public  JLabel [][] Transit(ChessBoard board,MyLabelListener listener){
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
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[0]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[12]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }

                    }else if(name.equals("cat")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[1]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[13]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
                    }else if(name.equals("dog")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[2]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[14]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
                    }else if(name.equals("wolf")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[3]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[15]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
                    }else if(name.equals("tiger")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[4]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[17]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
                    }else if(name.equals("leopard")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[5]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[16]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
                    }else if(name.equals("lion")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[6]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[18]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }

                    }else if(name.equals("elephant")){
                        if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()>0){
                            cell.setIcon(new ImageIcon(PicturePath[7]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }else if(board.getGameBoard()[i][j].getAnimal()!=null&&board.getGameBoard()[i][j].getAnimal().getRank()<0){
                            cell.setIcon(new ImageIcon(PicturePath[19]));
                            cell.setBorder(BorderFactory.createLineBorder(Color.black));
                            JBoard [i][j]=cell;
                        }
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
            System.out.println("成功转化");
            return JBoard;
        }
        public static JPanel drawBoard(JLabel[][] JBoard){
            JPanel board = new JPanel();
            board.setSize(560,720);
            board.setPreferredSize(new Dimension(560, 720));
            board.setLayout(new GridLayout(9,7));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    board.add(JBoard[i][j]);
                }
            }
            board.setVisible(true);
            System.out.println("成功生成可视化棋盘");
            return board;
        }
        //给panel2用的方法
        public JPanel FPanel(){
            JPanel FPanel =new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon imageIcon = new ImageIcon("src/main/java/View/picture/背景.jpg");
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            };;
            FPanel.setLayout(new BoxLayout(FPanel, BoxLayout.Y_AXIS));
            FPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
            FPanel.setPreferredSize(new Dimension(400,600));
            JButton Restart =new JButton("重新开始");
            Restart.setActionCommand("restart");;
            Restart.addActionListener(new ButtonListener());
            Restart.setMaximumSize(new Dimension(170,70));
            Restart.setMinimumSize(new Dimension(170,70));
            //
            JButton Save = new JButton("保存游戏");
            Save.setActionCommand("save");;
            Save.addActionListener(new ButtonListener());
            Save.setMaximumSize(new Dimension(170,70));
            Save.setMinimumSize(new Dimension(170,70));
            Save.setOpaque(false);
            //
            JButton huiqi = new JButton("悔棋");
            huiqi.setActionCommand("悔棋");
            huiqi.addActionListener(new ButtonListener());
            huiqi.setMaximumSize(new Dimension(170,70));
            huiqi.setMinimumSize(new Dimension(170,70));
            //
            JButton Exit = new JButton("退出游戏");
            Exit.setActionCommand("exit");
            Exit.addActionListener(new ButtonListener());
            Exit.setMaximumSize(new Dimension(170,70));
            Exit.setMinimumSize(new Dimension(170,70));
            //
            JButton Load = new JButton("读取存档");
            Load.setActionCommand("load");
            Load.addActionListener(new ButtonListener());
            Load.setMinimumSize(new Dimension(170,70));
            Load.setMaximumSize(new Dimension(170,70));

            JLabel Turns=new JLabel();
            Turns.setSize(170, 50);
            if(save.getTurns()%2==0){
                String turns="BLUE Turns "+save.getTurns()+"";
                Turns.setText(turns);
            }else{
                String turns = "RED Turns "+save.getTurns()+"";
                Turns.setText(turns);
            }
            FPanel.add(Turns);
            FPanel.add(Box.createVerticalStrut(10));
            FPanel.add(Restart);
            FPanel.add(Box.createVerticalStrut(70));
            FPanel.add(Save);
            FPanel.add(Box.createVerticalStrut(70));
            FPanel.add(huiqi);
            FPanel.add(Box.createVerticalStrut(70));
            FPanel.add(Load);
            FPanel.add(Box.createVerticalStrut(70));
            FPanel.add(Exit);
            return FPanel;
    }
        //鼠标事件适配器
        public class MyLabelListener extends MouseAdapter {
            //获取鼠标单击的位置相对棋盘坐标
            //默认值为 -1
            public int lastClickedCol =-1;
           public int lastClickedRow =-1;
            @Override
            public void mouseClicked(MouseEvent e) {
                lastClickedCol =(int)Math.floor(e.getComponent().getX()/80);
                lastClickedRow =(int)Math.floor(e.getComponent().getY()/80);
                System.out.printf("%d,%d JLabel \n ",lastClickedRow,lastClickedCol);
                y=lastClickedCol;
                x=lastClickedRow;
                int row = x;
                int col = y;
                System.out.println("Mouse clicked at row " + row + ", column " + col);
                // 移动棋子等
                //false表示未选中棋子
                //蓝方回合
                if(save.getTurns()%2==0){
                    if(selected==false){
                        if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()>0){
                            selected=true;
                        }else if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()==0){
                            //陷阱格动物
                            if(save.getGameBoard()[x][y].getType()==CellType.TRAP){
                                if(x==0&&y==2){
                                    selected=true;
                                }else if(x==0&&y==4){
                                    selected=true;
                                }else if(x==1&&y==3){
                                    selected=true;
                                }
                            }
                        }
                        x0=x;
                        y0=y;
                    }else if(selected){
                        //要移动的位置有棋子
                        if(movable(x0, y0, x, y)&&save.getGameBoard()[x][y].isHasChess()){
                            //大于零为蓝方棋子
                            if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()<0){
                                //蓝方棋子rank更大,吃子
                                if(save.getGameBoard()[x][y].getAnimal().getRank()<0 && Math.abs(save.getGameBoard()[x][y].getAnimal().getRank())<Math.abs(save.getGameBoard()[x0][y0].getAnimal().getRank())){
                                    save.getGameBoard()[x][y].removeAnimal();
                                    save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                    save.getGameBoard()[x0][y0].removeAnimal();
                                    save.setTurns(save.getTurns()+1);
                                    rep.add(save);
                                    rep1.add(save);
                                    //
                                    JLabel [][] Label =Transit(save, new MyLabelListener());
                                    panel1.removeAll();
                                    for (int i = 0; i < 9; i++) {
                                        for (int j = 0; j < 7; j++) {
                                            panel1.add(Label[i][j]);
                                        }
                                    }
                                    panel1.revalidate();
                                    panel1.repaint();
                                    panel1.updateUI();
                                    Component[] components = panel2.getComponents();
                                    for (Component comp : components) {
                                        if (comp instanceof JLabel ) {
                                            JLabel Turns = (JLabel) comp;
                                            if(save.getTurns()%2==0){
                                                String turns="BLUE Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }else{
                                                String turns = "RED Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }
                                        }
                                    }
                                    //
                                }
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                                //陷阱格
                            }else if(((x==8 && y==2)||(x==8 && y==4)||(x==7 && y ==3)) && save.getGameBoard()[x][y].getAnimal().getRank()==0 ){
                                save.getGameBoard()[x][y].removeAnimal();
                                save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                save.setTurns(save.getTurns()+1);
                                rep.add(save);
                                rep1.add(save);
                                //
                                JLabel [][] Label =Transit(save, new MyLabelListener());
                                panel1.removeAll();
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 7; j++) {
                                        panel1.add(Label[i][j]);
                                    }
                                }
                                panel1.revalidate();
                                panel1.repaint();
                                panel1.updateUI();
                                Component[] components = panel2.getComponents();
                                for (Component comp : components) {
                                    if (comp instanceof JLabel ) {
                                        JLabel Turns = (JLabel) comp;
                                        if(save.getTurns()%2==0){
                                            String turns="BLUE Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }else{
                                            String turns = "RED Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }
                                    }
                                }
                            }
                        }
                        if(movable(x0, y0, x, y)&& save.getGameBoard()[x][y].isHasChess()==false){
                            save.getGameBoard()[x][y].setAnimal(save.getGameBoard()[x0][y0].getAnimal());
                            save.getGameBoard()[x0][y0].removeAnimal();
                            save.setTurns(save.getTurns()+1);
                            rep.add(save);
                            rep1.add(save);
                            //
                            JLabel [][] Label =Transit(save, new MyLabelListener());
                            panel1.removeAll();
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 7; j++) {
                                    panel1.add(Label[i][j]);
                                }
                            }
                            panel1.revalidate();
                            panel1.repaint();
                            panel1.updateUI();
                            Component[] components = panel2.getComponents();
                            for (Component comp : components) {
                                if (comp instanceof JLabel ) {
                                    JLabel Turns = (JLabel) comp;
                                    if(save.getTurns()%2==0){
                                        String turns="BLUE Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }else{
                                        String turns = "RED Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }
                                }
                            }

                        }
                        selected=false;
                    }
                    if(save.WinTest()==1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("蓝方胜");
                        win.setFont(new Font("蓝方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }else if(save.WinTest()==-1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("红方胜");
                        win.setFont(new Font("红方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
                if(save.getTurns()%2==1){
                    if(selected==false){
                        if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()<0){
                            selected=true;
                        }else if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()==0){
                            //陷阱格动物
                            if(save.getGameBoard()[x][y].getType()==CellType.TRAP){
                                if(x==8&&y==2){
                                    selected=true;
                                }else if(x==8&&y==4){
                                    selected=true;
                                }else if(x==7&&y==3){
                                    selected=true;
                                }
                            }
                        }
                        x0=x;
                        y0=y;
                    }else if(selected){
                        //要移动的位置有棋子
                        if(movable(x0, y0, x, y)&&save.getGameBoard()[x][y].isHasChess()){
                            //大于零为蓝方棋子
                            if(save.getGameBoard()[x][y].getAnimal().getRank()<0){
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                                //比较rank,吃子
                                if(save.getGameBoard()[x][y].getAnimal().getRank()>0 && Math.abs(save.getGameBoard()[x][y].getAnimal().getRank())<Math.abs(save.getGameBoard()[x0][y0].getAnimal().getRank())){
                                    save.getGameBoard()[x][y].removeAnimal();
                                    save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                    save.getGameBoard()[x0][y0].removeAnimal();
                                    save.setTurns(save.getTurns()+1);
                                    rep.add(save);
                                    rep1.add(save);
                                    //重画棋盘
                                    JLabel [][] Label =Transit(save, new MyLabelListener());
                                    panel1.removeAll();
                                    for (int i = 0; i < 9; i++) {
                                        for (int j = 0; j < 7; j++) {
                                            panel1.add(Label[i][j]);
                                        }
                                    }
                                    panel1.revalidate();
                                    panel1.repaint();
                                    panel1.updateUI();
                                    Component[] components = panel2.getComponents();
                                    for (Component comp : components) {
                                        if (comp instanceof JLabel ) {
                                            JLabel Turns = (JLabel) comp;
                                            if(save.getTurns()%2==0){
                                                String turns="BLUE Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }else{
                                                String turns = "RED Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }
                                        }
                                    }
                                    //
                                }
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                                //陷阱格
                            }else if(((x==8 && y==2)||(x==8 && y==4)||(x==7 && y ==3)) && save.getGameBoard()[x][y].getAnimal().getRank()==0 ){
                                save.getGameBoard()[x][y].removeAnimal();
                                save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                save.setTurns(save.getTurns()+1);
                                rep.add(save);
                                rep1.add(save);
                                //
                                JLabel [][] Label =Transit(save, new MyLabelListener());
                                panel1.removeAll();
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 7; j++) {
                                        panel1.add(Label[i][j]);
                                    }
                                }
                                panel1.revalidate();
                                panel1.repaint();
                                panel1.updateUI();
                                Component[] components = panel2.getComponents();
                                for (Component comp : components) {
                                    if (comp instanceof JLabel ) {
                                        JLabel Turns = (JLabel) comp;
                                        if(save.getTurns()%2==0){
                                            String turns="BLUE Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }else{
                                            String turns = "RED Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }
                                    }
                                }
                            }
                        }
                        if(movable(x0, y0, x, y)&& save.getGameBoard()[x][y].isHasChess()==false){
                            save.getGameBoard()[x][y].setAnimal(save.getGameBoard()[x0][y0].getAnimal());
                            save.getGameBoard()[x0][y0].removeAnimal();
                            save.setTurns(save.getTurns()+1);
                            rep.add(save);
                            rep1.add(save);
                            //
                            JLabel [][] Label =Transit(save, new MyLabelListener());
                            panel1.removeAll();
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 7; j++) {
                                    panel1.add(Label[i][j]);
                                }
                            }
                            panel1.revalidate();
                            panel1.repaint();
                            panel1.updateUI();
                            Component[] components = panel2.getComponents();
                            for (Component comp : components) {
                                if (comp instanceof JLabel ) {
                                    JLabel Turns = (JLabel) comp;
                                    if(save.getTurns()%2==0){
                                        String turns="BLUE Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }else{
                                        String turns = "RED Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }
                                }
                            }

                        }
                        selected=false;
                    }
                    if(save.WinTest()==1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("蓝方胜");
                        win.setFont(new Font("蓝方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }else if(save.WinTest()==-1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("红方胜");
                        win.setFont(new Font("红方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
                System.out.println(selected);
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
                     //控制台确认按钮已被点击
                        System.out.println("save");
                        ChessBoard.writeBoardToFile(save.SaveRanks(), save.SaveTrap(),"src/main/java/data/");
                    //获取当前窗口
                     //取得PlayFrame;
                      break;
                case"load":
                    System.out.println("click load");
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
                        ChessBoard board=GameFrame.LoadSave(selectedFile);
                    if(board == null){
                        JFrame error = new JFrame();
                        error.setTitle("存档错误");
                        error.setSize(700, 100);
                        JLabel hint =new JLabel("错误的存档格式或存档内容");
                        hint.setFont(new Font("错误的存档格式或存档内容",Font.BOLD,32));
                        error.add(hint);
                        error.setVisible(true);
                    }else {
                        save = board;
                        rep.add(save);
                        rep1.clear();
                        rep1.add(save);
                        JLabel [][] Label =Transit(save, new MyLabelListener());
                        panel1.removeAll();
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 7; j++) {
                                panel1.add(Label[i][j]);
                            }
                        }
                        panel1.revalidate();
                        panel1.repaint();
                        panel1.updateUI();
                        Component[] components = panel2.getComponents();
                        for (Component comp : components) {
                            if (comp instanceof JLabel ) {
                                JLabel Turns = (JLabel) comp;
                                if(save.getTurns()%2==0){
                                    String turns="BLUE Turns "+save.getTurns()+"";
                                    Turns.setText(turns);
                                }else{
                                    String turns = "RED Turns "+save.getTurns()+"";
                                    Turns.setText(turns);
                                }
                                // do something with myLabel
                                break;
                            }
                        }
                    }
                    }
                    break;
                case"restart":
                    rep1.clear();
                    save = new ChessBoard(new Person(-1),new Person(1));
                    rep.add(save);
                    rep1.add(save);
                    JLabel [][] Label =Transit(save, new MyLabelListener());
                    panel1.removeAll();
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 7; j++) {
                            panel1.add(Label[i][j]);
                        }
                    }
                    panel1.revalidate();
                    panel1.repaint();
                    panel1.updateUI();
                    Component[] components = panel2.getComponents();
                    for (Component comp : components) {
                        if (comp instanceof JLabel ) {
                            JLabel Turns = (JLabel) comp;
                            if(save.getTurns()%2==0){
                                String turns="BLUE Turns "+save.getTurns()+"";
                                Turns.setText(turns);
                            }else{
                                String turns = "RED Turns "+save.getTurns()+"";
                                Turns.setText(turns);
                            }
                            // do something with myLabel
                            break;
                        }
                    }
                    break;
                case"huiqi":
                    if(rep1.size()>=2){
                        save=rep1.get(rep1.size()-2);
                        rep.add(save);
                        rep1.remove(rep1.size()-1);
                    }
                    break;
                    default:
                        break;
            }
                    }
            }
            public class PanelListener implements MouseListener {
            @Override
                public void mouseClicked(MouseEvent e){
                    int row = x;
                    int col = y;
                    System.out.println("Mouse clicked at row " + row + ", column " + col);
                    // 移动棋子等
                //false表示未选中棋子
                //蓝方回合
                if(save.getTurns()%2==0){
                if(selected==false){
                    if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()>0){
                        selected=true;
                    }else if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()==0){
                        //陷阱格动物
                        if(save.getGameBoard()[x][y].getType()==CellType.TRAP){
                            if(x==0&&y==2){
                                selected=true;
                            }else if(x==0&&y==4){
                                selected=true;
                            }else if(x==1&&y==3){
                                selected=true;
                            }
                        }
                    }
                    x0=x;
                    y0=y;
                }else if(selected){
                    //要移动的位置有棋子
                    if(movable(x0, y0, x, y)&&save.getGameBoard()[x][y].isHasChess()){
                        //大于零为蓝方棋子
                        if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                        }else if(save.getGameBoard()[x][y].getAnimal().getRank()<0){
                            //蓝方棋子rank更大,吃子
                            if(save.getGameBoard()[x][y].getAnimal().getRank()<0 && Math.abs(save.getGameBoard()[x][y].getAnimal().getRank())<Math.abs(save.getGameBoard()[x0][y0].getAnimal().getRank())){
                                save.getGameBoard()[x][y].removeAnimal();
                                save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                save.getGameBoard()[x0][y0].removeAnimal();
                                save.setTurns(save.getTurns()+1);
                                rep.add(save);
                                rep1.add(save);
                                //
                                JLabel [][] Label =Transit(save, new MyLabelListener());
                                panel1.removeAll();
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 7; j++) {
                                        panel1.add(Label[i][j]);
                                    }
                                }
                                panel1.revalidate();
                                panel1.repaint();
                                panel1.updateUI();
                                Component[] components = panel2.getComponents();
                                for (Component comp : components) {
                                    if (comp instanceof JLabel ) {
                                        JLabel Turns = (JLabel) comp;
                                        if(save.getTurns()%2==0){
                                            String turns="BLUE Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }else{
                                            String turns = "RED Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }
                                    }
                                }
                                //
                            }
                        }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                            //陷阱格
                        }else if(((x==8 && y==2)||(x==8 && y==4)||(x==7 && y ==3)) && save.getGameBoard()[x][y].getAnimal().getRank()==0 ){
                            save.getGameBoard()[x][y].removeAnimal();
                            save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                            save.setTurns(save.getTurns()+1);
                            rep.add(save);
                            rep1.add(save);
                            //
                            JLabel [][] Label =Transit(save, new MyLabelListener());
                            panel1.removeAll();
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 7; j++) {
                                    panel1.add(Label[i][j]);
                                }
                            }
                            panel1.revalidate();
                            panel1.repaint();
                            panel1.updateUI();
                            Component[] components = panel2.getComponents();
                            for (Component comp : components) {
                                if (comp instanceof JLabel ) {
                                    JLabel Turns = (JLabel) comp;
                                    if(save.getTurns()%2==0){
                                        String turns="BLUE Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }else{
                                        String turns = "RED Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }
                                }
                            }
                        }
                    }
                    if(movable(x0, y0, x, y)&& save.getGameBoard()[x][y].isHasChess()==false){
                        save.getGameBoard()[x][y].setAnimal(save.getGameBoard()[x0][y0].getAnimal());
                        save.getGameBoard()[x0][y0].removeAnimal();
                        save.setTurns(save.getTurns()+1);
                        rep.add(save);
                        rep1.add(save);
                        //
                        JLabel [][] Label =Transit(save, new MyLabelListener());
                        panel1.removeAll();
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 7; j++) {
                                panel1.add(Label[i][j]);
                            }
                        }
                        panel1.revalidate();
                        panel1.repaint();
                        panel1.updateUI();
                        Component[] components = panel2.getComponents();
                        for (Component comp : components) {
                            if (comp instanceof JLabel ) {
                                JLabel Turns = (JLabel) comp;
                                if(save.getTurns()%2==0){
                                    String turns="BLUE Turns "+save.getTurns()+"";
                                    Turns.setText(turns);
                                }else{
                                    String turns = "RED Turns "+save.getTurns()+"";
                                    Turns.setText(turns);
                                }
                            }
                        }

                    }
                    selected=false;
                }
                if(save.WinTest()==1){
                    JFrame WinBlue=new JFrame("胜利");
                    WinBlue.setSize(100, 100);
                    WinBlue.setLocation(300, 200);
                    JLabel win=new JLabel("蓝方胜");
                    win.setFont(new Font("蓝方胜",Font.BOLD,20));
                    WinBlue.add(win);
                    WinBlue.setVisible(true);
                    WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }else if(save.WinTest()==-1){
                    JFrame WinBlue=new JFrame("胜利");
                    WinBlue.setSize(100, 100);
                    WinBlue.setLocation(300, 200);
                    JLabel win=new JLabel("红方胜");
                    win.setFont(new Font("红方胜",Font.BOLD,20));
                    WinBlue.add(win);
                    WinBlue.setVisible(true);
                    WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                }
                }
                if(save.getTurns()%2==1){
                    if(selected==false){
                        if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()<0){
                            selected=true;
                        }else if(save.getGameBoard()[x][y].getAnimal()!=null && save.getGameBoard()[x][y].getAnimal().getRank()==0){
                            //陷阱格动物
                            if(save.getGameBoard()[x][y].getType()==CellType.TRAP){
                                if(x==8&&y==2){
                                    selected=true;
                                }else if(x==8&&y==4){
                                    selected=true;
                                }else if(x==7&&y==3){
                                    selected=true;
                                }
                            }
                        }
                        x0=x;
                        y0=y;
                    }else if(selected){
                        //要移动的位置有棋子
                        if(movable(x0, y0, x, y)&&save.getGameBoard()[x][y].isHasChess()){
                            //大于零为蓝方棋子
                            if(save.getGameBoard()[x][y].getAnimal().getRank()<0){
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                                //比较rank,吃子
                                if(save.getGameBoard()[x][y].getAnimal().getRank()>0 && Math.abs(save.getGameBoard()[x][y].getAnimal().getRank())<Math.abs(save.getGameBoard()[x0][y0].getAnimal().getRank())){
                                    save.getGameBoard()[x][y].removeAnimal();
                                    save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                    save.getGameBoard()[x0][y0].removeAnimal();
                                    save.setTurns(save.getTurns()+1);
                                    rep.add(save);
                                    rep1.add(save);
                                    //重画棋盘
                                    JLabel [][] Label =Transit(save, new MyLabelListener());
                                    panel1.removeAll();
                                    for (int i = 0; i < 9; i++) {
                                        for (int j = 0; j < 7; j++) {
                                            panel1.add(Label[i][j]);
                                        }
                                    }
                                    panel1.revalidate();
                                    panel1.repaint();
                                    panel1.updateUI();
                                    Component[] components = panel2.getComponents();
                                    for (Component comp : components) {
                                        if (comp instanceof JLabel ) {
                                            JLabel Turns = (JLabel) comp;
                                            if(save.getTurns()%2==0){
                                                String turns="BLUE Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }else{
                                                String turns = "RED Turns "+save.getTurns()+"";
                                                Turns.setText(turns);
                                            }
                                        }
                                    }
                                    //
                                }
                            }else if(save.getGameBoard()[x][y].getAnimal().getRank()>0){
                                //陷阱格
                            }else if(((x==8 && y==2)||(x==8 && y==4)||(x==7 && y ==3)) && save.getGameBoard()[x][y].getAnimal().getRank()==0 ){
                                save.getGameBoard()[x][y].removeAnimal();
                                save.setCellAnimal(x, y, save.getGameBoard()[x0][y0].getAnimal());
                                save.setTurns(save.getTurns()+1);
                                rep.add(save);
                                rep1.add(save);
                                //
                                JLabel [][] Label =Transit(save, new MyLabelListener());
                                panel1.removeAll();
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 7; j++) {
                                        panel1.add(Label[i][j]);
                                    }
                                }
                                panel1.revalidate();
                                panel1.repaint();
                                panel1.updateUI();
                                Component[] components = panel2.getComponents();
                                for (Component comp : components) {
                                    if (comp instanceof JLabel ) {
                                        JLabel Turns = (JLabel) comp;
                                        if(save.getTurns()%2==0){
                                            String turns="BLUE Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }else{
                                            String turns = "RED Turns "+save.getTurns()+"";
                                            Turns.setText(turns);
                                        }
                                    }
                                }
                            }
                        }
                        if(movable(x0, y0, x, y)&& save.getGameBoard()[x][y].isHasChess()==false){
                            save.getGameBoard()[x][y].setAnimal(save.getGameBoard()[x0][y0].getAnimal());
                            save.getGameBoard()[x0][y0].removeAnimal();
                            save.setTurns(save.getTurns()+1);
                            rep.add(save);
                            rep1.add(save);
                            //
                            JLabel [][] Label =Transit(save, new MyLabelListener());
                            panel1.removeAll();
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 7; j++) {
                                    panel1.add(Label[i][j]);
                                }
                            }
                            panel1.revalidate();
                            panel1.repaint();
                            panel1.updateUI();
                            Component[] components = panel2.getComponents();
                            for (Component comp : components) {
                                if (comp instanceof JLabel ) {
                                    JLabel Turns = (JLabel) comp;
                                    if(save.getTurns()%2==0){
                                        String turns="BLUE Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }else{
                                        String turns = "RED Turns "+save.getTurns()+"";
                                        Turns.setText(turns);
                                    }
                                }
                            }

                        }
                        selected=false;
                    }
                    if(save.WinTest()==1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("蓝方胜");
                        win.setFont(new Font("蓝方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }else if(save.WinTest()==-1){
                        JFrame WinBlue=new JFrame("胜利");
                        WinBlue.setSize(100, 100);
                        WinBlue.setLocation(300, 200);
                        JLabel win=new JLabel("红方胜");
                        win.setFont(new Font("红方胜",Font.BOLD,20));
                        WinBlue.add(win);
                        WinBlue.setVisible(true);
                        WinBlue.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                }
            }
            @Override
                public void mouseEntered(MouseEvent e){}
                @Override
                public void mousePressed(MouseEvent e) {
                    // 处理鼠标按下事件
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // 处理鼠标释放事件
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    // 处理鼠标离开事件
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

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    public void updatePanel1(){
            panel1.repaint();
    }

    public ArrayList<ChessBoard> getRep() {
        return rep;
    }

    public void setRep(ArrayList<ChessBoard> rep) {
        this.rep = rep;
    }
        public boolean movable(int lastCol, int lastRow,int NewCol,int NewRol){
            if(lastCol==NewCol&&lastRow==NewRol){return false;}
            int rank =Math.abs(save.getGameBoard()[lastCol][lastRow].getAnimal().getRank());
            if(Math.abs(save.getGameBoard()[lastCol][lastRow].getAnimal().getRank())==1){
                //检测是否为相邻四格
                if(Math.abs(lastCol-NewCol)>=2&Math.abs(lastRow-NewRol)>=2){
                    return false;
                    //对角线
                }else if(Math.abs(lastCol-NewCol)==1 && Math.abs(lastRow-NewRol)==1){
                    return false;
                }else{
                    return true;
                }
                //选中狮或者虎
            }
            else if (Math.abs(save.getGameBoard()[lastCol][lastRow].getAnimal().getRank()) == 6 & Math.abs(save.getGameBoard()[lastCol][lastRow].getAnimal().getRank()) == 7) {
                //4*4 区域边缘
                if(Math.abs(lastCol-NewCol)==2&Math.abs(lastRow-NewRol)==2){
                    return false;
                    //对角线
                }else if(Math.abs(lastCol-NewCol)==1&&Math.abs(lastRow-NewRol)==1){
                    return false;
                    //跳河
                }else if(Math.abs(lastCol-NewCol)==4&&Math.abs(lastRow-NewRol)==0){
                    //跳河纵向检测
                    //向上跳
                    if(lastCol>NewCol){
                        int Rn=0;
                        for(int i=1;i<=3;i++){
                            if(save.getGameBoard()[lastCol-i][lastRow].getType()==CellType.RIVER){
                                Rn +=1;
                            }else{
                                break;
                            }
                        }
                        if(Rn==3){
                            return true;
                        }else{
                            return false;
                        }
                    }else if(lastCol<NewCol){
                        int Rn=0;
                        for(int i=1;i<=3;i++){
                            if(save.getGameBoard()[lastCol+i][lastRow].getType()==CellType.RIVER){
                                Rn +=1;
                            }else{
                                break;
                            }
                        }
                        if(Rn==3){
                            return true;
                        }else{
                            return false;
                        }
                    }else {
                        return false;
                    }
                    //横跳
                }else if(Math.abs(lastCol-NewCol)==0&&Math.abs(lastRow-NewRol)==3){
                    //左跳
                    if(lastRow > NewRol){
                        int Rn=0;
                        for(int i = 1;i<3;i++){
                            if(save.getGameBoard()[lastCol][lastRow-i].getType()==CellType.RIVER){
                                Rn +=1;
                            }else{
                                break;
                            }
                        }
                        if(Rn==2){
                            return true;
                        }else{
                            return false;
                        }

                    }else if(lastRow < NewRol){
                        int Rn=0;
                        for(int i = lastRow;i<NewRol;i++){
                            if(save.getGameBoard()[lastCol][i].getType()==CellType.RIVER){
                                Rn +=1;
                            }else{
                                break;
                            }
                        }
                        if(Rn==2){
                            return true;
                        }else{
                            return false;
                        }

                    }else{
                        return false;
                    }
            }else if(Math.abs(lastCol-NewCol)==1&&lastRow==NewRol){
                    if(save.getGameBoard()[NewCol][NewRol].getType()==CellType.RIVER){
                        return false;
                    }else{
                        return true;
                    }
            }else if(Math.abs(lastRow-NewRol)==1&& lastCol==NewCol){
                    if(save.getGameBoard()[NewCol][NewRol].getType()==CellType.RIVER){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return false;
                }
        } else if(Math.abs(rank)!=6 && Math.abs(rank)!=7 && Math.abs(rank)!=1){
                //是否为相邻四格
                //步长>2的区域
                if(Math.abs(lastCol-NewCol)>=2 & Math.abs(lastRow-NewRol)>=2){
                    return false;
                }else if(Math.abs(lastCol-NewCol)==1&&Math.abs(NewRol-lastRow)==0){
                    if(save.getGameBoard()[NewCol][NewRol].getType()==CellType.RIVER){
                        return false;
                    }else{
                        return true;
                    }
                }else if(Math.abs(lastCol-NewCol)==0&&Math.abs(NewRol-lastRow)==1){
                    if(save.getGameBoard()[NewCol][NewRol].getType()==CellType.RIVER){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return false;
            }
            }else{
                return false;
            }
        }
        public void UpdatePanels(){
            JLabel [][] Label =Transit(save, new MyLabelListener());
            panel1.removeAll();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    panel1.add(Label[i][j]);
                }
            }
            panel1.revalidate();
            panel1.repaint();
            panel1.updateUI();
            Component[] components = panel2.getComponents();
            for (Component comp : components) {
                if (comp instanceof JLabel ) {
                    JLabel Turns = (JLabel) comp;
                    if(save.getTurns()%2==0){
                        String turns="BLUE Turns "+save.getTurns()+"";
                        Turns.setText(turns);
                    }else{
                        String turns = "RED Turns "+save.getTurns()+"";
                        Turns.setText(turns);
                    }
        }
    }
        }
}




