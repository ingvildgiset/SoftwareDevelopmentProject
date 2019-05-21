package Shapes;

import SquareImage.SquareImage;
import java.awt.*;

/**
 * Class for Line object. Inherits from class Shapes.
 */
public class Line extends Shapes {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Creates a line object between two points with specified color.
     * Points are in vec format (Always between 0 and 1)
     * @param x1 Point 1
     * @param y1 Point 1
     * @param x2 Point 2
     * @param y2 Point 2
     * @param color Color of the line
     */

    public Line(double x1, double y1, double x2, double y2, Color color){
        super(color, false, null);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Resize the line. Point 1 will always stick, so only point 2 will change.
     * @param x2 new x value
     * @param y2 ney y value
     */
    @Override
    public void resize(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Creates a string command to create a Line in vec format.
     * @return The syntax for line is "LINE [x1] [y1] [x2] [y2]"
     */
    @Override
    public String toVecFormat() {
        String vec = "PEN #" + Integer.toHexString(getPenColor().getRGB()).substring(2) + "\n";
        vec = vec + "LINE " + x1 + " " + y1 + " " + x2 + " " + y2 + "\n";
        return vec;
    }

    @Override
    public String toString() {
        return "Line";
    }


    /**
     * Draws the Line on the graphicObject. Uses the image and its size to calculate the correct
     * coordinates.
     * @param graphics Graphic object for drawing
     * @param image The image to be drawn at
     */
    @Override
    public void draw(Graphics graphics, SquareImage image) {
        graphics.setColor(getPenColor());
        graphics.drawLine((int)((x1)*image.getSize()), (int)((y1)*image.getSize()), (int)((x2)*image.getSize()), (int)((y2)*image.getSize()));
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
