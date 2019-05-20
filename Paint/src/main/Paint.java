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


        System.out.println("Creating fixed panel");
        JPanel fixedPanel = new JPanel(new GridBagLayout());
        fixedPanel.setBackground(Color.lightGray);


        System.out.println("Creating drawManager");
        DrawManager drawManager = new DrawManager(fixedPanel);
        ToolBar shapeMenu = new ToolBar(drawManager);
        ColorBar colorMenu = new ColorBar(drawManager);
        colorMenu.setPreferredSize(new Dimension(50, 100));


        //creating a scrollPane with drawManager as client
        System.out.println("Creating scrollPane");
        JScrollPane scrollPane = new JScrollPane(drawManager);





        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = constraints.BOTH;
        fixedPanel.add(scrollPane, constraints);





        System.out.println("adding to fixedPanel");
        fixedPanel.add(scrollPane);
        System.out.println("adding to container");
        container.add(colorMenu, BorderLayout.WEST);
        System.out.println("adding ading to contaienr");
        container.add(fixedPanel, BorderLayout.CENTER);
        System.out.println("adding to container");
        container.add(shapeMenu, BorderLayout.NORTH);



        //set features for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        Paint myPaint = new Paint();
    }

}







