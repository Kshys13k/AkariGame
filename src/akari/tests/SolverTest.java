package akari.tests;

import akari.model.Engine;
import akari.model.Generator;
import akari.model.Solver;

public class SolverTest extends Engine {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        Solver solver = new Solver();
        Field[][] board = generator.generate(engine.boardSize(), 0.25, 0.75, 0.5);
        engine.printBoard(board);
        Field[][] solvedBoard = solver.solve(board);
        System.out.println("Rozwiązanie: ");
        engine.printBoard(solvedBoard);
        System.out.println();
    }
}
/*
 */