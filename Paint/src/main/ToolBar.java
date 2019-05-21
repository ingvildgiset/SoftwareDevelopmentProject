import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import DrawManager.*;



public class ToolBar extends JPanel{
    private DrawManager drawManager;
    private File file;
    double zoomMultiplier;

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

        JButton saveAsButton = new JButton("Save As");

        JButton clearButton = new JButton("Clear All");
        JButton loadButton = new JButton("Load");

        String[] zoomStrings = {"50%", "75%", "100%", "125%", "200%", "300%"};
        //Creates ComboBox
        final JComboBox zoomList = new JComboBox(zoomStrings);
        zoomList.setSelectedIndex(2);
        zoomList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zoom = (String) zoomList.getSelectedItem();
                if (zoom == "50%") {
                    zoomMultiplier = 0.5;
                    System.out.println(zoomMultiplier);
                } else if (zoom == "75%") {
                    zoomMultiplier = 0.75;
                    System.out.println(zoomMultiplier);
                } else if (zoom == "100%") {
                    zoomMultiplier = 1;
                    System.out.println(zoomMultiplier);
                } else if (zoom == "125%") {
                    zoomMultiplier = 1.25;
                    System.out.println(zoomMultiplier);
                } else if (zoom == "200%"){
                    zoomMultiplier = 2;
                } else if (zoom == "300%"){
                    zoomMultiplier = 3;
                }
            }
            });

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

                if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    drawManager.save(filePath);
                }
            }
        });

        add(undoButton);
        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(polygonButton);
        add(plotButton);
        add(clearButton);
        add(saveAsButton);
        add(loadButton);
        add(zoomList);
    }
}
