package Shapes;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * JUnit tests for functionality in Shapes
 */
public class shapeTests {
    /**
     * Test output of creating a vec command for Line
     */
    @Test
    public void testLineVecFormat() {
        Line line = new Line(0, 0, 0.5, 0.5, Color.black);
        String lineStr = line.toVecFormat();
        assertEquals(lineStr, "PEN #000000\n" + "LINE 0.0 0.0 0.5 0.5\n");
    }
    /**
     * Test output of creating a vec command for Ellipse
     */
    @Test
    public void testEllipseVecFormat() {
        Ellipse ellipse = new Ellipse(0.0, 0.5, 1.0, 1.0, Color.black, false, Color.GREEN);
        String ellipseStr = ellipse.toVecFormat();
        assertEquals(ellipseStr, "FILL OFF\n" + "ELLIPSE 0.0 0.5 1.0 1.0\n");
    }
    /**
     * Test output of creating a vec command for Rectangle
     */
    @Test
    public void testRectangleVecFormat() {
        Rectangle rectangle = new Rectangle(0, 0, 0.75, 0.75, Color.CYAN, true, Color.GREEN);
        String rectStr = rectangle.toVecFormat();
        assertEquals(rectStr, "PEN #00ffff\n" + "FILL #00ff00\n" + "RECTANGLE 0.0 0.0 0.75 0.75\n");
    }
    /**
     * Test output of creating a vec command for Plot
     */
    @Test
    public void testPlotVecFormat() {
        Plot plot = new Plot(0.8, 0.6, Color.CYAN);
        String plotStr = plot.toVecFormat();
        assertEquals(plotStr, "PEN #00ffff\n" + "PLOT 0.8 0.6\n");
    }
    /**
     * Test output of creating a vec command for polygon
     */
    @Test
    public void testPolyVecFormat() {
        int numberOfPoints = 3;
        double[] xVal = {0,0.5,1};
        double[] yVal = {0,0.5,1};
        Polygon polygon = new Polygon(numberOfPoints, xVal, yVal, Color.MAGENTA, true, Color.GREEN);
        String polyStr = polygon.toVecFormat();
        assertEquals(polyStr, "PEN #ff00ff\n" + "FILL #00ff00\n" + "POLYGON 0.0 0.0 0.5 0.5 1.0 1.0 \n");
    }
    /**
     * Test output of creating a vec command the chosen color of fill and pen.
     * Test constructed to test both shape with and without fill.
     */
    @Test
    public void testColorToVecFormat() {
        Ellipse ellipse = new Ellipse(0.0, 0.5, 1.0, 1.0, Color.black, false, Color.GREEN);
        String color1 = ellipse.colorToVecFormat();
        assertEquals(color1, "FILL OFF\n");

        Rectangle rectangle = new Rectangle(0,0,0.75,0.75, Color.CYAN, true, Color.GREEN);
        String color2 = rectangle.colorToVecFormat();
        assertEquals(color2, "PEN #00ffff\n" + "FILL #00ff00\n");
    }

}


