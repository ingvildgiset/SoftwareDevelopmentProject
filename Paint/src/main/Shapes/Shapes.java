package Shapes;

import SquareImage.SquareImage;

import java.awt.*;


//burde kanskje ikke være en abstrakt klasse men et interface!!??!?!

public abstract class Shapes {
    //alle må ha disse variablene
    private Color boarderColor, fillColor;
    private boolean fill;


    Shapes(Color boarderColor, boolean fill, Color fillColor){
        this.boarderColor = boarderColor;
        this.fill = fill;
        this.fillColor = fillColor;
    }

    public Color getBoarderColor(){ return boarderColor; }

    public Color getFillColor(){
        return fillColor;
    }

    public boolean isFilled(){
        return fill;
    }


    //alle må implementere disse to metodene.
    //Disse vil være forskjellig for alle objektene. Vi lager de derfor abstrakte
    public abstract String toVecFormat();
    public abstract void draw(Graphics g, SquareImage image);
    public abstract void update(double x2, double y2);
    public abstract boolean addPoint(double x2, double y2);

    public String colorToVecFormat() {
        String vec = "";
        if (getBoarderColor() != Color.BLACK){
            vec +=  "PEN #" + Integer.toHexString(getBoarderColor().getRGB()).substring(2) + "\n";
        }
        if (isFilled()){
            vec += "FILL #" + Integer.toHexString(getFillColor().getRGB()).substring(2) + "\n";
        } else {
            vec += "FILL OFF\n";
        }
        return vec;
    }

}
