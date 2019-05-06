import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {


    public Paint(String windowName) {
        super(windowName);

        //add panels or components
        DrawArea drawArea = new DrawArea();
        ToolBar shapeMenu = new ToolBar();
        ToolBar colours = new ToolBar();

        //set layout
        setLayout(new GridLayout(1, 3));

        add(shapeMenu, BorderLayout.CENTER);
        add(drawArea, BorderLayout.CENTER);
        add(colours, BorderLayout.CENTER);

        //set features for frame
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}







