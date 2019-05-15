package Shapes;

import SquareImage.SquareImage;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shapes{
    private double x1, y1, x2, y2;
    private double width, height;

    public Ellipse(double x1, double y1, double x2, double y2, Color boarderColor, boolean fill, Color fillColor){
        super(boarderColor, fill, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.height = Math.abs(y1 - y2);
        this.width = Math.abs(x1 - x2);
    }

    @Override
    public void update(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;

        this.height = Math.abs(y1 - y2);
        this.width = Math.abs(x1 - x2);
    }

    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        vec = vec + "ELLIPSE " + Double.toString(x1) + " " + Double.toString(y1) + " " + Double.toString(x2) + " " + Double.toString(y2) + "\n";
        return vec;
    }


    public void draw(Graphics graphics, SquareImage image){
        double x = Math.min((x1), (x2));
        double y = Math.min((y1), (y2));
        x = x * image.getSize();
        y = y * image.getSize();
        graphics.setColor(getBoarderColor());
        graphics.drawOval((int)x, (int)y, (int)(width*image.getSize()), (int)(height*image.getSize()));
        if(isFilled()){
            graphics.setColor(getFillColor());
            graphics.fillOval((int)x, (int)y, (int)(width*image.getSize()), (int)(height*image.getSize()));
        }
    }

}

