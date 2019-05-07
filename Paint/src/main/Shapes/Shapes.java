package Shapes;

import java.awt.*;


//burde kanskje ikke være en abstrakt klasse men et interface!!??!?!

public abstract class Shapes {
    private Color boarderColor, fillColor;
    private boolean fill;


    Shapes(Color boarderColor, boolean fill, Color fillColor){
        this.boarderColor = boarderColor;
        this.fill = fill;
        this.fillColor = fillColor;
    }



    public void setColor(){

    }
    public int getColor(){
        return 0;
    }



    public void draw(Graphics2D g) {

    }
}
