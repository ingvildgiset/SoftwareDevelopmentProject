package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.List;
import SquareImage.*;
import Shapes.Rectangle;
import Shapes.Polygon;
import Shapes.*;

import static IO.VecFileManaging.createVecFileFromImage;


public class DrawManager extends JPanel{
    private SquareImage image;

    //Drawing settings
    private ShapeTool shapeTool;
    private Color penColor;
    private Color fillColor;
    private boolean fill;

    //Mouse coordinates
    private Point clickPoint;
    private Point releasePoint;

    //Shapes
    private Shapes currentShape;
    private JPanel parentPanel;

    //new code
    private double zoomMultiplier;
    Dimension size;


    public DrawManager(JPanel parentPanel) {
        size = new Dimension(600,600);

        //set JPanel Colour
        setBackground(Color.WHITE);
        this.parentPanel = parentPanel;


        //default values
        this.shapeTool = ShapeTool.LINE;
        this.penColor = Color.BLACK;
        this.fill = false;
        this.fillColor = Color.BLACK;
        this.image = new SquareImage(600);
        this.zoomMultiplier = 1;




        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickPoint = e.getPoint();
                System.out.println(clickPoint);
                //https://stackoverflow.com/questions/33925884/zoom-in-and-out-of-jpanel
                //hvis man må scale museklikket også?????

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
                        //create a new
                        if (currentShape == null){
                            double xVal[] = {toVecCoord(clickPoint.x), toVecCoord(clickPoint.x)};
                            double yVal[] = {toVecCoord(clickPoint.y), toVecCoord(clickPoint.y)};
                            currentShape = new Polygon(xVal.length, xVal, yVal, penColor, fill, fillColor);
                        }

                        break;
                    default:
                        System.out.println("Feil valg av shape");
                }
            }

            public void mouseReleased(MouseEvent e){
                //myShapes.add(currentShape);
                if (shapeTool == ShapeTool.POLYGON && releasePoint != null){

                    if(currentShape.addPoint(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y))){
                        image.addShape(currentShape);
                        clickPoint = null;
                        currentShape = null;
                    };
                } else {
                    //her er releasepoint strengt tatt ikke oppdatert, skal vi da legge til det li
                    image.addShape(currentShape);
                    clickPoint = null;
                    currentShape = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                releasePoint = e.getPoint();

                currentShape.update(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                repaint();

            }

            public void mouseMoved(MouseEvent e) {
                if (shapeTool == ShapeTool.POLYGON && clickPoint!=null){
                    releasePoint = e.getPoint();
                    currentShape.update(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                    //tester
                    repaint();
                }

            }
        });

    }



   /* public Dimension getPreferredSize() {
        System.out.println("Set preferred size");
        int size = 0;
        if (parentPanel.getHeight() > parentPanel.getWidth() && parentPanel.getHeight() != 0){
            size = parentPanel.getWidth();
        } else if (parentPanel.getWidth() > parentPanel.getHeight() && parentPanel.getWidth() != 0) {
            size = parentPanel.getHeight();
        }
        //check for Max values

        image.setSize(size);
        System.out.println(size);
        return new Dimension(size,size);
    }*/

    @Override
    public Dimension getMinimumSize() {
        System.out.println("minimum");
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        System.out.println("maksimum");
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        int w = (int)(zoomMultiplier*size.width);
        int h = (int)(zoomMultiplier*size.height);
        System.out.println(w);
        return new Dimension(w, h);
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        double x = (getWidth()  - zoomMultiplier*size.width)/2;
        double y = (getHeight() - zoomMultiplier*size.height)/2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(zoomMultiplier, zoomMultiplier);

        for (Shapes fig: image.getShapes()){
            //draw all shapes that are added
            fig.draw(graphics2D, image);
        }

        if (clickPoint != null) {
            currentShape.draw(graphics2D, image);
        }



    }

    public void setShapeTool(ShapeTool shapeTool){
        this.shapeTool = shapeTool;
        repaint();
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
        List<Shapes> currentImages = image.getShapes();
        if(currentImages.size() > 0) {
            currentImages.removeAll(currentImages);
        }
        repaint();
    }

    public void load(String filepath){
        try {
            SquareImage newImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
            this.image = newImage;
            //update image according to window
            image.setSize(parentPanel.getHeight());
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

    public void setZoomMultiplier(double zoomMultiplier){
        this.zoomMultiplier = zoomMultiplier;
        System.out.println("Skal zoome til");
        System.out.println(zoomMultiplier);
        repaint();
        revalidate();
    }




    public void undo(){
       List<Shapes> currentImages = image.getShapes();
        if(currentImages.size() > 0) {
            currentImages.remove(currentImages.size() - 1 );
        }
        repaint();
    }


    public double toVecCoord(int pixel){
        return (double)pixel/image.getSize();
    }


}
