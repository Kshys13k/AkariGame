package akari.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadBoard {

    public LoadBoard(){
    }

    //metoda na bazie stringa pobranego z pliku tworzy planszę gotową do gry
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

    public Engine.Field[][] load(Integer saveNumber){
        List <String> load= new ArrayList<>();
        Path path = Paths.get("./saves/save"+saveNumber.toString()+".csv");
        try {
            load=Files.readAllLines(path);
        } catch (IOException ex) {
            System.out.println("Nie mogę odczytać pliku.");
        }
        String boardCSV= load.get(0);
        Engine.Field[][] board= CSVtoBoard(boardCSV);
        return board;
    }

}
