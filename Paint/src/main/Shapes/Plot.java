package Shape;

import java.awt.*;

public class Plot extends Shape {
    private int x;
    private int y;
    Color color;


    public Plot(int x, int y, Color color){
        super(color);
        x = x;
        y = y;
        color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        //her må vi gjøre noe greier
    }
}
