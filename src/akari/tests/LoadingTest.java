package akari.tests;

import akari.GUI.GameFrame;
import akari.GUI.WrongCellTypeException;
import akari.model.Engine;
import akari.model.Generator;
import akari.model.LoadBoard;

public class LoadingTest extends Engine {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        LoadBoard loadBoard=new LoadBoard();
        Field[][] board=loadBoard.load(1);
        engine.printBoard(board);
        System.out.println();
    }
}