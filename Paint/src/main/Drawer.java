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





    public Drawer() {
        shapeSelection = Shape.RECTANGLE;
        currentColor = Color.BLUE;

        Image image = new Image(100, 100);
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

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = e.getPoint();
                int x = Math.min(clickPoint.x, dragPoint.x);
                int y = Math.min(clickPoint.y, dragPoint.y);


                switch(shapeSelection){
                    case LINE:
                        //create an object

                        Line newLine = new Line(clickPoint.x, clickPoint.y, dragPoint.x, dragPoint.y, currentColor);
                        //add object to image
                        image.addShape(newLine);
                        //draw on the screen
                        graphics.drawLine(clickPoint.x, clickPoint.y, dragPoint.x, dragPoint.y);
                        repaint();
                        break;
                    case RECTANGLE:
                        //create an object
                        Rectangle newRec = new Rectangle(clickPoint.x, clickPoint.y, dragPoint.x, dragPoint.y, currentColor);
                        //add object to image
                        image.addShape(newRec);
                        //draw on the screen
                        int width = Math.max(clickPoint.x, dragPoint.x) - x;
                        int height = Math.max(clickPoint.y, dragPoint.y) - y;
                        graphics.drawRect(x, y, width, height);
                        repaint();
                        break;
                    default:
                        System.out.println("Feil valg av shape");
                }


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



}
