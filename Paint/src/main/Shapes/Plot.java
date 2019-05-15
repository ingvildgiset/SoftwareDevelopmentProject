package Shapes;

import SquareImage.SquareImage;

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
        this.x = x2;
        this.y = y2;

    }

    @Override
    public String toVecFormat() {
        String vec = "PEN #" + Integer.toHexString(getBoarderColor().getRGB()).substring(2) + "\n";
        vec = vec + "PLOT " + Double.toString(x) + " " + Double.toString(y) + "\n";
        return vec;
    }

    @Override
    public void draw(Graphics g, SquareImage image) {
        g.setColor(getBoarderColor());
        g.fillOval((int) Math.round(x)*image.getSize(), (int) Math.round(y)*image.getSize(), 3, 3);
    }
}
