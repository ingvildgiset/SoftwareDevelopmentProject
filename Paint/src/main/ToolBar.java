import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import DrawManager.ShapeEnum;
import DrawManager.DrawManager;


public class ToolBar extends JPanel{


    public ToolBar(){
        setLayout(new FlowLayout());
        //setLayout(new FlowLayout(FlowLayout.LEFT, 1,1));
        setBackground(Color.lightGray);

        Icon rectangle = new ImageIcon(getClass().getResource("images/rectangle.png"));
        JButton rectangleButton = new JButton(rectangle);

        Icon ellipse = new ImageIcon(getClass().getResource("images/ellipse.png"));
        JButton ellipseButton = new JButton(ellipse);

        Icon line = new ImageIcon(getClass().getResource("images/line.png"));
        JButton lineButton = new JButton(line);

        JButton plotButton = new JButton("Plot");

        Icon poly = new ImageIcon(getClass().getResource("images/star.png"));
        JButton polygonButton = new JButton(poly);

        Icon undo = new ImageIcon(getClass().getResource("images/undo.png"));
        JButton undoButton = new JButton(undo);

        Icon redo = new ImageIcon(getClass().getResource("images/redo.png"));
        JButton redoButton = new JButton(redo);

        Icon save = new ImageIcon(getClass().getResource("images/save.png"));
        JButton saveButton = new JButton(save);
        JButton saveAsButton = new JButton("Save As");

        JButton clearButton = new JButton("Clear All");
        JButton loadButton = new JButton("Load");

        int saveCounter = 0;
        File file;

        lineButton.addActionListener(e -> System.out.println("update shape"));
        ellipseButton.addActionListener(e -> System.out.println("update"));
        rectangleButton.addActionListener(e -> System.out.println("update"));
        plotButton.addActionListener(e -> System.out.println("update"));
        polygonButton.addActionListener(e -> System.out.println("update shape"));
        undoButton.addActionListener(e -> System.out.println("Remove last thing"));
        redoButton.addActionListener(e -> System.out.println("redo"));

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clear");
            }
        });

       /* loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                }
            }*/


        add(undoButton);
        add(redoButton);
        add(saveButton);
        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(polygonButton);
        add(plotButton);
        add(clearButton);
        add(saveAsButton);
        add(loadButton);
    }



}
