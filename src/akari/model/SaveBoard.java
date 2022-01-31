package akari.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Saving board to .csv file
 */
public class SaveBoard {
    Engine.Field[][] board;

    public SaveBoard(){}
    public SaveBoard(Engine.Field board [][]){
            this.board=board;
    }

    /**
     * setting board to save
     * @param board
     */
    public void setBoard(Engine.Field[][] board) {
        this.board = board;
    }

    /**
     * converting board to string
     * @return
     */
    private String boardToCSV(){
        String boardCSV="";
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board.length-1; j++) {
                switch(board[i][j]){
                    case WALL:
                        boardCSV+="[#],";
                        break;
                    case WALL1:
                        boardCSV+="[1],";
                        break;
                    case WALL2:
                        boardCSV+="[2],";
                        break;
                    case WALL3:
                        boardCSV+="[3],";
                        break;
                    case WALL4:
                        boardCSV+="[4],";
                        break;
                    case WALL0:
                        boardCSV+="[0],";
                        break;
                    default:
                        boardCSV+="[ ],";
                }
            }boardCSV=boardCSV.substring(0,boardCSV.length()-1);
            boardCSV+="\n";
        }
        boardCSV=boardCSV.substring(0,boardCSV.length()-1);
        return boardCSV;
    }

    /**
     * saving board to .csv file
     * @param saveNumber number of save
     */
    public void save(Integer saveNumber){
        String boardCSV=boardToCSV();
        Path path = Paths.get("./saves/save"+saveNumber.toString()+".csv");
        try {
            Files.write(path, boardCSV.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println("Nie mogę zapisać pliku.");
        }

    }

}
