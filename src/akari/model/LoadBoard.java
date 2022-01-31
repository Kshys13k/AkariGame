package akari.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * loading board from .csv file
 */
public class LoadBoard {

    public LoadBoard(){
    }

    /**
     * converting string to board
     * @param boardCSV board as a string
     * @return returns board converted from a special String
     */
    private Engine.Field[][] CSVtoBoard(String boardCSV) {
        String[]rows=boardCSV.split("N");
        Engine.Field[][] board=new Engine.Field[rows.length+2][rows.length+2];

        //krawędzie planszy to ściany
        for(int i=0;i<rows.length+2;i++){
            board[0][i]= Engine.Field.WALL;
            board[rows.length+1][i]=Engine.Field.WALL;
            board[i][0]= Engine.Field.WALL;
            board[i][rows.length+1]=Engine.Field.WALL;
        }

        //wypełniamy planszę
        int i=0;
        for(String row:rows){
            String[] cells=row.split(",");
            int j=0;
            for(String cell:cells){
                switch(cell){
                    case "[#]"-> board[i+1][j+1]=Engine.Field.WALL;
                    case "[1]"-> board[i+1][j+1]=Engine.Field.WALL1;
                    case "[2]"-> board[i+1][j+1]=Engine.Field.WALL2;
                    case "[3]"-> board[i+1][j+1]=Engine.Field.WALL3;
                    case "[4]"-> board[i+1][j+1]=Engine.Field.WALL4;
                    case "[0]"-> board[i+1][j+1]=Engine.Field.WALL0;

                    case "[ ]"-> board[i+1][j+1]=Engine.Field.EMPTY;

                    case "[*]"-> board[i+1][j+1]=Engine.Field.BULB;

                    case "[:]"->  board[i+1][j+1]=Engine.Field.LIGHTED2;

                    case "[.]"-> board[i+1][j+1]=Engine.Field.LIGHTED;
                    default-> throw new RuntimeException("Błędna zawartość pliku");
                }
                j++;
            }
            i++;
        }
        return board;
}

    /**
     * loading board from .csv file
     * @param saveNumber number of save
     * @return returns loaded board from csv file
     */
    public Engine.Field[][] load(Integer saveNumber){
        StringBuilder resultString = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./saves/save"+saveNumber.toString()+".csv"));
            String line ;
            while ((line = br.readLine()) != null){
                resultString.append(line);
                resultString.append("N");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultString = new StringBuilder(resultString.substring(0, resultString.length() - 1));

        return CSVtoBoard(resultString.toString());
    }

}
