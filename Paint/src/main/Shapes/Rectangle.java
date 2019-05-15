package Shapes;

import SquareImage.SquareImage;

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
        this.height = Math.abs(y1 - y2);
        this.width = Math.abs(x1 - x2);
    }

    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        vec = vec + "RECTANGLE " + Double.toString(x1) + " " + Double.toString(y1) + " " + Double.toString(x2) + " " + Double.toString(y2) + "\n";
        return vec;
    }

    @Override
    public void draw(Graphics graphics, SquareImage image) {
        graphics.setColor(getBoarderColor());
        int x = (int) ((Math.min(x1, x2))*image.getSize());
        int y = (int) ((Math.min(y1, y2))*image.getSize());
        graphics.drawRect(x, y,  (int)(width*image.getSize()), (int)(height*image.getSize()));
        if (isFilled()){
            graphics.setColor(getFillColor());
            graphics.fillRect(x, y,  (int)(width*image.getSize()), (int)(height*image.getSize()));
        }
    }

    @Override
    public void update(double x2, double y2){
        this.x2 = x2;
        this.y2 = y2;

        this.height = Math.abs(y1 - y2);
        this.width = Math.abs(x1 - x2);


    }

    @Override
    public boolean addPoint(double x2, double y2) {
        return false;
    }


}
