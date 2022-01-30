package akari.model;

import java.util.Scanner;

public class Engine {
    /*
    Zmienne określające dany typ elementu planszy
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



    public static String alphabet = "abcdefghijklmnopqrstuvwxyz"; /* alfabet, przydatny do
    orientacji na planszy
     */

    //metoda umożliwiająca wprowadzenie przez użytkownika wielkości planszy
    public int boardSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER BOARD SIZE (1 - 26): ");
        return scanner.nextInt();
    }

    //metoda resetująca planszę, usuwa wszystkie żarówki z planszy
    public void reset(Field[][] board) {
        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board.length - 2; j++) {
                if (board[i + 1][j + 1] == Field.BULB) {
                    placeBulb(board, i + 1, j + 1);
                }
            }
        }
    }

    //metoda wyświetla instrukcję
    public void intro() {
        System.out.println("AKARI\nTO PLACE/DISPLACE A BULB TYPE COORDINATES OF A CELL (FOR EXAMPLE: d2)");
        System.out.println("TYPE reset TO RESET THE BOARD");
        System.out.println("TYPE exit TO EXIT");
    }

    //metoda ta wyświetla planszę i napis "YOU WON!"
    public void winGame(Field[][] board) {
        printBoard(board);
        System.out.println("YOU WON!");
    }

    //zlicza wszystkie żarówki stykające się z danym polem,
    //przydatne w określeniu liczby żarówek wokół pól z numerami
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

    //metoda sprawdza czy spełnione są warunki niezbędne do ukończenia gry,
    //w przypadku spełnienia tych warunków zwraca false,
    //w przeciwnym wypadku, tj. gdy któryś z warunków nie jest spełniony zwraca true
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

    //metoda do wpisywania pól, na których umieszczane będą żarówki,
    //pola wpisuje się poprzez podanie litery kolumny i numeru rzędu (np. d2)
    //po wpisaniu reset plansza się resetuje
    //po wpisaniu exit program wyłącza się
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

    //metoda, która wywołuje planszę w konsoli
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


    //lightUp zmienia pola nieoświetlone w oświetlone
    public void lightUp(Field[][] board, int row, int column) {
        if (board[row][column] == Field.EMPTY) {
            board[row][column] = Field.LIGHTED;
        } else if (board[row][column] == Field.LIGHTED) {
            board[row][column] = Field.LIGHTED2;
        }
    }

    //lightDown zmienia pola oświetlone w nieoświetlone
    public void lightDown(Field[][] board, int row, int column) {
        if (board[row][column] == Field.LIGHTED2) {
            board[row][column] = Field.LIGHTED;
        } else if (board[row][column] == Field.LIGHTED) {
            board[row][column] = Field.EMPTY;
        }
    }

    //metoda sprawdza czy pole jest oświetlone i jeżeli nie jest to je oświetla i zwraca true
    //zwraca false w przeciwnym wypadku
    public boolean light(Field[][] board, int row, int column) {
        if (board[row][column] == Field.EMPTY || board[row][column] == Field.LIGHTED) {
            lightUp(board, row, column);
            return true;
        } else {
            return false;
        }
    }

    //metoda sprawdza czy pole jest oświetlone i jeżeli jest to je odświetla i zwraca true
    //zwraca false w przeciwnym wypadku
    public boolean unLight(Field[][] board, int row, int column) {
        if (board[row][column] == Field.LIGHTED || board[row][column] == Field.LIGHTED2) {
            lightDown(board, row, column);
            return true;
        } else {
            return false;
        }
    }

    //metoda umieszcza żarówkę na danym polu i oświetla wszystkie pola wokół
    //metoda również usuwa żarówkę z danego pola i odświetla wszystkie pola wokół
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
}
