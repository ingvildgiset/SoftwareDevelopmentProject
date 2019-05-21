package Shapes;

import SquareImage.SquareImage;
import java.awt.*;

/**
 * Abstract class Shapes which is a generic class for the different shape objects.
 */

public abstract class Shapes {
    private Color boarderColor, fillColor;
    private boolean fill;

    /**
     * This is the super class constructor which initialize the private varibles
     * that is common for all the different shapes
     * @param penColor the color of the boarder of the figure. This is by default black.
     * @param fill a boolean variable saying if the shape should be filled or not. This is default=false.
     * @param fillColor the color filling the shape if fill=true.
     */
    Shapes(Color penColor, boolean fill, Color fillColor){
        this.boarderColor = penColor;
        this.fill = fill;
        this.fillColor = fillColor;
    }

    /**
     * Returns the pen color used for the shape object
     * @return color
     */
    Color getPenColor(){ return boarderColor; }

    /**
     * Returns the color used to fill the shape object
     * @return color
     */
    Color getFillColor(){
        return fillColor;
    }

    /**
     * Returns true if the shape object is filled.
     * @return boolean
     */
    boolean isFilled(){
        return fill;
    }

    /**
     * Returns the vector format for color of the pen and fill (if any). The colors are
     * written in hexa format giving the syntax PEN #FF000 and FILL #FFFF00
     * @return string
     */
    String colorToVecFormat() {
        String vec = "";
        if (getPenColor() != Color.BLACK) {
            vec += "PEN #" + Integer.toHexString(getPenColor().getRGB()).substring(2) + "\n";
        }
        if (isFilled()) {
            vec += "FILL #" + Integer.toHexString(getFillColor().getRGB()).substring(2) + "\n";
        } else {
            vec += "FILL OFF\n";
        }
        return vec;
    }

    /**
     * Returns the vec file command for drawing the shape object. This contains
     * all vertices/points, type/command for shape and color for fill and pen if specified.
     * @return String
     */
    public abstract String toVecFormat();

    /**
     * Method for drawing the shape object on the canvas. Customized for different shape
     * object
     * @param g A graphic object used for drawing
     * @param image The image to be drawn at
     */
    public abstract void draw(Graphics g, SquareImage image);

    /**
     * Resizes the shape object. Used when user is drawing (click and drag) on the canvas.
     * @param x2 new x value
     * @param y2 ney y value
     */
    public abstract void resize(double x2, double y2);

    /**
     * Used only for polygon... Used to change the number of vertices. The benefit of implementing it
     * as an abstract method is to use polymorphism when drawing from user at the canvas.
     * @param x2 x value in new point
     * @param y2 y value in new point
     * @return if the point is the same as the starting point the polygon is closed and return true
     */
    public abstract boolean addPoint(double x2, double y2);

}
