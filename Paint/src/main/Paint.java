import javax.swing.*;
import java.awt.*;

public class Paint {


    public Paint() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }


        JFrame frame = new JFrame("Paint ("+ 900 +"X" + 900 +")");


        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        //create all panels
        Drawer drawer = new Drawer();
        ToolBar shapeMenu = new ToolBar();
        ColorBar colorMenu = new ColorBar();
        //another panel to file

        container.add(drawer, BorderLayout.CENTER);
        container.add(shapeMenu, BorderLayout.NORTH);
        container.add(colorMenu, BorderLayout.WEST);





        colorMenu.setPreferredSize(new Dimension(200,300));
        // fix position and how much place the panel gets!! To minimize colorBar

        //set features for frame
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}







