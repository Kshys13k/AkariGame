package akari.tests;

import akari.GUI.MainFrame;
import akari.model.*;

/**
 * testing: Generator, Saver, Loader, Solver
 */
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
        saveBoard.save(2);
        board=loadBoard.load(2);
        System.out.println("Plansza wczytana z pliku:");
        engine.printBoard(board);
        Field[][] solvedBoard = solver.solve(board);
        System.out.println("Rozwiązanie: ");
        engine.printBoard(solvedBoard);
        System.out.println();
    }
}
/*
 */