package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;

import Image.Image;
import Shapes.Rectangle;
import Shapes.*;


public class DrawManager extends JPanel {
    private java.awt.Image canvas;          //the actual graphic image that we are drawing on
    private Graphics2D graphics;

    private ShapeTool shapeTool;



    // Mouse coordinates
    private Point clickPoint;
    private Point relasePoint;



    public DrawManager() {
        this.shapeTool = ShapeTool.PLOT;


        //dette er konstruktøren, så vet ikke om det blir helt riktig.
        Image image = new Image(100, 100);


        addMouseListener(new MouseAdapter() {
            //disse kan man kanskje flytte helt ut?
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                clickPoint = e.getPoint();
            }
        });


        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                // save coord x,y when mouse is pressed
                relasePoint = e.getPoint();
                //gir mer mening at image er en privat medlemsvariabel?
                drawFreeHand(image);
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


    protected void paintComponent(Graphics g) {
        if (canvas == null) {
            // create image
            canvas = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) canvas.getGraphics();
            // enable antialiasing
            // clear drawFreeHand area
            //lag et blankt bilde
        }

        g.drawImage(canvas, 0, 0, null);
    }

    public void drawFreeHand(Image image){
        //klarer man å bare skrive obj.draw her eller må man ta hensyn?
        switch(shapeTool){
            case PLOT:
                //add figure to image
                Plot newPlot = new Plot(clickPoint.x, clickPoint.y, Color.BLACK);
                image.addShape(newPlot);

                //draw on Canvas
                Ellipse2D plot = new Ellipse2D.Double(clickPoint.x, clickPoint.y, 3, 3);
                graphics.fill(plot);
                break;
            case LINE:
                //add figure to image
                Line newLLine = new Line(clickPoint.x, clickPoint.y, relasePoint.x, relasePoint.y, Color.black);
                image.addShape(newLLine);

                //draw on Canvas
                graphics.drawLine(clickPoint.x, clickPoint.y, relasePoint.x, relasePoint.y);
                repaint();
                break;
            case RECTANGLE:
                //add figure to image
                Rectangle newRec = new Rectangle(clickPoint.x, clickPoint.y, relasePoint.x, relasePoint.y, Color.black, false, Color.BLACK);
                image.addShape(newRec);

                //draw on Canvas
                Rectangle rectangle = new Rectangle(clickPoint.x, clickPoint.y, relasePoint.x, relasePoint.y, Color.black, false, Color.BLACK);
                rectangle.draw(graphics);
                repaint();
                break;
            case ELLIPSE:
                //add figure to image
                Ellipse newEllipse = new Ellipse(clickPoint.x, clickPoint.y, relasePoint.x, relasePoint.y, Color.black, false, Color.BLACK);
                image.addShape(newEllipse);

                //draw on Canvas
                int w = Math.abs(relasePoint.x - clickPoint.x);
                int h = Math.abs(relasePoint.y - clickPoint.y);
                graphics.draw(new Ellipse2D.Double(Math.min(clickPoint.x, relasePoint.x), Math.min(clickPoint.y, relasePoint.y), w, h));
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
