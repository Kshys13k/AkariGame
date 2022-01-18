/*
Główna klasa Akari
Znajdują się tu zmienne i metody niezbędne do realizacji programu
Gra ta polega na ustawianiu żarówek na planszy
plansza składa się z pustych pól (komórek) na których można umieszczać żarówki i ścian
umieszczanie żarówek polega na wpisywaniu odpowiednich adresów pól
poszczególne pola zapisywane są w tablicy dwuwymiarowej
 */

package akari;

import java.util.Scanner;

public class Akari {

    /*
    Zmienne określające dany typ elementu planszy
     */

    public static String empty = " "; //pusty element planszy
    public static String bulb = "*"; //żarówka
    public static String lighted = "."; //pole oświetlone przez jedną żarówkę
    public static String lighted2 = ":"; //pole oświetlone przez dwie żarówki
    public static String wall = "#"; //ściana
    public static String wall0 = "0"; //ściana z 0
    public static String wall1 = "1"; //ściana z 1
    public static String wall2 = "2"; //ściana z 2
    public static String wall3 = "3"; //ściana z 3
    public static String wall4 = "4"; //ściana z 4
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz"; /* alfabet, przydatny do
    orientacji na planszy
     */

    //metoda umożliwiająca wprowadzenie przez użytkownika wielkości planszy
    public static int boardSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ENTER BOARD SIZE (1 - 26): ");
        return scanner.nextInt() + 2;
    }

    //metoda resetująca planszę, usuwa wszystkie żarówki z planszy
    public static void reset(String[][] board) {
        for(int i = 0; i < board.length - 2; i++) {
            for(int j = 0; j < board.length - 2; j++) {
                if(board[i + 1][j + 1].equals(bulb)) {
                    placeBulb(board, i + 1, j + 1);
                }
            }
        }
    }

    //metoda wyświetla instrukcję
    public static void intro() {
        System.out.println("AKARI\nTO PLACE/UNPLACE A BULB TYPE COORDINATES OF A CELL (FOR EXAMPLE: d2)");
        System.out.println("TYPE reset TO RESET THE BOARD");
        System.out.println("TYPE exit TO EXIT");
    }

    //metoda ta wyświetla planszę i napis "YOU WON!"
    public static void winGame(String[][] board) {
        printBoard(board);
        System.out.println("YOU WON!");
    }

    //zlicza wszystkie żarówki stykające się z danym polem,
    //przydatne w określeniu liczby żarówek wokół pól z numerami
    public static int countBulbs(String[][] board, int row, int column) {
        int counter = 0;
        if(board[row][column - 1].equals(bulb)) {
            counter++;
        }
        if(board[row][column + 1].equals(bulb)) {
            counter++;
        }
        if(board[row - 1][column].equals(bulb)) {
            counter++;
        }
        if(board[row + 1][column].equals(bulb)) {
            counter++;
        }
        return counter;
    }

    //metoda sprawdza czy spełnione są warunki niezbędne do ukończenia gry,
    //w przypadku spełnienia tych warunków zwraca false,
    //w przeciwnym wypadku, tj. gdy któryś z warunków nie jest spełniony zwraca true
    public static boolean endGame(String[][] board) {
        boolean bool = false;
        outer: for(int i = 0; i < board.length - 2; i++) {
            for(int j = 0; j < board.length - 2; j++) {
                if(board[i + 1][j + 1].equals(empty)) {
                    bool = true;
                    break outer;
                }
                else if(board[i + 1][j + 1].equals(wall0) && countBulbs(board, i + 1, j + 1) != 0) {
                    bool = true;
                    break outer;
                }
                else if(board[i + 1][j + 1].equals(wall1) && countBulbs(board, i + 1, j + 1) != 1) {
                    bool = true;
                    break outer;
                }
                else if(board[i + 1][j + 1].equals(wall2) && countBulbs(board, i + 1, j + 1) != 2) {
                    bool = true;
                    break outer;
                }
                else if(board[i + 1][j + 1].equals(wall3) && countBulbs(board, i + 1, j + 1) != 3) {
                    bool = true;
                    break outer;
                }
                else if(board[i + 1][j + 1].equals(wall4) && countBulbs(board, i + 1, j + 1) != 4) {
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
    public static void input(String[][] board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SELECT CELL: ");
        String cell = scanner.nextLine();
        if(cell.equals("exit")) {
            System.exit(0);
        }
        else if(cell.equals("reset")) {
            reset(board);
        }
        else {
            int row = Integer.parseInt(cell.substring(1, 2));
            int column = 1;
            for(int i = 0; i < alphabet.length(); i++) {
                if(cell.substring(0, 1).equals(alphabet.substring(i, i + 1))) {
                    break;
                }
                column++;
            }
            placeBulb(board, row, column);
        }
    }

    //metoda, która wywołuje planszę
    public static void printBoard(String[][] board) {
        System.out.print("   ");
        for(int i = 0; i < board.length - 2; i++) {
            System.out.print(" " + alphabet.charAt(i) + " ");
        }
        System.out.println();
        for(int i = 0; i < board.length - 2; i++) {
            if(i < 9) {
                System.out.print(i + 1 + "  ");
            }
            else {
                System.out.print(i + 1 + " ");
            }
            for(int j = 0; j < board.length - 2; j++) {
                System.out.print("[" + board[i + 1][j + 1] + "]");
            }
            System.out.println();
        }
    }

    //lightUp zmienia pola nieoświetlone w oświetlone
    public static void lightUp(String[][] board, int row, int column) {
        if(board[row][column].equals(empty)) {
            board[row][column] = lighted;
        }
        else if(board[row][column].equals(lighted)) {
            board[row][column] = lighted2;
        }
    }

    //lightDown zmienia pola oświetlone w nieoświetlone
    public static void lightDown(String[][] board, int row, int column) {
        if(board[row][column].equals(lighted2)) {
            board[row][column] = lighted;
        }
        else if(board[row][column].equals(lighted)) {
            board[row][column] = empty;
        }
    }

    //metoda sprawdza czy pole jest oświetlone i jeżeli nie jest to je oświetla i zwraca true
    //zwraca false w przeciwnym wypadku
    public static boolean light(String[][] board, int row, int column) {
        if(board[row][column].equals(empty) || board[row][column].equals(lighted)) {
            lightUp(board, row, column);
            return true;
        }
        else {
            return false;
        }
    }

    //metoda sprawdza czy pole jest oświetlone i jeżeli jest to je odświetla i zwraca true
    //zwraca false w przeciwnym wypadku
    public static boolean unLight(String[][] board, int row, int column) {
        if(board[row][column].equals(lighted) || board[row][column].equals(lighted2)) {
            lightDown(board, row, column);
            return true;
        }
        else {
            return false;
        }
    }

    //metoda umieszcza żarówkę na danym polu i oświetla wszystkie pola wokół
    //metoda również usuwa żarówkę z danego pola i odświetla wszystkie pola wokół
    public static void placeBulb(String[][] board, int row, int column) {
        boolean left = true;
        boolean right = true;
        boolean up = true;
        boolean down = true;
        if(board[row][column].equals(empty)) {
            board[row][column] = bulb;
            for(int i = 0; left; i++) {
                left = light(board, row, column - i - 1);
            }
            for(int i = 0; right; i++) {
                right = light(board, row, column + i + 1);
            }
            for(int i = 0; up; i++) {
                up = light(board, row - i - 1, column);
            }
            for(int i = 0; down; i++) {
                down = light(board, row + i + 1, column);
            }
        }
        else if(board[row][column].equals(bulb)) {
            board[row][column] = empty;
            for(int i = 0; left; i++) {
                left = unLight(board, row, column - i - 1);
            }
            for(int i = 0; right; i++) {
                right = unLight(board, row, column + i + 1);
            }
            for(int i = 0; up; i++) {
                up = unLight(board, row - i - 1, column);
            }
            for(int i = 0; down; i++) {
                down = unLight(board, row + i + 1, column);
            }
        }
    }

    /*
    W mainie znajduje się plansza z generatora i pętla kończąca się po prawidłowym ustawieniu
    wszystkich żarówek. Pętla wywołuje planszę oraz metodę input
     */

    public static void main(String[] args) {
        intro();
        String[][] board = Generator.generate(boardSize());
        while(endGame(board)) {
            printBoard(board);
            input(board);
        }
        winGame(board);
    }
}
