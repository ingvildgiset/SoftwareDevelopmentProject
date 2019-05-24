package SquareImage;

import Shapes.Shapes;
import java.util.ArrayList;
import java.util.List;

public class SquareImage{
    private int size;
    private List<Shapes> shapes;

    public SquareImage(int size){
        this.size = size;
        shapes = new ArrayList<Shapes>();
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void addShape(Shapes shape){
        shapes.add(shape);
    }

    public List<Shapes> getShapes(){
        return shapes;
    }



}





