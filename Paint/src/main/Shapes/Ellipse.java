package Shapes;

import SquareImage.SquareImage;
import java.awt.*;

/**
 * Class for Ellipse object. Inherits from class Shapes.
 */
public class Ellipse extends Shapes{
    private double x1, y1, x2, y2;
    private double width, height;

    /**
     * Creates an Ellipse that fits within the square created of (x1, y1) and (x2,y2).
     * The points are in vec format (between 0 and 1).
     * @param x1 Upper left corner of rectangle
     * @param y1 Upper left corner of rectangle
     * @param x2 Lower right corner of rectangle
     * @param y2 Lower right corner of rectangle
     * @param penColor Color of the boarder
     * @param fill Boolean value for fill
     * @param fillColor Fill color if fill=true
     */
    public Ellipse(double x1, double y1, double x2, double y2, Color penColor, boolean fill, Color fillColor){
        super(penColor, fill, fillColor);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.height = Math.abs(y1 - y2);
        this.width = Math.abs(x1 - x2);
    }

    /**
     * Resizes the rectangle that describes the ellipse.
     * @param x2 New point
     * @param y2 New point
     */
    @Override
    public void resize(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;
        this.height = Math.abs(y2 - y1);
        this.width = Math.abs(x2 - x1);
    }

    /**
     * Creates a string command to create a Ellipse in vec format.
     * The syntax for ellipse is "ELLIPSE [x1] [y1] [x2] [y2]"
     * @return the string command
     */
    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        vec = vec + "ELLIPSE " + x1 + " " + y1 + " " + x2 + " " + y2 + "\n";
        return vec;
    }


    /**
     * Draws the ellipse on the graphicObject. Uses the image and its size to calculate the correct
     * coordinates.
     * @param graphics Graphic object for drawing
     * @param image The image to be drawn at
     */
    @Override
    public void draw(Graphics graphics, SquareImage image){
        double x = Math.min((x1), (x2));
        double y = Math.min((y1), (y2));
        x = x * image.getSize();
        y = y * image.getSize();
        graphics.setColor(getPenColor());
        graphics.drawOval((int)x, (int)y, (int)(width*image.getSize()), (int)(height*image.getSize()));
        if(isFilled()){
            graphics.setColor(getFillColor());
            graphics.fillOval((int)x, (int)y, (int)(width*image.getSize()), (int)(height*image.getSize()));
        }
    }


    /**
     * Only for the benefit of polymorphism
     * @param x2 x value in new point
     * @param y2 y value in new point
     * @return false Not in use
     */
    @Override
    public boolean addPoint(double x2, double y2) {
        return false;
    }


}

