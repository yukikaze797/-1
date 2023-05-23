package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Animal.Person;
import Model.Chess.CellType;
import Model.Chess.ChessBoard;

public class GameFrame extends JFrame {
    private int saveTest;
    public ChessBoard save = new ChessBoard(new Person(-1),new Person(1));;//已经有的存档,使用默认存档
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
        GameBoard.setContentPane(new MainMenu(saveTest));
        GameBoard.setVisible(true);
    }

    //不要用下面那个方法，他只是传默认参数用的
    public GameFrame() {
        if (save == null) {
            saveTest = 0;
        } else {
            saveTest = 1;
        }
    }

    public static class MainMenu extends JPanel {
        private JButton Start = new JButton("开始游戏");
        private JButton Exit = new JButton("退出游戏");
        private JButton Continue = new JButton("继续游戏");
        private JButton Load = new JButton("读取存档");


        //具体的主菜单 0为无存档时的界面，1为有存档时的界面
        public MainMenu(int number) {
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
                switch (command) {
                    case "start":
                        JFrame playFrame = new PlayFrame(new GameFrame().oriSave);
                        Window windowS = SwingUtilities.getWindowAncestor(MainMenu.this);
                        if (windowS instanceof JFrame) {
                            JFrame frame = (JFrame) windowS;
                            frame.dispose();
                        }
                    case "exit":
                        Window windowE = SwingUtilities.getWindowAncestor(MainMenu.this);
                        if (windowE instanceof JFrame) {
                            JFrame frame = (JFrame) windowE;
                            frame.dispose();
                        }
                }
            }
        }
    }
    }