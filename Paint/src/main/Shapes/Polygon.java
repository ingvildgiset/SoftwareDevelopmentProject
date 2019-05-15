package Shapes;

import SquareImage.SquareImage;

import java.awt.*;
import java.util.ArrayList;

public class Polygon extends Shapes {
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private int numberOfPoints;
    private boolean closed;


    public Polygon(int numberOfPoints, double[] xValues, double[] yValues, Color boarderColor, boolean fill, Color fillColor){
        super(boarderColor, fill, fillColor);

        this.numberOfPoints = numberOfPoints;
        this.xValues = new ArrayList<Double>();
        this.yValues = new ArrayList<Double>();
        this.closed = true;

        for (double x : xValues){
            this.xValues.add(x);
        }

        for (double y : yValues){
            this.yValues.add(y);
        }


    }

    @Override
    public String toVecFormat() {
        String vec = colorToVecFormat();
        vec = vec + "POLYGON ";
        for (int i=0; i < numberOfPoints; i++){
            vec = vec + Double.toString(xValues.get(i)) + " " + Double.toString(yValues.get(i)) + " ";
        }
        vec = vec + "\n";
        return vec;
    }

    @Override
    public void draw(Graphics g, SquareImage image) {
        if(closed){

            int[] xVal = new int[xValues.size()];
            int[] yVal = new int[yValues.size()];
            for(int i = 0; i < xValues.size(); i++){
                xVal[i] = (int) (xValues.get(i)*image.getSize());
                yVal[i] = (int) (yValues.get(i)*image.getSize());
            }
            g.setColor(getBoarderColor());
            g.drawPolygon(xVal, yVal, numberOfPoints);

            if (isFilled()){
                g.setColor(getFillColor());
                g.fillPolygon(xVal, yVal, numberOfPoints);
            }
        }else{
            g.setColor(getBoarderColor());
            if(numberOfPoints > 1) {
                //know that there are at least two points
                for (int i = 0; i < xValues.size() - 1; i++) {
                    double x1 = xValues.get(i)*image.getSize();
                    double y1 = yValues.get(i)*image.getSize();
                    double x2 = xValues.get(i+1)*image.getSize();
                    double y2 = yValues.get(i+1)*image.getSize();


                    g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

                }

            }
            
        }

    }

    @Override
    public void update(double x2, double y2) {
        xValues.set(xValues.size() -1, x2);
        yValues.set(yValues.size() -1, y2);
        

    }

    public boolean addPoint(double x2, double y2) {
        //add a point
        //kunne denne knaskje ha blitt lagt til i update.
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
