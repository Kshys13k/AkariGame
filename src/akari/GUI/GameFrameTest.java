package akari.GUI;

public class GameFrameTest {
    public static void main(String[] args) throws WrongCellTypeException {
        GameFrame frame = new GameFrame(20,20);
        frame.pack();
        frame.setVisible(true);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                frame.setCell(i,j,'*');
            }
        }

    }
}
