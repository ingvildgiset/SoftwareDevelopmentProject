import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.IdentityHashMap;

import DrawManager.*;



public class ToolBar extends JPanel{
    private DrawManager drawManager;
    private File file;


    public ToolBar(DrawManager drawManager){
        this.drawManager = drawManager;

        setLayout(new FlowLayout());
        //setLayout(new FlowLayout(FlowLayout.LEFT, 1,1));
        setBackground(Color.lightGray);
        setFocusable(true);


        Icon rectangle = new ImageIcon(getClass().getResource("images/rectangle.png"));
        JButton rectangleButton = new JButton(rectangle);

        Icon ellipse = new ImageIcon(getClass().getResource("images/ellipse.png"));
        JButton ellipseButton = new JButton(ellipse);

        Icon line = new ImageIcon(getClass().getResource("images/line.png"));
        JButton lineButton = new JButton(line);

        Icon dot = new ImageIcon(getClass().getResource("images/dot.png"));
        JButton plotButton = new JButton(dot);

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

        lineButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.LINE));
        ellipseButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.ELLIPSE));
        rectangleButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.RECTANGLE));
        plotButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.PLOT));
        polygonButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.POLYGON));

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("undo");
                drawManager.undo();
            }
        });

        // CTRL+Z Keybindings
        Object undoAction = new Object();
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((KeyEvent.VK_Z), InputEvent.CTRL_MASK),undoAction);
        getActionMap().put(undoAction, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.err.println("escape 1");
                drawManager.undo();
            }
        });

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
