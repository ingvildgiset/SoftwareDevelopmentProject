package Image;

import Shapes.Shapes;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Image {
    private int width;
    private int height;
    private List<Shapes> shapes;

    public Image(int height, int width){
        this.width = width;
        this.height = height;

        shapes = new ArrayList<Shapes>();

    }

    public int getWidth(ImageObserver observer) {
        return width;
    }

    public int getHeight(ImageObserver observer) {
        return height;
    }

    public void addShape(Shapes shape){
        System.out.println("Skal legge til en ny shape");
        shapes.add(shape);
    }


    public void drawAll(Graphics2D graphics){

        for (Shapes shapeObj: shapes) {
            shapeObj.draw(graphics);
        }
    }

}





