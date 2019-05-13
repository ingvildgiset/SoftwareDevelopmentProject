import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// LAGER REKTANGEL MENS DU FLYTTER MUSEN

public class SelectExample {

    public static void main(String[] args) {
        new SelectExample();
    }

    public SelectExample() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {
        private Rectangle selection;

        public TestPane() {
            MouseAdapter ma = new MouseAdapter() {
                private Point clickPoint;

                @Override
                public void mousePressed(MouseEvent e) {
                    clickPoint = e.getPoint();
                    selection = null;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Point dragPoint = e.getPoint();
                    int x = Math.min(clickPoint.x, dragPoint.x);
                    int y = Math.min(clickPoint.y, dragPoint.y);
                    int width = Math.max(clickPoint.x, dragPoint.x) - x;
                    int height = Math.max(clickPoint.y, dragPoint.y) - y;
                    if (selection == null) {
                        selection = new Rectangle(x, y, width, height);
                    } else {
                        selection.setBounds(x, y, width, height);
                    }
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    selection = null;
                    repaint();
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        protected void paintComponent(Graphics g) {
            System.out.println("HELLOO");
            super.paintComponent(g);
            if (selection != null) {
                g.setColor(UIManager.getColor("List.selectionBackground"));
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
                g2d.fill(selection);
                g2d.dispose();
                g2d = (Graphics2D) g.create();
                g2d.draw(selection);
                //g2d.dispose();
            }
        }

    }

}