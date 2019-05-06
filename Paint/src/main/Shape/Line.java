package Shape;

import java.awt.*;

public class Line extends Shape{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Color color;


    public Line(int x1, int y1, int x2, int y2, Color color){
        x1 = x1;
        y1 = y1;
        x2 = x2;
        y2 = y2;

        color = color;

    }

    @Override
    public void draw() {
        //her må vi gjøre noe greier
    }
}
