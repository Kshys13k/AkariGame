package akari.GUI;

import akari.model.Akari;
import akari.model.Generator;

public class GameFrameTest {
    public static void main(String[] args) throws WrongCellTypeException {

        GameFrame frame = new GameFrame(10);
        frame.pack();
        frame.setVisible(true);

    }
}
