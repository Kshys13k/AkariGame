package akari.model;

public class SuspectedCell {
    private int x;
    private int y;
    private boolean bulbOn;

    //konstruktor
    public SuspectedCell(int x, int y){
        this.x=x;
        this.y=y;
        bulbOn=false;
    }

    //setter
    public void turnOnOff(){
        bulbOn=!bulbOn;
    }

    //gettery
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isBulbOn(){
        return bulbOn;
    }
}
