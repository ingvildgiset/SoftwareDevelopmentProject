package Shapes;

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

        this.height = Math.abs(x1 - x2);
        this.width = Math.abs(y1 - y2);
    }


    @Override
    public void update(double x2, double y2) {

    }

    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        vec = vec + "ELLIPSe" + Double.toString(x1) + " " + Double.toString(x2) + " " + Double.toString(y1) + " " + Double.toString(y2) + "\n";
        return vec;
    }


    public void draw(Graphics graphics){
        //graphics.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), width, height));
    }

}

