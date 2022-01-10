/*
Klasa Generator zawiera metody niezbędne do wygenerowania planszy (tablicy) na której toczy się rozgrywka
Generator generuje tylko możliwe do rozwiązania plansze
Generator może wygenerować każdą możliwą do rozwiązania planszę
 */

package akari;

import java.util.Random;

public class Generator extends Akari {

    //metoda sprawdza czy dane pole jest ścianą i zwraca true jeżeli jest, a false jeżeli nie jest
    public static boolean isWall(String[][] board, int row, int column) {
        return board[row][column].equals(wall) || board[row][column].equals(wall0) || board[row][column].equals(wall1)
                || board[row][column].equals(wall2) || board[row][column].equals(wall3)
                || board[row][column].equals(wall4);
    }

    //metoda zlicza wszystkie ściany stykające się z danym polem
    public static int countWalls(String[][] board, int row, int column) {
        int counter = 0;
        if(isWall(board, row, column - 1)) {
            counter++;
        }
        if(isWall(board, row, column + 1)) {
            counter++;
        }
        if(isWall(board, row - 1, column)) {
            counter++;
        }
        if(isWall(board, row + 1, column)) {
            counter++;
        }
        return counter;
    }

    //metoda zlicza ile jest pustych pól na planszy
    public static int countEmpty(String[][] board) {
        int counter = 0;
        for(int i = 0; i < board.length - 2; i++) {
            for(int j = 0; j < board.length - 2; j++) {
                if(board[i + 1][j + 1].equals(empty)) {
                    counter++;
                }
            }
        }
        return counter;
    }

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

        Random random = new Random();
        int cells = (n - 2) * (n - 2); //liczba wszystkich pól na planszy
        int walls = random.nextInt(cells); //liczba generowanych ścian

        //pętla, która losowo umieszcza ściany na planszy
        for(int i = 0; i < walls; i++) {
            int position = random.nextInt(countEmpty(board)); //pozycja ściany na planszy
            int counter = 0;
            outer: for(int j = 0; j < board.length - 2; j++) {
                for(int k = 0; k < board.length - 2; k++) {
                    if(board[j + 1][k + 1].equals(empty)) {
                        if(position == counter) {
                            board[j + 1][k + 1] = wall;
                            break outer;
                        }
                        counter++;
                    }
                }
            }
        }

        //pętla, która losowo umieszcza żarówki na planszy
        while(countEmpty(board) > 0) {
            int position = random.nextInt(countEmpty(board)); //pozycja żarówki na planszy
            int counter = 0;
            outer: for(int i = 0; i < board.length - 2; i++) {
                for(int j = 0; j < board.length - 2; j++) {
                    if(board[i + 1][j + 1].equals(empty)) {
                        if(position == counter) {
                            placeBulb(board, i + 1, j + 1);
                            break outer;
                        }
                        counter++;
                    }
                }
            }
        }

        //pętla, która losuje, czy dana ściana będzie miała numer czy nie
        for(int j = 0; j < board.length - 2; j++) {
            for(int k = 0; k < board.length - 2; k++) {
               if(board[j + 1][k + 1].equals(wall)) {
                   if(countWalls(board, j + 1, k + 1) == 4) {
                       continue;
                   }
                   boolean toNumber = random.nextBoolean();
                   int wallNumber = countBulbs(board, j + 1, k + 1);
                   switch(wallNumber) {
                       case 0: {
                           if(toNumber) {
                               board[j + 1][k + 1] = wall0;
                               break;
                           }
                       }
                       case 1: {
                           if(toNumber) {
                               board[j + 1][k + 1] = wall1;
                               break;
                           }
                       }
                       case 2: {
                           if(toNumber) {
                               board[j + 1][k + 1] = wall2;
                               break;
                           }
                       }
                       case 3: {
                           if(toNumber) {
                               board[j + 1][k + 1] = wall3;
                               break;
                           }
                       }
                       case 4: {
                           if(toNumber) {
                               board[j + 1][k + 1] = wall4;
                               break;
                           }
                       }
                   }
               }
            }
        }
        reset(board);
        return board;
    }
}
