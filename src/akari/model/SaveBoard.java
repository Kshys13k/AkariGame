package akari.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Saving board to .csv file
 */
public class SaveBoard {
    Engine.Field[][] board;

    public SaveBoard(){}
    public SaveBoard(Engine.Field[][] board){
            this.board=board;
    }

    /**
     * setting board to save
     */
    public void setBoard(Engine.Field[][] board) {
        this.board = board;
    }

    /**
     * converting board to string
     */
    private String boardToCSV() throws IOException {
        StringBuilder boardCSV= new StringBuilder();
        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board.length-1; j++) {
                switch(board[i][j]){
                    case WALL-> boardCSV.append("[#],");
                    case WALL1-> boardCSV.append("[1],");
                    case WALL2-> boardCSV.append("[2],");
                    case WALL3-> boardCSV.append("[3],");
                    case WALL4-> boardCSV.append("[4],");
                    case WALL0-> boardCSV.append("[0],");
                    case LIGHTED-> boardCSV.append("[.],");
                    case LIGHTED2-> boardCSV.append("[:],");
                    case EMPTY-> boardCSV.append("[ ],");
                    case BULB-> boardCSV.append("[*],");
                    default-> throw new IOException("Internal Error");
                }
            }
            boardCSV = new StringBuilder(boardCSV.substring(0, boardCSV.length() - 1));
            boardCSV.append("\n");
        }
        boardCSV = new StringBuilder(boardCSV.substring(0, boardCSV.length() - 1));
        return boardCSV.toString();
    }

    /**
     * saving board to .csv file
     * @param saveNumber number of save
     */
    public void save(Integer saveNumber) throws IOException {
        String boardCSV=boardToCSV();
        Path path = Paths.get("./saves/save"+saveNumber.toString()+".csv");
        try {
            Files.writeString(path, boardCSV);
        } catch (IOException ex) {
            System.out.println("Nie mogę zapisać pliku.");
        }

    }

}
