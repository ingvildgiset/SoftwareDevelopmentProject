package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import SquareImage.*;
import Shapes.Rectangle;
import Shapes.*;

import static IO.VecFileManaging.createVecFileFromImage;


public class DrawManager extends JPanel {
    private java.awt.Image canvas;          //the actual graphic image that we are drawing on
    private SquareImage image;

    //Drawing settings
    private ShapeTool shapeTool;
    private Color penColor;
    private Color fillColor;
    private boolean fill;

    //Mouse coordinates
    private Point clickPoint;
    private Point releasePoint;

    private Graphics2D graphics;

    //Shapes
    private List<Shapes> myShapes;
    private Shapes currentShape;


    private int imageSize;

    private JPanel parentPanel;


    public DrawManager(JPanel parentPanel) {
        this.parentPanel = parentPanel;

        this.shapeTool = ShapeTool.LINE;
        myShapes = new ArrayList<Shapes>();
        this.imageSize = 500;
        this.image = new SquareImage(500);
        System.out.println(imageSize);

        this.penColor = Color.BLACK;
        this.fill = false;
        this.fillColor = Color.BLACK;

        //set JPanel Colour
        setBackground(Color.WHITE);








        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickPoint = e.getPoint();
                System.out.print("Du har klikket");
                System.out.println(clickPoint);

                switch (shapeTool){
                    case PLOT:
                        currentShape = new Plot(toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), penColor);
                        break;
                    case LINE:
                        currentShape = new Line(toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), penColor);
                        break;
                    case RECTANGLE:
                        currentShape = new Rectangle(toVecCoord(clickPoint.x), toVecCoord(clickPoint.y),toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), penColor, fill, fillColor);
                        break;
                    case ELLIPSE:
                        currentShape = new Ellipse(toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), toVecCoord(clickPoint.x), toVecCoord(clickPoint.y), penColor, fill, fillColor);
                        break;
                    case POLYGON:
                        System.out.println("tegner polygon");
                        break;
                    default:
                        System.out.println("Feil valg av shape");
                }
            }

            public void mouseReleased(MouseEvent e){
                //myShapes.add(currentShape);
                image.addShape(currentShape);
                clickPoint = null;
                currentShape = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                releasePoint = e.getPoint();
                currentShape.update(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                repaint();
            }
        });

    }


    @Override
    public Dimension getPreferredSize() {
        if (parentPanel.getHeight() > parentPanel.getWidth() && parentPanel.getHeight() != 0){
            imageSize = parentPanel.getWidth();
            image.setSize(imageSize);
        } else if (parentPanel.getWidth() > parentPanel.getHeight() && parentPanel.getWidth() != 0) {
            imageSize = parentPanel.getHeight();
            image.setSize(imageSize);
        }
        return new Dimension(imageSize, imageSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas == null) {
            canvas = createImage(100, 100);
            System.out.println("Lager et nytt bilde");
            graphics = (Graphics2D) canvas.getGraphics();
        }

        for (Shapes fig: image.getShapes()){
            //draw all shapes that are added
            fig.draw(g, image);
        }

        if (clickPoint != null) {
            currentShape.draw(g, image);
        }
    }

    public void setShapeTool(ShapeTool shapeTool){
        this.shapeTool = shapeTool;
    }

    public void setPenColor(Color color){
        this.penColor = color;
    }

    public void setFillColor(Color color){
        this.fillColor = color;
        this.fill = true;
    }

    public void fillOff(){
        this.fill = false;
    }

    public void clearCanvas(){
        System.out.println("Clear Canvas");

    }

    public void load(String filepath){
        try {
            SquareImage newImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
            this.image = newImage;
            newImage.drawAll(graphics);
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String filepath){
        try{
            createVecFileFromImage(filepath, image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo(){
        System.out.println("undo");
    }

    public void redo(){
        System.out.println("redo");
    }

    public double toVecCoord(int pixel){
        return (double)pixel/imageSize;
    }

}
