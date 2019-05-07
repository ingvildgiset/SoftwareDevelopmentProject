package Shape;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Image {
    private int width;
    private int height;
    private List<Shape> shapes;

    public Image(int height, int width){
        width = width;
        height = height;

        shapes = new ArrayList<Shape>();

    }


    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public int getWidth(ImageObserver observer) {
        return width;
    }

    public int getHeight(ImageObserver observer) {
        return height;
    }



}
