package akari.model;


/**
 * Contains coordinates of suspected cell
 */
public record SuspectedCell(int x, int y) {
    //gettery
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
