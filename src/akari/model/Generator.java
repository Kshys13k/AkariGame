package akari.model;

import java.util.Random;

public class Generator extends Engine {

    /**
     * isWall(Field[][] board, int row, int column) checks whether the field is a wall and returns true if it is,
     * else returns false
     */

    public boolean isWall(Field[][] board, int row, int column) {
        Field field = board[row][column];
        return field == Field.WALL || field == Field.WALL0 || field == Field.WALL1 || field == Field.WALL2
                || field == Field.WALL3 || field == Field.WALL4;
    }

    /**
     * countWalls(Field[][] board, int row, int column) counts all the walls adjacent to the field
     */

    public int countWalls(Field[][] board, int row, int column) {
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

    /**
     * countEmpty(Field[][] board) counts how many empty fields are on the board
     */

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

    /**
     * generate(int n, double wallsMin, double wallsMax, double toNumberChance) generates a random akari's board
     * @param n size of the board is n x n
     * @param wallsMin minimum number of walls is wallsMin * number of cells
     * @param wallsMax maximum number of walls is wallsMax * number of cells
     * @param toNumberChance chance of converting the wall into the wall with a number
     * @return generated board
     */

    public Field[][] generate(int n, double wallsMin, double wallsMax, double toNumberChance) {
        n += 2; // border

        /* n x n board, first and last rows and columns are filled with walls,
        they are omitted when printing the board */

        Field[][] board = new Field[n][n];
        board[0][0] = Field.WALL;
        board[0][board.length - 1] = Field.WALL;
        board[board.length - 1][0] = Field.WALL;
        board[board.length - 1][board.length - 1] = Field.WALL;

        // the loop fills first and last rows and columns with walls and the rest board with empty fields

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
        int cells = (n - 2) * (n - 2); // the number of all fields on the board

        // the number of generated walls
        int walls = random.nextInt((int)(cells * wallsMax - cells * wallsMin + 1)) + (int)(cells * wallsMin);

        // the loop that randomly fills the board with walls
        for(int i = 0; i < walls; i++) {
            int position = random.nextInt(countEmpty(board)); // randomly generated position of a wall on the board
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

        // the loop that randomly fills the board with bulbs
        while(countEmpty(board) > 0) {
            int position = random.nextInt(countEmpty(board)); // randomly generated position of a bulb on the board
            int counter = 0;
            outer: for(int i = 0; i < board.length - 2; i++) {
                for(int j = 0; j < board.length - 2; j++) {
                    if(board[i + 1][j + 1] == Field.EMPTY) {
                        if(position == counter) {
                            placeBulb(board, i + 1, j + 1);
                            break outer;
                        }
                        counter++;
                    }
                }
            }
        }

        // the loop that randomly converts the wall into the wall with a number
        for(int j = 0; j < board.length - 2; j++) {
            for(int k = 0; k < board.length - 2; k++) {
                if(board[j + 1][k + 1] == Field.WALL) {
                    if(countWalls(board, j + 1, k + 1) == 4) {
                        continue;
                    }
                    double toNumber = random.nextDouble();
                    int wallNumber = countBulbs(board, j + 1, k + 1);
                    switch(wallNumber) {
                        case 0: {
                            if(toNumber < toNumberChance) {
                                board[j + 1][k + 1] = Field.WALL0;
                                break;
                            }
                        }
                        case 1: {
                            if(toNumber < toNumberChance) {
                                board[j + 1][k + 1] = Field.WALL1;
                                break;
                            }
                        }
                        case 2: {
                            if(toNumber < toNumberChance) {
                                board[j + 1][k + 1] = Field.WALL2;
                                break;
                            }
                        }
                        case 3: {
                            if(toNumber < toNumberChance) {
                                board[j + 1][k + 1] = Field.WALL3;
                                break;
                            }
                        }
                        case 4: {
                            if(toNumber < toNumberChance) {
                                board[j + 1][k + 1] = Field.WALL4;
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
