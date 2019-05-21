package Shapes;

import SquareImage.SquareImage;
import java.awt.*;

/**
 * Class for Ellipse object. Inherits from class Shapes.
 */
public class Plot extends Shapes {
    private double x;
    private double y;

    /**
     * Creating a plot, which is just a point in specified color. Coordinates
     * is given in vector format (values between 0 and 1)
     * @param x x coordinate
     * @param y y coordinatte
     * @param color color of the plot
     */
    public Plot(double x, double y, Color color){
        super(color, false, null);
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the point to another position
     * @param x2 new x value
     * @param y2 new y value
     */
    @Override
    public void resize(double x2, double y2) {
        this.x = x2;
        this.y = y2;

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

    /**
     * Creates a string command to create a plot in vec format.
     * The syntax for ellipse is "PLOT [x1] [y1]"
     * @return string command
     */
    @Override
    public String toVecFormat() {
        String vec = "PEN #" + Integer.toHexString(getPenColor().getRGB()).substring(2) + "\n";
        vec = vec + "PLOT " + x + " " + y + "\n";
        return vec;
    }

    @Override
    public String toString() {
        return "Plot";
    }


    /**
     * Draws the plot on the canvas.
     * @param g Graphic object used for drawing
     * @param image The image to be drawn at
     */
     @Override
    public void draw(Graphics g, SquareImage image) {
        g.setColor(getPenColor());
        g.fillOval((int) ((x)*image.getSize()), (int) ((y)*image.getSize()), 3, 3);
    }
}
