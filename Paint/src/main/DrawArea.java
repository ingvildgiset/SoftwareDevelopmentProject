import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


//I denne må vi legge til slipped.

public class DrawArea extends JPanel {

    // Graphics2D object ==> used to draw on
    private Graphics2D graphics;

    //denne må oppdateres av toolBar
    private Rectangle shapeSelection;

    // Mouse coordinates
    private Point clickPoint;

    public DrawArea() {
        System.out.println("vi har lagt et drawArea");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                clickPoint = e.getPoint();
                System.out.println("pressed");
                System.out.println(clickPoint.x);
                System.out.println(clickPoint.y);
                plot();

            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = e.getPoint();
                int x = Math.min(clickPoint.x, dragPoint.x);
                int y = Math.min(clickPoint.y, dragPoint.y);
                int width = Math.max(clickPoint.x, dragPoint.x) - x;
                int height = Math.max(clickPoint.y, dragPoint.y) - y;

                //sjekk shape, si at det er rektangel
                if (shapeSelection == null){
                    shapeSelection = new Rectangle(x, y, width, height);
                } else {
                    shapeSelection.setBounds(x, y, width, height);
                }

                repaint();
                System.out.println("dragged");

            }
        });
    }

    public void updateShapeSelection(){
        //her må vi velge hvilken shape som skal tegnes

    }

    public void plot(){
        graphics.drawLine(clickPoint.x, clickPoint.y, clickPoint.x+1, clickPoint.y+1);
    }

}
