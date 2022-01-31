package akari.model;

import java.util.Scanner;

public class Engine {

    /**
     * enums contain types of the akari's field
     * empty - an empty field
     * bulb - a field with a bulb
     * lighted - a field lighted by a bulb
     * lighted2 - a field lighted by two bulbs
     * wall - a field with a wall without any number
     * wall 0,1,2,3,4 - a field with a wall with number 0,1,2,3,4
     */

    public enum Field {
        EMPTY,
        BULB,
        LIGHTED,
        LIGHTED2,
        WALL,
        WALL0,
        WALL1,
        WALL2,
        WALL3,
        WALL4,
    }
    private Engine.Field[][] board;

    public Field[][] getBoard() {
        return board;
    }

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    /**
     * alphabet, useful for orientation on the board
     */

    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * boardSize() allows a user to enter the size of a board
     */

    public int boardSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER BOARD SIZE (1 - 26): ");
        return scanner.nextInt();
    }

    /**
     * reset(Field[][] board) resets the board, by displacing all bulbs
     */

    public void reset(Field[][] board) {
        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board.length - 2; j++) {
                if (board[i + 1][j + 1] == Field.BULB) {
                    placeBulb(board, i + 1, j + 1);
                }
            }
        }
    }

    /**
     * intro() prints the instruction
     */

    public void intro() {
        System.out.println("AKARI\nTO PLACE/DISPLACE A BULB TYPE COORDINATES OF A CELL (FOR EXAMPLE: d2)");
        System.out.println("TYPE reset TO RESET THE BOARD");
        System.out.println("TYPE exit TO EXIT");
    }

    /**
     * winGame(Field[][] board) prints the board and String "YOU WON!"
     */

    public void winGame(Field[][] board) {
        printBoard(board);
        System.out.println("YOU WON!");
    }

    /**
     * countBulbs(Field[][] board, int row, int column) counts all the bulbs adjacent to the field
     */

    public int countBulbs(Field[][] board, int row, int column) {
        int counter = 0;
        if (board[row][column - 1] == Field.BULB) {
            counter++;
        }
        if (board[row][column + 1] == Field.BULB) {
            counter++;
        }
        if (board[row - 1][column] == Field.BULB) {
            counter++;
        }
        if (board[row + 1][column] == Field.BULB) {
            counter++;
        }
        return counter;
    }

    /**
     * endGame(Field[][] board) checks whether the conditions to win the game are met and if they are returns false
     * otherwise returns true
     */

    public boolean endGame(Field[][] board) {
        boolean bool = false;
        outer:
        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board.length - 2; j++) {
                int bulbsCount = countBulbs(board, i + 1, j + 1);
                Field field = board[i + 1][j + 1];
                if (field == Field.EMPTY) {
                    bool = true;
                    break outer;
                } else if (field == Field.WALL0 && bulbsCount != 0) {
                    bool = true;
                    break outer;
                } else if (field == Field.WALL1 && bulbsCount != 1) {
                    bool = true;
                    break outer;
                } else if (field == Field.WALL2 && bulbsCount != 2) {
                    bool = true;
                    break outer;
                } else if (field == Field.WALL3 && bulbsCount != 3) {
                    bool = true;
                    break outer;
                } else if (field == Field.WALL4 && bulbsCount != 4) {
                    bool = true;
                    break outer;
                }
            }
        }
        return bool;
    }

    /**
     * input(Field[][] board) allows a user to enter the field on which the bulb will be placed/displaced
     * to enter a field type column letter and row number
     * type exit to exit
     * type reset to reset the board
     */

    public void input(Field[][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SELECT CELL: ");
        String cell = scanner.nextLine();
        if (cell.equals("exit")) {
            System.exit(0);
        } else if (cell.equals("reset")) {
            reset(board);
        } else {
            int row = Integer.parseInt(cell.substring(1));
            int column = 1;
            for (int i = 0; i < alphabet.length(); i++) {
                if (cell.substring(0, 1).equals(alphabet.substring(i, i + 1))) {
                    break;
                }
                column++;
            }
            placeBulb(board, row, column);
        }
    }


    /**
     * printBoard(Field[][] board) prints the board in the terminal
     */

    public void printBoard(Field[][] board) {
        System.out.print("   ");
        for (int i = 0; i < board.length - 2; i++) {
            System.out.print(" " + alphabet.charAt(i) + " ");

        }
        System.out.println();
        for (int i = 0; i < board.length - 2; i++) {
            if (i < 9) {
                System.out.print(i + 1 + "  ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < board.length - 2; j++) {
                Field field = board[i + 1][j + 1];
                String fieldAsString;
                fieldAsString = switch (field) {
                    case EMPTY -> " ";
                    case BULB -> "*";
                    case LIGHTED, LIGHTED2 -> ".";
                    case WALL -> "#";
                    case WALL0 -> "0";
                    case WALL1 -> "1";
                    case WALL2 -> "2";
                    case WALL3 -> "3";
                    case WALL4 -> "4";
                };
                System.out.print("[" + fieldAsString + "]");
            }
            System.out.println();

        }
    }


    /**
     * lightUp(Field[][] board, int row, int column) turns an empty field into lighted
     * and a lighted field into lighted2
     */

    public void lightUp(Field[][] board, int row, int column) {
        if (board[row][column] == Field.EMPTY) {
            board[row][column] = Field.LIGHTED;
        } else if (board[row][column] == Field.LIGHTED) {
            board[row][column] = Field.LIGHTED2;
        }
    }

    /**
     * lightDown(Field[][] board, int row, int column) turns a lighted field into empty
     * and a lighted2 field into lighted
     */

    public void lightDown(Field[][] board, int row, int column) {
        if (board[row][column] == Field.LIGHTED2) {
            board[row][column] = Field.LIGHTED;
        } else if (board[row][column] == Field.LIGHTED) {
            board[row][column] = Field.EMPTY;
        }
    }

    /**
     * light(Field[][] board, int row, int column) checks whether the field is empty or lighted
     * and if it is, lights it up and returns true,
     * else returns false
     */

    public boolean light(Field[][] board, int row, int column) {
        if (board[row][column] == Field.EMPTY || board[row][column] == Field.LIGHTED) {
            lightUp(board, row, column);
            return true;
        } else {
            return false;
        }
    }

    /**
     * unLight(Field[][] board, int row, int column) checks whether the field is lighted or lighted2
     * and if it is, lights it down and returns true,
     * else returns false
     */

    public boolean unLight(Field[][] board, int row, int column) {
        if (board[row][column] == Field.LIGHTED || board[row][column] == Field.LIGHTED2) {
            lightDown(board, row, column);
            return true;
        } else {
            return false;
        }
    }

    /**
     * placeBulb(Field[][] board, int row, int column) checks whether the field is empty
     * and if it is, places the bulb in it,
     * else if the field already contains the bulb, displaces the bulb
     */

    public void placeBulb(Field[][] board, int row, int column) {
        boolean left = true;
        boolean right = true;
        boolean up = true;
        boolean down = true;
        if (board[row][column] == Field.EMPTY) {
            board[row][column] = Field.BULB;
            for (int i = 0; left; i++) {
                left = light(board, row, column - i - 1);
            }
            for (int i = 0; right; i++) {
                right = light(board, row, column + i + 1);
            }
            for (int i = 0; up; i++) {
                up = light(board, row - i - 1, column);
            }
            for (int i = 0; down; i++) {
                down = light(board, row + i + 1, column);
            }
        } else if (board[row][column] == Field.BULB) {
            board[row][column] = Field.EMPTY;
            for (int i = 0; left; i++) {
                left = unLight(board, row, column - i - 1);
            }
            for (int i = 0; right; i++) {
                right = unLight(board, row, column + i + 1);
            }
            for (int i = 0; up; i++) {
                up = unLight(board, row - i - 1, column);
            }
            for (int i = 0; down; i++) {
                down = unLight(board, row + i + 1, column);
            }
        }
    }
    public void generateBoard(int size, float wallsMin, float wallsMax, float toNumberChance){
        Generator generator = new Generator();
        this.board = generator.generate(size, wallsMin, wallsMax, toNumberChance);
    }
}
