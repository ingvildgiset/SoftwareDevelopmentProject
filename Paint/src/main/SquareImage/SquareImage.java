package SquareImage;

import Shapes.Shapes;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Class for the actual Image that is showed on Canvas.
 * Image is always square and uses size to convert between vector coordinates
 * and pixels.
 * Contains a list off all shapes that are drawn at the Image.
 */
public class SquareImage{
    private int size;
    private List<Shapes> shapes;
    private Vector<String> commandHistory;

    /** Creates a Image with specified size
     * @param size the size for the image. Image is always square.
     */
    public SquareImage(int size){
        this.size = size;
        shapes = new ArrayList<Shapes>();
        commandHistory = new Vector<>();
    }

    /** Creates a deep Copy of the image object
     * @param image the object to take a copy off
     */
    public SquareImage(SquareImage image)
    {
        this.size = image.size;
        shapes = new ArrayList<Shapes>(image.shapes);
        commandHistory = new Vector<>(image.commandHistory);
    }

    /**
     * Returns the size of the Image.
     * @return size
     */
    public int getSize(){
        return size;
    }

    /**
     * Changes the size of the Image.
     * @param size size of Image
     */
    public void setSize(int size){
        this.size = size;
    }

    /**
     * Adds a shape object to the list that stores all shapes.
     * @param shape Shape object to add to the picture
     */
    public void addShape(Shapes shape){
        shapes.add(shape);
        commandHistory.add(shape.toString());
    }

    /**
     * Returns the
     * @return A list object containing all Shapes drawn at the Image object.
     */
    public List<Shapes> getShapes(){
        return shapes;
    }

    public Vector<String> getCommandHistory(){
        return commandHistory;
    }

}





