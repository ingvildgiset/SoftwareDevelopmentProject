package SquareImage;

import Shapes.Shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SquareImage{
    private int size;
    private List<Shapes> shapes;

    public SquareImage(int size){
        this.size = size;
        shapes = new ArrayList<Shapes>();
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
            shapeObj.draw(graphics, this);
        }
    }

    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size = size;
    }

}





