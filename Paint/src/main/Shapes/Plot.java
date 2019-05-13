package Shapes;

import java.awt.*;

public class Plot extends Shapes {
    private double x;
    private double y;


    public Plot(double x, double y, Color color){
        super(color, false, null);
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(double x2, double y2) {

    }

    @Override
    public String toVecFormat() {
        String vec = "PEN #" + Integer.toHexString(getBoarderColor().getRGB()).substring(2) + "\n";
        vec = vec + "PLOT " + Double.toString(x) + " " + Double.toString(y) + "\n";
        return vec;
    }

    @Override
    public void draw(Graphics g) {
        //her må vi gjøre noe greier
    }
}
