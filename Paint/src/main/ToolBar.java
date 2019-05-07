import javax.swing.*;
import java.awt.*;
import DrawManager.ShapeEnum;
import DrawManager.DrawManager;


public class ToolBar extends JPanel{

    public ToolBar(){
        setBackground(Color.lightGray);
        JRadioButton lineButton = new JRadioButton("Line");
        JRadioButton ellipseButton = new JRadioButton("Ellipse");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        JRadioButton plotButton = new JRadioButton("Plot");
        JRadioButton polygonButton = new JRadioButton("Polygon");

        //Må legge til at bare en checkbox kan velges.....


        lineButton.addActionListener(e -> DrawManager.updateShape(ShapeEnum.LINE));
        ellipseButton.addActionListener(e -> DrawManager.updateShape(ShapeEnum.ELLIPSE));
        rectangleButton.addActionListener(e -> DrawManager.updateShape(ShapeEnum.RECTANGLE));
        plotButton.addActionListener(e -> DrawManager.updateShape(ShapeEnum.PLOT));
        polygonButton.addActionListener(e -> DrawManager.updateShape(ShapeEnum.POLYGON));

        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(plotButton);

    }



}
