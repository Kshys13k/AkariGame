/*
Klasa Solver w budowie
 */

package akari;

import java.util.ArrayList;
import java.util.List;

public class Solver extends Akari {
    //metoda sprawdzająca czy pole jest ścianą
    public static boolean isNotWall(String[][] board, int row, int column) {
        return !(board[row][column].equals(wall) || board[row][column].equals(wall0) || board[row][column].equals(wall1)
                || board[row][column].equals(wall2) || board[row][column].equals(wall3)
                || board[row][column].equals(wall4));
    }
    //metoda sprawdzająca czy w sąsiedztwie nie ma ściany [0]
    public static boolean isNotNextTo0(String[][] board, int row, int column) {
        return !(board[row][column].equals(wall0) || board[row-1][column-1].equals(wall0) || board[row-1][column+1].equals(wall1)
                || board[row+1][column-1].equals(wall0) || board[row+1][column+1].equals(wall0));
    }


    //sprawdza czy można zaświecić podejrzane pole
    public static boolean canBeTurnedOn(String[][] board, int x, int y){
        //jeśli pole podświetlone lub zawiera żarówkę to nie można:
        if(board[x][y].equals(lighted)||board[x][y].equals(lighted2)||board[x][y].equals(bulb)) return false;
        //jeśli przekroczymy limit żarówek na sąsiedniej ścianie z numerem to nie można:
        //(x+1)(y+1)
        if(board[x+1][y+1].equals(wall1)){
            if(countBulbs(board,x+1,y+1)>=1) return false;
        }
        if(board[x+1][y+1].equals(wall2)){
            if(countBulbs(board,x+1,y+1)>=2) return false;
        }
        if(board[x+1][y+1].equals(wall3)){
            if(countBulbs(board,x+1,y+1)>=3) return false;
        }
        if(board[x+1][y+1].equals(wall4)){
            if(countBulbs(board,x+1,y+1)>=4) return false;
        }

        //(x+1)(y-1)
        if(board[x+1][y-1].equals(wall1)){
            if(countBulbs(board,x+1,y-1)>=1) return false;
        }
        if(board[x+1][y-1].equals(wall2)){
            if(countBulbs(board,x+1,y-1)>=2) return false;
        }
        if(board[x+1][y-1].equals(wall3)){
            if(countBulbs(board,x+1,y-1)>=3) return false;
        }
        if(board[x+1][y-1].equals(wall4)){
            if(countBulbs(board,x+1,y-1)>=4) return false;
        }

        //(x-1)(y+1)
        if(board[x-1][y+1].equals(wall1)){
            if(countBulbs(board,x+1,y+1)>=1) return false;
        }
        if(board[x-1][y+1].equals(wall2)){
            if(countBulbs(board,x+1,y+1)>=2) return false;
        }
        if(board[x-1][y+1].equals(wall3)){
            if(countBulbs(board,x+1,y+1)>=3) return false;
        }
        if(board[x-1][y+1].equals(wall4)){
            if(countBulbs(board,x+1,y+1)>=4) return false;
        }

        //(x-1)(y-1)
        if(board[x-1][y-1].equals(wall1)){
            if(countBulbs(board,x+1,y-1)>=1) return false;
        }
        if(board[x-1][y-1].equals(wall2)){
            if(countBulbs(board,x+1,y-1)>=2) return false;
        }
        if(board[x-1][y-1].equals(wall3)){
            if(countBulbs(board,x+1,y-1)>=3) return false;
        }
        if(board[x-1][y-1].equals(wall4)){
            if(countBulbs(board,x+1,y-1)>=4) return false;
        }
        return true;
    }


    public static String[][] solve(String[][] unsolvedBoard){
        //wyznaczanie miejsc gdzie mozna postawic żarówkę:
        String [][] board= unsolvedBoard;
        List<SuspectedCell> listOfSuspectedCells= new ArrayList<SuspectedCell>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(isNotWall(board,i,j) && isNotNextTo0(board,i,j)) {
                    listOfSuspectedCells.add(new SuspectedCell(i,j));
                }
            }
        }

        //właściwy algorytm rozwiązujący planszę:
        int pointer= 0;
        int x,y;
        int limit=listOfSuspectedCells.size()-1;
        for(;;){
            x=listOfSuspectedCells.get(pointer).getX();
            y=listOfSuspectedCells.get(pointer).getY();
            if(pointer<limit){
                if(canBeTurnedOn(board, x, y)){
                    placeBulb(board,x,y);
                    if(!endGame(board)) break;
                    pointer++;
                }
            }else{
                if(board[x][y].equals(bulb)) placeBulb(board,x,y);
                for(;;){
                    pointer--;
                    if(board[x][y].equals(bulb)) {
                        placeBulb(board,x,y);
                        pointer++;
                        break;
                    }
                    if(pointer==0) return board;
                }
            }
        }
            return board;
        }

    }

