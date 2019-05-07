import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel{


    public ToolBar(){
        setBackground(Color.lightGray);
        JRadioButton lineButton = new JRadioButton("Line");
        JRadioButton ellipseButton = new JRadioButton("Ellipse");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        JRadioButton plotButton = new JRadioButton("Plot");


        lineButton.addActionListener(e -> System.out.println("update shape"));
        ellipseButton.addActionListener(e -> System.out.println("update"));
        rectangleButton.addActionListener(e -> System.out.println("update"));
        plotButton.addActionListener(e -> System.out.println("update"));

        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(plotButton);

    }

}
