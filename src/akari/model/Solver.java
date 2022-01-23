package akari.model;

import java.util.ArrayList;
import java.util.List;

public class Solver extends Akari {

    private Akari akari = new Akari();
    private Solver solver = new Solver();

    public Solver(final Akari akari) {

    }

    public Solver() {

    }

    //metoda sprawdzająca czy pole jest ścianą
    public boolean isNotWall(Field[][] board, int row, int column) {
        Field field = board[row][column];
        return !(field == Field.WALL || field == Field.WALL0 || field == Field.WALL1 || field == Field.WALL2
                || field == Field.WALL3 || field == Field.WALL4);
    }
    //metoda sprawdzająca czy w sąsiedztwie nie ma ściany [0]
    public boolean isNotNextTo0(Field[][] board, int row, int column) {
        return !(board[row-1][column] == Field.WALL0 || board[row+1][column] == Field.WALL0
                || board[row][column-1] == Field.WALL0 || board[row][column+1] == Field.WALL0);
    }


    //sprawdza czy można zaświecić podejrzane pole
    public boolean canBeTurnedOn(Field[][] board, int x, int y){
        //jeśli pole podświetlone lub zawiera żarówkę to nie można:
        if(board[x][y] == Field.LIGHTED || board[x][y] == Field.LIGHTED2 || board[x][y] == Field.BULB) return false;
        //jeśli przekroczymy limit żarówek na sąsiedniej ścianie z numerem to nie można:
        //(x+1)(y)
        if(board[x+1][y] == Field.WALL1){
            if(akari.countBulbs(board,x+1,y)>=1) return false;
        }
        if(board[x+1][y] == Field.WALL2){
            if(akari.countBulbs(board,x+1,y)>=2) return false;
        }
        if(board[x+1][y] == Field.WALL3){
            if(akari.countBulbs(board,x+1,y)>=3) return false;
        }
        if(board[x+1][y] == Field.WALL4){
            if(akari.countBulbs(board,x+1,y)>=4) return false;
        }

        //(x)(y-1)
        if(board[x][y-1] == Field.WALL1){
            if(akari.countBulbs(board,x,y-1)>=1) return false;
        }
        if(board[x][y-1] == Field.WALL2){
            if(akari.countBulbs(board,x,y-1)>=2) return false;
        }
        if(board[x][y-1] == Field.WALL3){
            if(akari.countBulbs(board,x,y-1)>=3) return false;
        }
        if(board[x][y-1] == Field.WALL4){
            if(akari.countBulbs(board,x,y-1)>=4) return false;
        }

        //(x-1)
        if(board[x-1][y] == Field.WALL1){
            if(akari.countBulbs(board,x-1,y)>=1) return false;
        }
        if(board[x-1][y] == Field.WALL2){
            if(akari.countBulbs(board,x-1,y)>=2) return false;
        }
        if(board[x-1][y] == Field.WALL3){
            if(akari.countBulbs(board,x-1,y)>=3) return false;
        }
        if(board[x-1][y] == Field.WALL4){
            if(akari.countBulbs(board,x-1,y)>=4) return false;
        }

        //(x)(y+1)
        if(board[x][y+1] == Field.WALL1){
            if(akari.countBulbs(board,x,y+1)>=1) return false;
        }
        if(board[x][y+1] == Field.WALL2){
            if(akari.countBulbs(board,x,y+1)>=2) return false;
        }
        if(board[x][y+1] == Field.WALL3){
            if(akari.countBulbs(board,x,y+1)>=3) return false;
        }
        if(board[x][y+1] == Field.WALL4){
            if(akari.countBulbs(board,x,y+1)>=4) return false;
        }
        return true;
    }


    public Field[][] solve(Field[][] unsolvedBoard){
        //wyznaczanie miejsc gdzie mozna postawic żarówkę:
        Field[][] board= unsolvedBoard;
        List<SuspectedCell> listOfSuspectedCells= new ArrayList<SuspectedCell>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(solver.isNotWall(board,i,j) && solver.isNotNextTo0(board,i,j)) {
                    listOfSuspectedCells.add(new SuspectedCell(i,j));
                }
            }
        }

        //właściwy algorytm rozwiązujący planszę:
        int pointer= 0;
        int x,y;
        int limit=listOfSuspectedCells.size()-1;
        for(;;){
            System.out.println("\n"+pointer);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x=listOfSuspectedCells.get(pointer).getX();
            y=listOfSuspectedCells.get(pointer).getY();
            if(pointer<=limit){
                if(solver.canBeTurnedOn(board, x, y)){
                    akari.placeBulb(board,x,y);
                    if(!akari.endGame(board)) {
                        break;
                    }
                }
            }if(pointer==limit){
                if(board[x][y] == Field.BULB) akari.placeBulb(board,x,y);
                for(;;){
                    pointer--;
                    if(board[x][y] == Field.BULB) {
                        akari.placeBulb(board,x,y);
                        break;
                    }
                    if(pointer==0) return board;
                }
            }
            akari.printBoard(board);
           pointer++;
        }
            return board;
        }

    }

