package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;

import Image.Image;
import Shapes.*;
import Shapes.Rectangle;


public class DrawManager extends JPanel {

    //usikker på om vi trenger et image enda??
    private java.awt.Image canvas;
    private Graphics2D graphics;

    //denne må oppdateres av toolBar
    private static ShapeEnum shapeSelection;
    private Color currentColor;

    // Mouse coordinates
    private Point clickPoint;
    private Point currentPoint;

    private Image image;



    public DrawManager() {
        currentColor = Color.BLUE;
        shapeSelection = ShapeEnum.PLOT;        //dette er default verdien

        image = new Image(100, 100);
        System.out.println("vi har lagt et drawArea");


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                clickPoint = e.getPoint();
                System.out.println("pressed");

                //create an object
                Plot newplot = new Plot(clickPoint.x, clickPoint.y, currentColor);
                //add object to image
                image.addShape(newplot);
                //drawFreeHand on the screen
                //kan man farge bare ett punkt?????

                //graphics.drawLine(clickPoint.x, clickPoint.y, clickPoint.x+1, clickPoint.y);
                repaint();
            }
        });


        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                // save coord x,y when mouse is pressed
                currentPoint= e.getPoint();
                System.out.println("sluppet");
                drawFreeHand();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = e.getPoint();
                int x = Math.min(clickPoint.x, dragPoint.x);
                int y = Math.min(clickPoint.y, dragPoint.y);
                repaint();
            }
        });

    }

    public static void updateShape(ShapeEnum newShape) {
        shapeSelection = newShape;
    }

    protected void paintComponent(Graphics g) {
        if (canvas == null) {
            // create image
            canvas = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) canvas.getGraphics();
            graphics.setColor(currentColor);
            // enable antialiasing
            // clear drawFreeHand area
            //lag et blankt bilde
        }

        g.drawImage(canvas, 0, 0, null);
    }

    public void drawFreeHand(){
        //klarer man å bare skrive obj.draw her eller må man ta hensyn?
        switch(shapeSelection){
            case PLOT:
                System.out.println("tegner plot");
                Ellipse2D plot = new Ellipse2D.Double(clickPoint.x, clickPoint.y, 3, 3);
                System.out.println(plot);
                graphics.fill(plot);
                break;
            case LINE:
                System.out.println("tegner linje");
                graphics.drawLine(clickPoint.x, clickPoint.y, currentPoint.x, currentPoint.y);
                repaint();
                break;
            case RECTANGLE:
                //her må det oppdateres med fill og evt fill farge
                Rectangle rectangle = new Rectangle(clickPoint.x, clickPoint.y, currentPoint.x, currentPoint.y, currentColor, false, currentColor);
                rectangle.draw(graphics);
                repaint();
                break;
            case ELLIPSE:
                System.out.println("tegner ellipse");
                int w = Math.abs(currentPoint.x - clickPoint.x);
                int h = Math.abs(currentPoint.y - clickPoint.y);
                graphics.draw(new Ellipse2D.Double(Math.min(clickPoint.x, currentPoint.x), Math.min(clickPoint.y, currentPoint.y), w, h));
                break;

            case POLYGON:
                //denne venter vi litt med.
                System.out.println("tegner polygon");
                break;
            default:
                System.out.println("Feil valg av shape");
        }

    }

    public void drawFromVecFile(String filepath){
        try {
            Image newImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
            newImage.drawAll(graphics);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
