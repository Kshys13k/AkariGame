package akari.model;

import java.util.ArrayList;
import java.util.List;

public class Solver extends Engine {


    public Solver(final Akari akari) {

    }

    public Solver() {

    }

    //metoda sprawdzająca czy pole jest ścianą, żarówką lub jest podświetlone, lub czy jest obok ściany [0]
    private boolean possibleBulb(Field[][] board, int row, int column){
        Field field = board[row][column];
        return !(field == Field.WALL || field == Field.WALL0 || field == Field.WALL1 || field == Field.WALL2
                || field == Field.WALL3 || field == Field.WALL4 || field == Field.BULB || field == Field.LIGHTED
                || field == Field.LIGHTED2 || board[row-1][column] == Field.WALL0 || board[row+1][column] == Field.WALL0
                || board[row][column-1] == Field.WALL0 || board[row][column+1] == Field.WALL0);
    }

    //sprawdza czy można zaświecić podejrzane pole
    private boolean canBeTurnedOn(Field[][] board, int x, int y){
        //jeśli pole podświetlone lub zawiera żarówkę to nie można:
        if(board[x][y] == Field.LIGHTED || board[x][y] == Field.LIGHTED2 || board[x][y] == Field.BULB) return false;
        //jeśli przekroczymy limit żarówek na sąsiedniej ścianie z numerem to nie można:
        //(x+1)(y)
        if(board[x+1][y] == Field.WALL1){
            if(countBulbs(board,x+1,y)>=1) return false;
        }
        if(board[x+1][y] == Field.WALL2){
            if(countBulbs(board,x+1,y)>=2) return false;
        }
        if(board[x+1][y] == Field.WALL3){
            if(countBulbs(board,x+1,y)>=3) return false;
        }
        if(board[x+1][y] == Field.WALL4){
            if(countBulbs(board,x+1,y)>=4) return false;
        }

        //(x)(y-1)
        if(board[x][y-1] == Field.WALL1){
            if(countBulbs(board,x,y-1)>=1) return false;
        }
        if(board[x][y-1] == Field.WALL2){
            if(countBulbs(board,x,y-1)>=2) return false;
        }
        if(board[x][y-1] == Field.WALL3){
            if(countBulbs(board,x,y-1)>=3) return false;
        }
        if(board[x][y-1] == Field.WALL4){
            if(countBulbs(board,x,y-1)>=4) return false;
        }

        //(x-1)
        if(board[x-1][y] == Field.WALL1){
            if(countBulbs(board,x-1,y)>=1) return false;
        }
        if(board[x-1][y] == Field.WALL2){
            if(countBulbs(board,x-1,y)>=2) return false;
        }
        if(board[x-1][y] == Field.WALL3){
            if(countBulbs(board,x-1,y)>=3) return false;
        }
        if(board[x-1][y] == Field.WALL4){
            if(countBulbs(board,x-1,y)>=4) return false;
        }

        //(x)(y+1)
        if(board[x][y+1] == Field.WALL1){
            if(countBulbs(board,x,y+1)>=1) return false;
        }
        if(board[x][y+1] == Field.WALL2){
            if(countBulbs(board,x,y+1)>=2) return false;
        }
        if(board[x][y+1] == Field.WALL3){
            if(countBulbs(board,x,y+1)>=3) return false;
        }
        if(board[x][y+1] == Field.WALL4){
            if(countBulbs(board,x,y+1)>=4) return false;
        }
        return true;
    }

    private void placeBulbOnAdjacentFieldsIfPossible(Field[][] board, int i, int j){
        if(i>1) if(canBeTurnedOn(board,i-1,j)) placeBulb(board,i-1,j);
        if(i<board.length-2) if(canBeTurnedOn(board,i+1,j)) placeBulb(board,i+1,j);
        if(j>1) if(canBeTurnedOn(board,i,j-1)) placeBulb(board,i,j-1);
        if(j<board.length-2) if(canBeTurnedOn(board,i,j+1)) placeBulb(board,i,j+1);
    }
    //krok 1 algorytmu
    private void step1(Field[][]board){
        boolean newBulb;
        int wallCounter;
        for(int a=0;a<10;a++){
            newBulb=false;
            for(int i = 1; i < board.length-1; i++) {
            for(int j = 1; j < board.length-1; j++) {
                wallCounter=0;
                if(!possibleBulb(board,i+1,j)) wallCounter++;
                if(!possibleBulb(board,i-1,j)) wallCounter++;
                if(!possibleBulb(board,i,j+1)) wallCounter++;
                if(!possibleBulb(board,i,j-1)) wallCounter++;

                if(board[i][j]==Field.WALL4) {
                    placeBulbOnAdjacentFieldsIfPossible(board,i,j);
                    newBulb=true;
                }
                if(board[i][j]==Field.WALL3 && wallCounter==1) {
                    placeBulbOnAdjacentFieldsIfPossible(board,i,j);
                    newBulb=true;
                }
                if(board[i][j]==Field.WALL2 && wallCounter==2) {
                    placeBulbOnAdjacentFieldsIfPossible(board,i,j);
                    newBulb=true;
                }
                if(board[i][j]==Field.WALL1 && wallCounter==3) {
                    placeBulbOnAdjacentFieldsIfPossible(board,i,j);
                    newBulb=true;
                }
//                printBoard(board);
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            }


            if(newBulb==false) break;
        }
    }

    private Field[][] bruteForce(Field [][] board){
        //wyznaczanie miejsc gdzie mozna postawic żarówkę:
        List<SuspectedCell> listOfSuspectedCells= new ArrayList<SuspectedCell>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(possibleBulb(board,i,j)) {
                    listOfSuspectedCells.add(new SuspectedCell(i,j));
                }
            }
        }

        //właściwy algorytm rozwiązujący planszę:
        int pointer= 0;
        int x,y;
        int limit=listOfSuspectedCells.size()-1;
        for(;;){

            //TEST
//            System.out.println("\n"+pointer);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            x=listOfSuspectedCells.get(pointer).getX();
            y=listOfSuspectedCells.get(pointer).getY();
            if(pointer<=limit){
                if(canBeTurnedOn(board, x, y)){
                    placeBulb(board,x,y);
                    if(!endGame(board)) {
                        //TEST
//                        System.out.println("gra spełniona");

                        break;
                    }
                }
            }if(pointer==limit){
                //TEST
//                System.out.println("pointer==limit");

                if(board[x][y] == Field.BULB) placeBulb(board,x,y);
                for(;;){
                    //TEST
//                    System.out.println("\n"+pointer);

                    pointer--;
                    x=listOfSuspectedCells.get(pointer).getX();
                    y=listOfSuspectedCells.get(pointer).getY();
                    if(board[x][y] == Field.BULB) {
                        //TEST
//                        System.out.println("usuwqam zarowke");


                        placeBulb(board,x,y);
                        break;
                    }
                    if(pointer==0) return board;
                }
            }
//            printBoard(board);
            pointer++;
        }
        return board;
    }



    public Field[][] solve(Field[][] unsolvedBoard){
        Field[][] board= unsolvedBoard;
        reset(board);

        //krok 1- wstawienie żarówek tam gdzie muszą być (JESZCZE NIE DZIAŁA!)
       // step1(board);
       // printBoard(board);
        //krok 2- brute forcowanie reszty
        bruteForce(board);
        return board;
        }
    }

