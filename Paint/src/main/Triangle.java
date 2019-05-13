import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Triangle extends JFrame {
    Point startDrag, endDrag, midPoint;
    private java.util.List<Polygon> triangles = new LinkedList<Polygon>();
    public static void main(String[] args)
    {
        Triangle t = new Triangle();
    }
    Triangle()
    {
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseMotionListener);
        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            startDrag = new Point(e.getX(), e.getY());
            endDrag = startDrag;
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (startDrag.x > endDrag.x)
                midPoint = new Point((endDrag.x +(Math.abs(startDrag.x - endDrag.x)/2)),e.getY());
            else
                midPoint = new Point((endDrag.x -(Math.abs(startDrag.x - endDrag.x)/2)),e.getY());
            int[] xs = { startDrag.x, endDrag.x, midPoint.x };
            int[] ys = { startDrag.y, startDrag.y, midPoint.y };
            triangles.add( new Polygon(xs, ys, 3));
            startDrag = null;
            endDrag = null;
            repaint();
        }
    };
    MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            endDrag = new Point(e.getX(), e.getY());
            repaint();
        }
    };


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Polygon triangle : triangles)
            g2.drawPolygon(triangle);
    }
}