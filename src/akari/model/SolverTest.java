package akari.model;

public class SolverTest extends Engine{
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        Solver solver = new Solver();
        Field[][] board = generator.generate(engine.boardSize());
        engine.printBoard(board);
        Field[][] solvedBoard = solver.solve(board);
        System.out.println("Rozwiązanie: ");
        engine.printBoard(solvedBoard);
        System.out.println();
    }
}
/*
 */