import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileFilter;

import DrawManager.*;
import Shapes.Ellipse;


public class ToolBar extends JPanel{
    private DrawManager drawManager;
    private File file;


    public ToolBar(DrawManager drawManager){
        this.drawManager = drawManager;

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

        JRadioButton penColor = new JRadioButton("Pen color");
        JRadioButton fillColor = new JRadioButton("Fill color");

        ButtonGroup group = new ButtonGroup();
        group.add(penColor);
        group.add(fillColor);


        lineButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.LINE));
        ellipseButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.ELLIPSE));
        rectangleButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.RECTANGLE));
        plotButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.PLOT));
        polygonButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.POLYGON));
        undoButton.addActionListener(e -> System.out.println("Remove last thing"));
        redoButton.addActionListener(e -> System.out.println("redo"));


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawManager.clearCanvas();
            }
        });

        loadButton.addActionListener(new ActionListener() {
             @Override

             public void actionPerformed(ActionEvent e) {
                 JFileChooser fileChooser = new JFileChooser();
                 FileNameExtensionFilter filter = new FileNameExtensionFilter(
                         "VEC files", "vec");
                 fileChooser.setFileFilter(filter);

                 if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
                     String filePath = fileChooser.getSelectedFile().getPath();
                     drawManager.load(filePath);
                 }
             }
        });

        saveAsButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "VEC files", "vec");
                fileChooser.setFileFilter(filter);

                if (fileChooser.showOpenDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {

                    String filePath = fileChooser.getSelectedFile().getPath();
                    drawManager.save(filePath);
                }
            }
        });


        add(penColor);
        add(fillColor);
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
