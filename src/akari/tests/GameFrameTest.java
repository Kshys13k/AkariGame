package akari.tests;


import akari.GUI.GameFrame;
import akari.GUI.WrongCellTypeException;

public class GameFrameTest {
    public static void main(String[] args) throws WrongCellTypeException {

        GameFrame frame = new GameFrame(9, (float) 0.35,(float) 0.5, (float) 0.6);
        frame.pack();
        frame.setVisible(true);

    }
}
