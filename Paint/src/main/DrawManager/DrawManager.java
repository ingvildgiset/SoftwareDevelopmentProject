package DrawManager;

import Shapes.Polygon;
import Shapes.Rectangle;
import Shapes.*;
import SquareImage.SquareImage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static IO.VecFileManaging.createVecFileFromImage;


public class DrawManager extends JPanel {
    private JPanel parentPanel;

    //Drawing settings
    private ShapeTool shapeTool;
    private Color penColor;
    private Color fillColor;
    private boolean fill;
    private Shapes currentShape;
    private SquareImage currentImage;

    //Mouse coordinates
    private Point clickPoint;
    private Point releasePoint;

    //History variables
    private List<SquareImage> imageHistory;
    private Vector<String> commandHistory;


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
        commandHistory = new Vector<>();

        //Mouse events
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
                            double[] xVal = {toVecCoord(clickPoint.x), toVecCoord(clickPoint.x)};
                            double[] yVal = {toVecCoord(clickPoint.y), toVecCoord(clickPoint.y)};
                            currentShape = new Polygon(xVal.length, xVal, yVal, penColor, fill, fillColor);
                        }

                        break;
                    default:
                        //set back to default value
                       shapeTool = ShapeTool.PLOT;
                }
            }

            public void mouseReleased(MouseEvent e){

                if (shapeTool == ShapeTool.POLYGON && releasePoint != null){
                    //if drawing a Polygon drawing is not complete until back at start point
                    if(currentShape.addPoint(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y))){
                        //update currentImage
                        currentImage.addShape(currentShape);
                        //add this version to history
                        SquareImage prevVersion = new SquareImage(currentImage);
                        imageHistory.add(prevVersion);
                        commandHistory.add(currentShape.toString());

                        clickPoint = null;
                        currentShape = null;
                    }
                } else {
                    //figure is done, create shape and add to image
                    currentImage.addShape(currentShape);
                    //add this version to history
                    SquareImage prevVersion = new SquareImage(currentImage);
                    imageHistory.add(prevVersion);
                    commandHistory.add(currentShape.toString());
                    clickPoint = null;
                    currentShape = null;
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //draw while dragging, resize shape
                releasePoint = e.getPoint();
                currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                if (shapeTool == ShapeTool.POLYGON && clickPoint!=null){
                    //if drawing a polygon we want the line to follow mouse between click points
                    releasePoint = e.getPoint();
                    currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        for (Shapes fig: currentImage.getShapes()){
            //draw all shapes that are added to image
            fig.draw(graphics, currentImage);
        }
        if (clickPoint != null) {
            //display preview of the figure
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
        if(currentImage.getShapes().size() > 0) {
            currentImage.getShapes().removeAll(currentImage.getShapes());
        }
        repaint();
    }

    public void load(String filepath){
        try {
            this.currentImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
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
        }
        repaint();
    }

    public void newImageFromHistory(int historyIndex){
        //create a new image to add to the history
        currentImage = new SquareImage(imageHistory.get(historyIndex));
        SquareImage prevVersion = new SquareImage(currentImage);
        imageHistory.add(prevVersion);
        commandHistory.add("Edit from history");
        System.out.println(imageHistory);
        repaint();
    }

    public void previewImageFromHistory(int historyIndex){
        //make it possible to view the different image versions
        currentImage = imageHistory.get(historyIndex);
        repaint();
    }

    public int numberOfShapes(){
        return currentImage.getShapes().size();
    }

    public Vector<String> revealImageHistory(){
        return commandHistory;
    }

    //only for internal calculations
    private double toVecCoord(int pixel){
        return (double)pixel/ currentImage.getSize();
    }

}
