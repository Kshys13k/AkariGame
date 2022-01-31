package akari.tests;

import akari.model.Engine;
import akari.model.Generator;

public class GameEngineTest extends Engine {

    /*
    W mainie znajduje się plansza z generatora i pętla kończąca się po prawidłowym ustawieniu
    wszystkich żarówek. Pętla wywołuje planszę oraz metodę input
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
