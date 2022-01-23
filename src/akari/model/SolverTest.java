package akari.model;

public class SolverTest extends Akari{
    public static void main(String[] args) {
        Akari akari = new Akari();
        Generator generator = new Generator();
        Solver solver = new Solver();
        Field[][] board = generator.generate(akari.boardSize());

        akari.printBoard(board);
        Field[][] solvedboard = solver.solve(board);
        System.out.println("Rozwiązanie: ");
        akari.printBoard(solvedboard);
        System.out.println();
    }
}
