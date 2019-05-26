package DrawManager;

//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
//dependencies
import Shapes.Polygon;
import Shapes.Rectangle;
import Shapes.*;
import SquareImage.SquareImage;


/**
 * Class that handles drawing from user. Drawing is done by drag and drop.
 * Shapes are saved in a SquareImage object. This can be saved and loaded to file.
 * All edits will be tracked in a history.
 */
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

    /**
     * Creates the GUI for the canvas. Add mouse listeners to track click, dragging and movement.
     * @param parentPanel the panel that the canvas is displayed within.
     */
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

            /**
             * Creates a new shapes object if the mouse is pressed. What kind of shape depends on the shapeTool.
             * @param e mouseEvent
             */
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
                        //to check if we are just adding a point or creating a new polygon.
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

            /**
             * When the mouse is released the figure is done. Adds shape to image and stores a version of this image
             * and the command to the history.
             * @param e mouse event
             */
            public void mouseReleased(MouseEvent e){
                //if drawing a Polygon drawing, check if the polygon is complete
                if (shapeTool == ShapeTool.POLYGON && releasePoint != null){
                    //check if adding a point or completing the shape
                    if(currentShape.addPoint(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y))){

                        //shape complete, add shape and store image version and command to history.
                        currentImage.addShape(currentShape);
                        SquareImage prevVersion = new SquareImage(currentImage);
                        imageHistory.add(prevVersion);
                        commandHistory.add(currentShape.toString());

                        //reset variables
                        clickPoint = null;
                        currentShape = null;
                    }
                } else {
                    //figure is done, create shape and add to image and command to history
                    currentImage.addShape(currentShape);
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
            /**
             * Resize shapes while dragging the mouse.
             * @param e Mouse event
             */
            public void mouseDragged(MouseEvent e) {
                //draw while dragging, resize shape
                releasePoint = e.getPoint();
                currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                repaint();
            }

            /**
             * For drawing a polygon we click for adding a point. Therefore want the line to follow
             * movement between clicks.
             * @param e Mouse event
             */
            public void mouseMoved(MouseEvent e) {
                if (shapeTool == ShapeTool.POLYGON && clickPoint!=null){
                    releasePoint = e.getPoint();
                    currentShape.resize(toVecCoord(releasePoint.x), toVecCoord(releasePoint.y));
                    repaint();
                }
            }
        });
    }

    /**
     * Make sure that the JPanel is always squared.
     * @return new dimension of the JPanel
     */
    @Override
    public Dimension getPreferredSize() {
        if (parentPanel.getHeight() > parentPanel.getWidth() && parentPanel.getHeight() != 0){
            currentImage.setSize(parentPanel.getWidth());
        } else if (parentPanel.getWidth() > parentPanel.getHeight() && parentPanel.getWidth() != 0) {
            currentImage.setSize(parentPanel.getHeight());
        }
        return new Dimension(currentImage.getSize(), currentImage.getSize());
    }

    /**
     * Paints all the added shapes on the JPanel.
     * @param g graphic object to draw
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        for (Shapes fig: currentImage.getShapes()){
            //draw all shapes that are added to image
            fig.draw(graphics, currentImage.getSize());
        }
        if (clickPoint != null) {
            //display preview of the figure while dragging
            currentShape.draw(g, currentImage.getSize());
        }
    }

    /**
     * update shape to be drawn
     * @param shapeTool drawing command
     */
    public void setShapeTool(ShapeTool shapeTool){
        this.shapeTool = shapeTool;
    }

    /**
     * change pen color
     * @param color pen color
     */
    public void setPenColor(Color color){
        this.penColor = color;
    }

    /**
     * change fill color
     * @param color fil color
     */
    public void setFillColor(Color color){
        this.fillColor = color;
        this.fill = true;
    }

    /**
     * turn fill off
     */
    public void fillOff(){
        this.fill = false;
    }

    /**
     * remove all shapes from canvas
     */
    public void clearCanvas(){
        if(currentImage.getShapes().size() > 0) {
            currentImage.getShapes().removeAll(currentImage.getShapes());
        }
        repaint();
    }

    /**
     * load a new squareImage from file
     * @param filepath location of file
     */
    public void load(String filepath){
        try {
            this.currentImage = IO.VecFileManaging.constructImageFromVecFile(filepath);
            currentImage.setSize(parentPanel.getHeight());
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * save squareImage to vec file
     * @param filepath location to save file
     */
    public void save(String filepath){
        try{
            IO.VecFileManaging.createVecFileFromImage(filepath, currentImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * undo last drawing command. Removes last shape from image.
     */
    public void undo(){
       List<Shapes> currentImages = currentImage.getShapes();
        if(currentImages.size() > 0) {
            commandHistory.add("Undo " + currentImages.get(currentImages.size() - 1).toString());
            currentImages.remove(currentImages.size() - 1 );
            SquareImage prevVersion = new SquareImage(currentImage);
            imageHistory.add(prevVersion);

        }
        repaint();
    }

    /**
     * go back to earlier version of drawing.
     * @param historyIndex image version.
     */
    public void newImageFromHistory(int historyIndex){
        //make sure index inside bounds
        if (historyIndex < imageHistory.size() && historyIndex >= 0){
            currentImage = new SquareImage(imageHistory.get(historyIndex));
            SquareImage prevVersion = new SquareImage(currentImage);
            imageHistory.add(prevVersion);
            commandHistory.add("Edit from history");
            System.out.println(imageHistory);
            repaint();
        }

    }

    /**
     * preview another version of the image
     * @param historyIndex image version
     */
    public void previewImageFromHistory(int historyIndex){
        //make it possible to view the different image versions
        currentImage = imageHistory.get(historyIndex);
        repaint();
    }

    /**
     * check if the image is edited
     * @return true if image is edited
     */
    public boolean imageEdited(){
        return currentImage.getShapes().size() > 0;
    }

    /**
     * return all edit commands
     * @return vector containing all edit commands
     */
    public Vector<String> revealImageHistory(){
        return commandHistory;
    }

    /**
     * calculate between clickpoint on canvas and vec coordinates. Only for
     * internal use in class
     * @param canvasPos mouseclick position on canvas
     * @return vector coordinate. Number between 0 and 1.
     */
    private double toVecCoord(int canvasPos){
        return (double)canvasPos/ currentImage.getSize();
    }

}
