package Shapes;

import java.awt.*;

public class Plot extends Shapes {
    private double x;
    private double y;
    Color color;


    public Plot(double x, double y, Color color){
        super(color, false, null);
        this.x = x;
        this.y = y;
    }

    @Override
    public String toVecFormat() {
        return null;
    }

    @Override
    public void draw(Graphics2D g) {
        //her må vi gjøre noe greier
    }
}
