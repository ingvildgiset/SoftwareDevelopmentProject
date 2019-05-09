import javax.swing.*;
import java.awt.*;
import DrawManager.DrawManager;
import DrawManager.ShapeTool;


public class ToolBar extends JPanel{

    public ToolBar(){
        setBackground(Color.lightGray);
        JRadioButton lineButton = new JRadioButton("Line");
        JRadioButton ellipseButton = new JRadioButton("Ellipse");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        JRadioButton plotButton = new JRadioButton("Plot");

        //Må legge til at bare en checkbox kan velges.....


        lineButton.addActionListener(e -> System.out.println("linje"));//DrawManager.updateShape(ShapeTool.LINE));
        ellipseButton.addActionListener(e -> System.out.println("ellipse"));//DrawManager.updateShape(ShapeTool.ELLIPSE));
        rectangleButton.addActionListener(e -> System.out.println("rectangel"));//DrawManager.updateShape(ShapeTool.RECTANGLE));
        plotButton.addActionListener(e -> System.out.println("plot"));//DrawManager.updateShape(ShapeTool.PLOT));

        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(plotButton);

    }



}
