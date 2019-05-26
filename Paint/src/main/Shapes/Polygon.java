package Shapes;

import SquareImage.SquareImage;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class for polygon shape. Inherits from Shapes class.
 */
public class Polygon extends Shapes {
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private int numberOfPoints;
    private boolean closed;

    /**
     * Creates a polygon object. The coordinates are given in vector format.
     * Meaning they are values between 0 and 1
     * @param numberOfPoints number of vertices/points
     * @param xValues array containing the x coordinates
     * @param yValues array containing the y coordinates
     * @param penColor color of the outline
     * @param fill boolean variable saying if the shape should be filled
     * @param fillColor color for the fill if fill=true
     */
    public Polygon(int numberOfPoints, double[] xValues, double[] yValues, Color penColor, boolean fill, Color fillColor){
        super(penColor, fill, fillColor);
        this.numberOfPoints = numberOfPoints;
        this.xValues = new ArrayList<>();
        this.yValues = new ArrayList<>();
        this.closed = true;
        for (double x : xValues){
            this.xValues.add(x);
        }
        for (double y : yValues){
            this.yValues.add(y);
        }
    }

    /**
     * Return the string command for creating a polygon in a vec file. The syntax is
     * "POLYGON 0.5 0.0 1.0 0.5 0.5 1.0 0.0 0.5".
     * @return A command string
     */
    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        StringBuilder vecBuilder = new StringBuilder(vec + "POLYGON ");
        for (int i = 0; i < numberOfPoints; i++) vecBuilder.append(xValues.get(i)).append(" ").append(yValues.get(i)).append(" ");
        vec = vecBuilder.toString();
        vec = vec + "\n";
        return vec;
    }

    @Override
    public String toString() {return "Polygon #" + Integer.toHexString(getPenColor().getRGB()).substring(2);
    }


    /**
     * Draws the polygon on the graphicObject. Uses the image and its size to calculate the correct
     * coordinates.
     * @param g Graphic object for drawing
     * @param imageSize The size of the image to be drawn at
     */
    @Override
    public void draw(Graphics g, int imageSize) {
        if(closed){

            int[] xVal = new int[xValues.size()];
            int[] yVal = new int[yValues.size()];
            for(int i = 0; i < xValues.size(); i++){
                xVal[i] = (int) (xValues.get(i)*imageSize);
                yVal[i] = (int) (yValues.get(i)*imageSize);
            }
            g.setColor(getPenColor());
            g.drawPolygon(xVal, yVal, numberOfPoints);

            if (isFilled()){
                g.setColor(getFillColor());
                g.fillPolygon(xVal, yVal, numberOfPoints);
            }
        }else{
            g.setColor(getPenColor());
            if(numberOfPoints > 1) {
                //know that there are at least two points
                for (int i = 0; i < xValues.size() - 1; i++) {
                    double x1 = xValues.get(i)*imageSize;
                    double y1 = yValues.get(i)*imageSize;
                    double x2 = xValues.get(i+1)*imageSize;
                    double y2 = yValues.get(i+1)*imageSize;
                    g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
                }
            }
        }
    }

    /**
     * Changes the last point added to the polygon.
     * @param x2 new x value
     * @param y2 new y value
     */
    @Override
    public void resize(double x2, double y2) {
        xValues.set(xValues.size() -1, x2);
        yValues.set(yValues.size() -1, y2);
    }

    /**
     * Adds a new vertice to the polygon. If this point is allready added, meaning the
     * polygon is closed it returnes true. The shape is complete.
     * @param x2 x value in new point
     * @param y2 y value in new point
     * @return true if the polygon is closed
     */
    @Override
    public boolean addPoint(double x2, double y2) {
        //add a point
        //kunne denne knaskje ha blitt lagt til i resize.
        //ville gjort polymorphisen bedre
        this.closed = false;
        //if you are adding a point close to the starting point! DONE
        if ((x2 < xValues.get(0) + 0.02 && x2 > xValues.get(0) - 0.02) && (y2 < yValues.get(0) + 0.02 && y2 > yValues.get(0) - 0.02)){
            closed = true;
            return true;
        } else {
            numberOfPoints +=1;
            this.xValues.add(x2);
            this.yValues.add(y2);
        }
        return false;
    }
}
