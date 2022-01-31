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
    private String boardToCSV() throws IOException {
        String boardCSV="";
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board.length-1; j++) {
                switch(board[i][j]){
                    case WALL-> boardCSV+="[#],";
                    case WALL1-> boardCSV+="[1],";
                    case WALL2-> boardCSV+="[2],";
                    case WALL3-> boardCSV+="[3],";
                    case WALL4-> boardCSV+="[4],";
                    case WALL0-> boardCSV+="[0],";
                    case LIGHTED-> boardCSV+="[.],";
                    case LIGHTED2-> boardCSV+="[:],";
                    case EMPTY-> boardCSV+="[ ],";
                    case BULB-> boardCSV+="[*],";
                    default-> throw new IOException("Internal Error");
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
    public void save(Integer saveNumber) throws IOException {
        String boardCSV=boardToCSV();
        Path path = Paths.get("./saves/save"+saveNumber.toString()+".csv");
        try {
            Files.write(path, boardCSV.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println("Nie mogę zapisać pliku.");
        }

    }

}
