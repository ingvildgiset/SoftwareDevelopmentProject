package Shape;

import java.awt.*;

public abstract class Shape {
    private boolean fill;
    private Color color;


    Shape(Color color){
        this.color = color;
    }



    public void setColor(){

    }
    public int getColor(){
        return 0;
    }



    public void draw(Graphics2D g) {

    }
}
