package akari.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * loading board from .csv file
 */
public class LoadBoard {

    public LoadBoard(){
    }

    /**
     * converting string to board
     * @param boardCSV board as a string
     * @return
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
        int j=0;
        for(String row:rows){
            String[] cells=row.split(",");
            j=0;
            for(String cell:cells){
                switch(cell){
                    case "[#]":
                        board[i+1][j+1]=Engine.Field.WALL;
                        break;
                    case "[1]":
                        board[i+1][j+1]=Engine.Field.WALL1;
                        break;
                    case "[2]":
                        board[i+1][j+1]=Engine.Field.WALL2;
                        break;
                    case "[3]":
                        board[i+1][j+1]=Engine.Field.WALL3;
                        break;
                    case "[4]":
                        board[i+1][j+1]=Engine.Field.WALL4;
                        break;
                    case "[0]":
                        board[i+1][j+1]=Engine.Field.WALL0;
                        break;
                    case "[ ]":
                        board[i+1][j+1]=Engine.Field.EMPTY;
                        break;
                    default:
                        throw new RuntimeException("Błędna zawartość pliku");
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
     * @return
     */
    public Engine.Field[][] load(Integer saveNumber){
        String resultString = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("./saves/save"+saveNumber.toString()+".csv"));
            String line ;
            while ((line = br.readLine()) != null){
                resultString+= line;
                resultString += "N";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultString = resultString.substring(0,resultString.length()-1) ;

        Engine.Field[][] board= CSVtoBoard(resultString);
        return board;
    }

}
