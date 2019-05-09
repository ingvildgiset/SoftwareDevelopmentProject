import javax.swing.*;
import java.awt.*;
import DrawManager.DrawManager;
import DrawManager.ShapeTool;


public class ToolBar extends JPanel{

    public ToolBar(){
        setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
        setBackground(Color.lightGray);
        JRadioButton lineButton = new JRadioButton("Line");
        JRadioButton ellipseButton = new JRadioButton("Ellipse");
        JRadioButton rectangleButton = new JRadioButton("Rectangle");
        JRadioButton plotButton = new JRadioButton("Plot");
        JRadioButton polyButton = new JRadioButton("Polymorphism");
        JButton undoButton = new JButton("Undo");

        //Må legge til at bare en checkbox kan velges.....

        ButtonGroup myButtonGroup = new ButtonGroup();
        myButtonGroup.add(lineButton);
        myButtonGroup.add(ellipseButton);
        myButtonGroup.add(rectangleButton);
        myButtonGroup.add(plotButton);
        myButtonGroup.add(polyButton);

        lineButton.addActionListener(e -> System.out.println("linje"));
        ellipseButton.addActionListener(e -> System.out.println("ellipse"));
        rectangleButton.addActionListener(e -> System.out.println("rectangle"));
        plotButton.addActionListener(e -> System.out.println("plot"));
        polyButton.addActionListener(e -> System.out.println("poly"));
        undoButton.addActionListener(e -> System.out.println("undo"));


        add(undoButton, BorderLayout.WEST);
        add(lineButton, BorderLayout.CENTER);
        add(ellipseButton, BorderLayout.CENTER);
        add(rectangleButton, BorderLayout.CENTER);
        add(polyButton, BorderLayout.CENTER);
        add(plotButton, BorderLayout.CENTER);

    }



}
