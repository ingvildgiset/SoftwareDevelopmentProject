package Shapes;

import SquareImage.SquareImage;

import java.awt.*;

public class Line extends Shapes {
    private double x1;
    private double y1;
    private double x2;
    private double y2;



    public Line(double x1, double y1, double x2, double y2, Color color){
        super(color, false, null);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

    }

    @Override
    public void update(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public boolean addPoint(double x2, double y2) {
        return false;

    }

    @Override
    public String toVecFormat() {
        String vec = "PEN #" + Integer.toHexString(getBoarderColor().getRGB()).substring(2) + "\n";
        vec = vec + "LINE " + Double.toString(x1) + " " + Double.toString(y1) + " " + Double.toString(x2) + " " + Double.toString(y2) + "\n";
        return vec;
    }

    @Override
    public void draw(Graphics graphics, SquareImage image) {
        graphics.setColor(getBoarderColor());
        graphics.drawLine((int)((x1)*image.getSize()), (int)((y1)*image.getSize()), (int)((x2)*image.getSize()), (int)((y2)*image.getSize()));
    }

}
