/*
Klasa Generator zawiera metody niezbędne do wygenerowania planszy (tablicy) na której toczy się rozgrywka

Klasa w trakcie budowy
 */

package akari;

public class Generator extends Akari {

    //metoda generująca planszę o wymiarach (n - 2) X (n - 2)
    public static String[][] generate(int n) {

        //tablica n X n, pierwsze i ostatnie rzędy i kolumny są ścianami, są one pomijane
        //w metodzie printBoard(); przydatne w innych metodach
        String[][] board = new String[n][n];
        board[0][0] = wall;
        board[0][board.length - 1] = wall;
        board[board.length - 1][0] = wall;
        board[board.length - 1][board.length - 1] = wall;

        //pętla wypełnia pierwsze i ostatnie rzędy i kolumny ścianami (wall), a resztę tablicy empty
        for(int i = 0; i < board.length - 2; i++) {
            board[0][i + 1] = wall;
            board[i + 1][0] = wall;
            board[board.length - 1][i + 1] = wall;
            board[i + 1][board.length - 1] = wall;
            for(int j = 0; j < board.length - 2; j++) {
                board[i + 1][j + 1] = empty;
            }
        }

        /*
        część pól ustawione jest jako różne ściany do testów
         */
        board[5][5] = wall;
        board[4][5] = wall;
        board[5][4] = wall;
        board[2][8] = wall;
        board[3][4] = wall;
        board[1][3] = wall0;
        board[5][2] = wall1;
        board[8][4] = wall2;
        board[3][2] = wall3;
        board[4][7] = wall4;
        return board;
    }
}
