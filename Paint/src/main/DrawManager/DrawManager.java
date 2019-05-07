import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import Shape.Image;
import Shape.Plot;
import Shape.Line;
import Shape.Rectangle;




enum Shape {
    PLOT, LINE, RECTANGLE, ELLIPSE, POLYGON
}


public class Drawer extends JPanel {

    //usikker på om vi trenger et image enda??
    private java.awt.Image canvas;
    private Graphics2D graphics;

    //denne må oppdateres av toolBar
    private Shape shapeSelection;
    private Color currentColor;

    // Mouse coordinates
    private Point clickPoint;
    private Point currentPoint;

    private Image image;





    public Drawer() {
        shapeSelection = Shape.RECTANGLE;
        currentColor = Color.BLUE;

        image = new Image(100, 100);
        System.out.println("vi har lagt et drawArea");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                clickPoint = e.getPoint();
                System.out.println("pressed");


                //create an object
                Plot newplot = new Plot(clickPoint.x, clickPoint.y, currentColor);
                //add object to image
                image.addShape(newplot);
                //draw on the screen
                //kan man farge bare ett punkt?????

                //graphics.drawLine(clickPoint.x, clickPoint.y, clickPoint.x+1, clickPoint.y);
                repaint();
            }
        });


        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                // save coord x,y when mouse is pressed
                currentPoint= e.getPoint();
                System.out.println("sluppet");
                draw();
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = e.getPoint();
                int x = Math.min(clickPoint.x, dragPoint.x);
                int y = Math.min(clickPoint.y, dragPoint.y);

                //dette må vi lage sener

                repaint();


            }
        });



    }


    protected void paintComponent(Graphics g) {
        if (canvas == null) {
            // create image
            canvas = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) canvas.getGraphics();
            graphics.setColor(currentColor);
            // enable antialiasing
            // clear draw area
            //lag et blankt bilde
        }

        g.drawImage(canvas, 0, 0, null);
    }


    public void updateShapeSelection(){
        //her må vi velge hvilken shape som skal tegnes

    }

    public void updateColor(){

    }

    public void draw(){
        switch(shapeSelection){
            case LINE:
                graphics.drawLine(clickPoint.x, clickPoint.y, currentPoint.x, currentPoint.y);
                repaint();
                break;
            case RECTANGLE:
                int width = Math.abs(currentPoint.x - clickPoint.x);
                int height = Math.abs(currentPoint.y - clickPoint.y);
                System.out.println(width);
                System.out.println(height);
                graphics.drawRect(Math.min(clickPoint.x, currentPoint.x), Math.min(clickPoint.y, currentPoint.y), width, height);
                repaint();
                break;
            default:
                System.out.println("Feil valg av shape");
        }

    }




}
