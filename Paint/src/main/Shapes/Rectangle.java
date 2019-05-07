package Shapes;

import java.awt.*;

public class Rectangle extends Shapes {
    private double x1, x2, y1, y2;
    private double width, height;


    public Rectangle(double x1, double y1, double x2, double y2, Color boarderColor, boolean fill, Color fillColor){
        super(boarderColor, fill, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = Math.abs(x1 - x2);
        this.width = Math.abs(y1 - y2);

    }


    @Override
    public String toVecFormat() {
        return null;
    }

    @Override
    public void draw(Graphics2D graphics ) {
        graphics.drawRect((int) Math.round(Math.min(x1, x2)), (int) Math.round(Math.min(y1, y2)),  (int) Math.round(width), (int) Math.round(height));
    }


}
