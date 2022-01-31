/*
Główna klasa Akari
Znajdują się tu zmienne i metody niezbędne do realizacji programu
Gra ta polega na ustawianiu żarówek na planszy
plansza składa się z pustych pól (komórek) na których można umieszczać żarówki i ścian
umieszczanie żarówek polega na wpisywaniu odpowiednich adresów pól
poszczególne pola zapisywane są w tablicy dwuwymiarowej
 */

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
