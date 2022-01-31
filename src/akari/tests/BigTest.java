package akari.tests;

import akari.GUI.MainFrame;
import akari.model.*;

//solvuje wygenerowaną, zapisaną i odczytaną planszę
public class BigTest extends Engine {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        Solver solver = new Solver();
        SaveBoard saveBoard= new SaveBoard();
        LoadBoard loadBoard= new LoadBoard();
        Field[][] board = generator.generate(engine.boardSize(), 0.25, 0.75, 0.5);
        System.out.println("Wygenerowana plansza:");
        engine.printBoard(board);
        saveBoard.setBoard(board);
        saveBoard.save(10);
        board=loadBoard.load(10);
        System.out.println("Plansza wczytana z pliku:");
        engine.printBoard(board);
        Field[][] solvedBoard = solver.solve(board);
        System.out.println("Rozwiązanie: ");
        engine.printBoard(solvedBoard);
        System.out.println();
    }

    public static class MainFrameTest {
        public static void main(String[] args) {
            MainFrame frame = new MainFrame();
            frame.pack();
            frame.setVisible(true);
        }
    }
}
/*
 */