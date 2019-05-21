package DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import SquareImage.*;
import Shapes.Rectangle;
import Shapes.Polygon;
import Shapes.*;

import static IO.VecFileManaging.createVecFileFromImage;


public class DrawManager extends JPanel {
    private SquareImage currentImage;


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

    private List<SquareImage> imageHistory;


    public DrawManager(JPanel parentPanel) {

        //set JPanel Colour
        setBackground(Color.WHITE);
        this.parentPanel = parentPanel;


        //default values
        this.shapeTool = ShapeTool.LINE;
        this.penColor = Color.BLACK;
        this.fill = false;
        this.fillColor = Color.BLACK;
        this.currentImage = new SquareImage(parentPanel.getHeight());

        imageHistory = new ArrayList<SquareImage>();


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                clickPoint = e.getPoint();

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
                        //update currentImage
                        currentImage.addShape(currentShape);
                        //add this version to history
                        SquareImage prevVersion = new SquareImage(currentImage);
                        imageHistory.add(prevVersion);

                        clickPoint = null;
                        currentShape = null;
                    };
                } else {
                    //her er releasepoint strengt tatt ikke oppdatert, skal vi da legge til det li
                    currentImage.addShape(currentShape);
                    //add this version to history
                    SquareImage prevVersion = new SquareImage(currentImage);
                    imageHistory.add(prevVersion);
                    clickPoint = null;
                    currentShape = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                releasePoint = e.getPoint();

                currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                repaint();

            }

            public void mouseMoved(MouseEvent e) {
                if (shapeTool == ShapeTool.POLYGON && clickPoint!=null){
                    releasePoint = e.getPoint();
                    currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                    //tester
                    repaint();
                }

            }
        });

    }


    @Override
    public Dimension getPreferredSize() {
        if (parentPanel.getHeight() > parentPanel.getWidth() && parentPanel.getHeight() != 0){
            currentImage.setSize(parentPanel.getWidth());
        } else if (parentPanel.getWidth() > parentPanel.getHeight() && parentPanel.getWidth() != 0) {
            currentImage.setSize(parentPanel.getHeight());
        }
        return new Dimension(currentImage.getSize(), currentImage.getSize());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        for (Shapes fig: currentImage.getShapes()){
            //draw all shapes that are added
            fig.draw(graphics, currentImage);
        }

        if (clickPoint != null) {
            currentShape.draw(g, currentImage);
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
        List<Shapes> currentImages = currentImage.getShapes();
        if(currentImages.size() > 0) {
            currentImages.removeAll(currentImages);
        }
        repaint();
    }

    public void load(String filepath){
        try {
            SquareImage newImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
            this.currentImage = newImage;
            currentImage.setSize(parentPanel.getHeight());
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String filepath){
        try{
            createVecFileFromImage(filepath, currentImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo(){
       List<Shapes> currentImages = currentImage.getShapes();
        if(currentImages.size() > 0) {
            currentImages.remove(currentImages.size() - 1 );
            //vet ikke helt hvordan undo skal være med her
            //SquareImage prevVersion = new SquareImage(currentImage);
            //imageHistory.add(prevVersion);
        }

        repaint();
    }

    public void newImageFromHistory(int historyIndex){
        currentImage = imageHistory.get(historyIndex);
        int a = 0;
        repaint();
    }

    public List<String> revealImageHistory(){
        System.out.println(currentImage.getCommandHistory());
        return currentImage.getCommandHistory();
    }


    public double toVecCoord(int pixel){
        return (double)pixel/ currentImage.getSize();
    }



}
