/*
Klasa Generator zawiera metody niezbędne do wygenerowania planszy (tablicy) na której toczy się rozgrywka
Generator generuje tylko możliwe do rozwiązania plansze
Generator może wygenerować każdą możliwą do rozwiązania planszę
 */

package akari.model;

import java.util.Random;

public class Generator extends Akari {
    private Akari akari = new Akari();
    private Generator generator = new Generator();

    //metoda sprawdza czy dane pole jest ścianą i zwraca true jeżeli jest, a false jeżeli nie jest
    public boolean isWall(Field[][] board, int row, int column) {
        Field field = board[row][column];
        return field == Field.WALL || field == Field.WALL0 || field == Field.WALL1 || field == Field.WALL2
                || field == Field.WALL3 || field == Field.WALL4;
    }

    //metoda zlicza wszystkie ściany stykające się z danym polem
    public int countWalls(Field[][] board, int row, int column) {
        int counter = 0;
        if(generator.isWall(board, row, column - 1)) {
            counter++;
        }
        if(generator.isWall(board, row, column + 1)) {
            counter++;
        }
        if(generator.isWall(board, row - 1, column)) {
            counter++;
        }
        if(generator.isWall(board, row + 1, column)) {
            counter++;
        }
        return counter;
    }

    //metoda zlicza ile jest pustych pól na planszy
    public int countEmpty(Field[][] board) {
        int counter = 0;
        for(int i = 0; i < board.length - 2; i++) {
            for(int j = 0; j < board.length - 2; j++) {
                if(board[i + 1][j + 1] == Field.EMPTY) {
                    counter++;
                }
            }
        }
        return counter;
    }

    //metoda generująca planszę o wymiarach (n - 2) X (n - 2)
    public Field[][] generate(int n) {

        //tablica n X n, pierwsze i ostatnie rzędy i kolumny są ścianami, są one pomijane
        //w metodzie printBoard(); przydatne w innych metodach
        Field[][] board = new Field[n][n];
        board[0][0] = Field.WALL;
        board[0][board.length - 1] = Field.WALL;
        board[board.length - 1][0] = Field.WALL;
        board[board.length - 1][board.length - 1] = Field.WALL;

        //pętla wypełnia pierwsze i ostatnie rzędy i kolumny ścianami (wall), a resztę tablicy empty
        for(int i = 0; i < board.length - 2; i++) {
            board[0][i + 1] = Field.WALL;
            board[i + 1][0] = Field.WALL;
            board[board.length - 1][i + 1] = Field.WALL;
            board[i + 1][board.length - 1] = Field.WALL;
            for(int j = 0; j < board.length - 2; j++) {
                board[i + 1][j + 1] = Field.EMPTY;
            }
        }

        Random random = new Random();
        int cells = (n - 2) * (n - 2); //liczba wszystkich pól na planszy
        int walls = random.nextInt((int)(cells * 0.75 - cells * 0.25 + 1)) + (int)(cells * 0.25); //liczba generowanych ścian

        //pętla, która losowo umieszcza ściany na planszy
        for(int i = 0; i < walls; i++) {
            int position = random.nextInt(generator.countEmpty(board)); //pozycja ściany na planszy
            int counter = 0;
            outer: for(int j = 0; j < board.length - 2; j++) {
                for(int k = 0; k < board.length - 2; k++) {
                    if(board[j + 1][k + 1] == Field.EMPTY) {
                        if(position == counter) {
                            board[j + 1][k + 1] = Field.WALL;
                            break outer;
                        }
                        counter++;
                    }
                }
            }
        }

        //pętla, która losowo umieszcza żarówki na planszy
        while(generator.countEmpty(board) > 0) {
            int position = random.nextInt(generator.countEmpty(board)); //pozycja żarówki na planszy
            int counter = 0;
            outer: for(int i = 0; i < board.length - 2; i++) {
                for(int j = 0; j < board.length - 2; j++) {
                    if(board[i + 1][j + 1] == Field.EMPTY) {
                        if(position == counter) {
                            akari.placeBulb(board, i + 1, j + 1);
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
               if(board[j + 1][k + 1] == Field.WALL) {
                   if(generator.countWalls(board, j + 1, k + 1) == 4) {
                       continue;
                   }
                   boolean toNumber = random.nextBoolean();
                   int wallNumber = akari.countBulbs(board, j + 1, k + 1);
                   switch(wallNumber) {
                       case 0: {
                           if(toNumber) {
                               board[j + 1][k + 1] = Field.WALL0;
                               break;
                           }
                       }
                       case 1: {
                           if(toNumber) {
                               board[j + 1][k + 1] = Field.WALL1;
                               break;
                           }
                       }
                       case 2: {
                           if(toNumber) {
                               board[j + 1][k + 1] = Field.WALL2;
                               break;
                           }
                       }
                       case 3: {
                           if(toNumber) {
                               board[j + 1][k + 1] = Field.WALL3;
                               break;
                           }
                       }
                       case 4: {
                           if(toNumber) {
                               board[j + 1][k + 1] = Field.WALL4;
                               break;
                           }
                       }
                   }
               }
            }
        }
        akari.reset(board);
        return board;
    }
}
