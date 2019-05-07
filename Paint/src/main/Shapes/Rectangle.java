package Shape;

import java.awt.*;

public class Rectangle extends Shape{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int width;
    private int height;

    private Color color;


    public Rectangle(int x1, int y1, int x2, int y2, Color color){
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = Math.abs(x1 - x2);
        this.width = Math.abs(y1 - y2);

    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

    @Override
    public void draw(Graphics2D graphics ) {
        graphics.drawRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
    }
}
