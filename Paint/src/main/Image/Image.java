package Image;

import Shapes.Shapes;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
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
        System.out.println("add shape");
        shapes.add(shape);
    }

    public List<Shapes> getShapes(){
        return shapes;
    }


    public void drawAll(Graphics2D graphics){
        System.out.println("tegner alle");
        for (Shapes shapeObj: shapes) {
            shapeObj.draw(graphics);
        }
    }

}





