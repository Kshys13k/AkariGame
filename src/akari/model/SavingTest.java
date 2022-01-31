package akari.model;

public class SavingTest extends Engine{
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        Field[][] board = generator.generate(engine.boardSize(), 0.25, 0.75, 0.5);
        engine.printBoard(board);
        SaveBoard saveBoard= new SaveBoard(board);
        saveBoard.save(1);
        System.out.println();
    }
}
