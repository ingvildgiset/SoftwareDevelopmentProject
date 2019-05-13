package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Image.Image;
import Shapes.Rectangle;
import Shapes.*;


public class DrawManager extends JPanel {
    private java.awt.Image canvas;          //the actual graphic image that we are drawing on

    //settings
    private ShapeTool shapeTool;
    private Color penColor;
    private Color fillColor;
    private boolean fill;

    // Mouse coordinates
    private Point clickPoint;
    private Point releasePoint;

    private List<Shapes> myShapes;
    private Shapes currentShape;





    public DrawManager() {
        this.shapeTool = ShapeTool.RECTANGLE;
        myShapes = new ArrayList<Shapes>();
        this.penColor = Color.black;
        this.fill = true;
        this.fillColor = Color.BLACK;



        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickPoint = e.getPoint();
                switch (shapeTool){
                    case PLOT:
                        currentShape = new Plot(clickPoint.x, clickPoint.y, penColor);
                        break;
                    case LINE:
                        currentShape = new Line(clickPoint.x, clickPoint.y, clickPoint.x, clickPoint.y, penColor);
                        break;
                    case RECTANGLE:
                        currentShape = new Rectangle(clickPoint.x, clickPoint.y, clickPoint.x, clickPoint.y, penColor, fill, fillColor);
                        break;
                    case ELLIPSE:
                        currentShape = new Ellipse(clickPoint.x, clickPoint.y, clickPoint.x, clickPoint.y, penColor, fill, fillColor);
                        break;
                    case POLYGON:
                        System.out.println("tegner polygon");
                        break;
                    default:
                        System.out.println("Feil valg av shape");
                }
            }

            public void mouseReleased(MouseEvent e){
                Rectangle test = new Rectangle(clickPoint.x, clickPoint.y, releasePoint.x, releasePoint.y, Color.black, false, Color.BLACK);
                myShapes.add(test);
                clickPoint = null;
                currentShape = null;
                repaint();
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                releasePoint = e.getPoint();
                currentShape.update(releasePoint.x, releasePoint.y);
                repaint();
            }
        });



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (canvas == null) {
            canvas = createImage(getSize().width, getSize().height);
        }


        if (clickPoint != null) {
            //set the colour
            g.setColor(Color.RED);

            //draw the temp figure
            currentShape.draw(g);
            //g.drawRect(clickPoint.x, clickPoint.y, releasePoint.x - clickPoint.x, releasePoint.y - clickPoint.y);
        }

        for (Shapes fig: myShapes){
            //draw all shapes that are added
            fig.draw(g);
        }
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

                break;
            case LINE:
                //add figure to image
                Line newLLine = new Line(clickPoint.x, clickPoint.y, releasePoint.x, releasePoint.y, Color.black);
                image.addShape(newLLine);


                break;
            case RECTANGLE:
                //add figure to image
                Rectangle newRec = new Rectangle(clickPoint.x, clickPoint.y, releasePoint.x, releasePoint.y, Color.black, false, Color.BLACK);
                image.addShape(newRec);


                break;
            case ELLIPSE:
                //add figure to image
                Ellipse newEllipse = new Ellipse(clickPoint.x, clickPoint.y, releasePoint.x, releasePoint.y, Color.black, false, Color.BLACK);
                image.addShape(newEllipse);
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
            //newImage.drawAll(graphics);
            //må vi ha denne som en privat variabel alikavel
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setShapeTool(ShapeTool shapeTool){
        shapeTool = shapeTool;
    }

    public void setColor(){
        //her kan vi oppdatere farge
    }

}
