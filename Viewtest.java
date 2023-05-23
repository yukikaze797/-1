package View;
import javax.swing.*;
import Model.Animal.Person;
import Model.Chess.ChessBoard;
import Model.Chess.LandCell;

import java.awt.*;

public class Viewtest {
    public static void main(String[] args){

        Person RED=new Person(-1);
        Person BLUE = new Person(1);
        ChessBoard b1 = new ChessBoard(RED,BLUE);
        JFrame TestBoard1 = new GameFrame(b1);
        //JFrame p =new GameFrame.PlayFrame(b1);
        for(int i=0;i<9;i++){
            for(int j=0;j<7;j++){
                System.out.print(b1.getGameBoard()[i][j].getColor()+" ");
            }
        System.out.println();
        }
    for(int i=0;i<9;i++){
        for(int j=0;j<7;j++){
            System.out.print(b1.getGameBoard()[i][j].isHasChess()+" ");
        }
        System.out.println();}

}
}
