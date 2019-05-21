import DrawManager.DrawManager;
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


        JFrame frame = new JFrame("Paint");
        frame.setSize(900, 900);


        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());


        JPanel fixedPanel = new JPanel(new GridBagLayout());
        fixedPanel.setBackground(Color.lightGray);


        DrawManager drawManager = new DrawManager(fixedPanel);
        ToolBar shapeMenu = new ToolBar(drawManager);
        ColorBar colorMenu = new ColorBar(drawManager);
        colorMenu.setPreferredSize(new Dimension(50, 100));


        //creating a scrollPane with drawManager as client
        JScrollPane scrollPane = new JScrollPane(drawManager);




        fixedPanel.add(scrollPane);



        fixedPanel.add(scrollPane);
        container.add(colorMenu, BorderLayout.WEST);
        container.add(fixedPanel, BorderLayout.CENTER);
        container.add(shapeMenu, BorderLayout.NORTH);



        //set features for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        Paint myPaint = new Paint();
    }

}







