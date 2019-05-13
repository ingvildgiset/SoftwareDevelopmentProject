import javax.swing.*;
import java.awt.*;
import DrawManager.ShapeEnum;
import DrawManager.DrawManager;


public class ToolBar extends JPanel{

    public ToolBar(){
        setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        setBackground(Color.lightGray);
        JRadioButton lineButton = new JRadioButton("Line");
        JRadioButton ellipseButton = new JRadioButton("Ellipse");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        JRadioButton plotButton = new JRadioButton("Plot");
        JRadioButton polygonButton = new JRadioButton("Polygon");
        JButton undoButton = new JButton("Undo");


        ButtonGroup myButtonGroup = new ButtonGroup();
        myButtonGroup.add(lineButton);
        myButtonGroup.add(ellipseButton);
        myButtonGroup.add(rectangleButton);
        myButtonGroup.add(plotButton);
        myButtonGroup.add(polygonButton);

        lineButton.addActionListener(e -> System.out.println("update shape"));
        ellipseButton.addActionListener(e -> System.out.println("update"));
        rectangleButton.addActionListener(e -> System.out.println("update"));
        plotButton.addActionListener(e -> System.out.println("update"));
        polygonButton.addActionListener(e -> System.out.println("update shape"));
        undoButton.addActionListener(e -> System.out.println("Remove last thing"));


        add(undoButton, BorderLayout.WEST);
        add(lineButton, BorderLayout.CENTER);
        add(ellipseButton, BorderLayout.CENTER);
        add(rectangleButton, BorderLayout.CENTER);
        add(polygonButton, BorderLayout.CENTER);
        add(plotButton, BorderLayout.CENTER);

    }



}
