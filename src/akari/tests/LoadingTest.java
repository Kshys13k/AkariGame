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

    public static class GameFrameTest {
        public static void main(String[] args) throws WrongCellTypeException {

            GameFrame frame = new GameFrame(9, (float) 0.35,(float) 0.5, (float) 0.6);
            frame.pack();
            frame.setVisible(true);

        }
    }
}