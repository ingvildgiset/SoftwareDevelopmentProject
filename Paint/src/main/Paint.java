import DrawManager.DrawManager;
import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {

    public Paint(String windowName) {
        super(windowName);

        //add panels or components
        DrawManager drawManager = new DrawManager();
        ToolBar shapeMenu = new ToolBar();
        ColorBar colorMenu = new ColorBar();



        
        //set layout
        setLayout(new BorderLayout());
        add(shapeMenu, BorderLayout.NORTH);
        add(drawManager, BorderLayout.CENTER);
        add(colorMenu, BorderLayout.WEST);
        colorMenu.setPreferredSize(new Dimension(200,300));
        // fix position and how much place the panel gets!! To minimize colorBar

        //set features for frame
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {
        Paint myPaint = new Paint("Paint window");
    }

}







