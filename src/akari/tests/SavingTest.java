package akari.tests;

import akari.model.Engine;
import akari.model.Generator;
import akari.model.SaveBoard;

public class SavingTest extends Engine {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        Field[][] board = generator.generate(engine.boardSize(), 0.25, 0.75, 0.5);
        engine.printBoard(board);
        SaveBoard saveBoard= new SaveBoard(board);
        saveBoard.save(3);
        System.out.println();
    }
}
