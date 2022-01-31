package akari.tests;

import akari.model.Engine;
import akari.model.Generator;

public class GameEngineTest extends Engine {

    /**
     * Testing akari's engine
     */

    public static void main(String[] args) {
        Engine engine = new Engine();
        Generator generator = new Generator();
        engine.intro();
        Field[][] board = generator.generate(engine.boardSize(), 0.25, 0.75, 0.5);
        while(engine.endGame(board)) {
            engine.printBoard(board);
            engine.input(board);
        }
        engine.winGame(board);
    }
}
