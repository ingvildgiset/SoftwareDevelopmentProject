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
        this.shapeTool = ShapeTool.PLOT;
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
                myShapes.add(currentShape);
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
        }

        for (Shapes fig: myShapes){
            //draw all shapes that are added
            fig.draw(g);
        }
    }


    public void setShapeTool(ShapeTool shapeTool){
        this.shapeTool = shapeTool;
    }

    public void setPenColor(Color color){
        this.penColor = penColor;
    }

    public void setFillColor(Color color){
        this.fillColor = color;
    }

    public void clearCanvas(){

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


}
