package View;

import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
        //该窗口下用来存档的变量save,实际上就是正在操作的棋盘
        private ChessBoard save;
        private GameFrame gameFrame;
        //全局回放replay
    private ArrayList<ChessBoard> rep;
    //用来悔棋的replay
    private ArrayList<ChessBoard> rep1;

        public PlayFrame(GameFrame gameFrame) {
            ChessBoard board=gameFrame.getSaveData();
            if(board==null){
                save = new ChessBoard(new Person(-1),new Person(1));
            }else{
            save = board;
            }
            rep.add(save);
            rep1.add(save);
            frame.setSize(1280, 800);
            MyLabelListener listener =new MyLabelListener();
            JLabel [][] JBoard = Transit(save,listener);
            panel1 = drawBoard(JBoard);
            panel1.setLocation(400, 50);
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
            //
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
            private int lastClickedCol =-1;
            private int lastClickedRow =-1;
            @Override
            public void mouseClicked(MouseEvent e) {
                lastClickedCol =(int)Math.floor(e.getComponent().getX()/80);
                lastClickedRow =(int)Math.floor(e.getComponent().getY()/80);
                System.out.printf("%d,%d \n",lastClickedRow,lastClickedCol);
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
}



