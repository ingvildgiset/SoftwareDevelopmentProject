package Menus;

//imports
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;

//dependencies
import DrawManager.DrawManager;
import DrawManager.ShapeTool;


/**
 * Creates a ToolBar that contains buttons for all the different shapes (rectangle, ellipse, line and plot).
 * It also has buttons for load/save image. Implements a undo button. Using a key binding this event
 * can also be triggered by CTRL + Z. Creates a dialog box to view image history if "view history" button is pushed.
 */

public class ToolBar extends JPanel{

    public ToolBar(DrawManager drawManager, JFrame parent){
        //set layout for panel
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        setFocusable(true);

        //import icons
        Icon rectangleIcon = new ImageIcon(getClass().getResource("../images/rectangle.png"));
        Icon ellipseIcon = new ImageIcon(getClass().getResource("../images/ellipse.png"));
        Icon lineIcon = new ImageIcon(getClass().getResource("../images/line.png"));
        Icon dotIcon = new ImageIcon(getClass().getResource("../images/dot.png"));
        Icon polyIcon = new ImageIcon(getClass().getResource("../images/star.png"));
        Icon undoIcon = new ImageIcon(getClass().getResource("../images/undo.png"));

        //create buttons
        JButton rectangleButton = new JButton(rectangleIcon);
        JButton ellipseButton = new JButton(ellipseIcon);
        JButton lineButton = new JButton(lineIcon);
        JButton plotButton = new JButton(dotIcon);
        JButton polygonButton = new JButton(polyIcon);
        JButton undoButton = new JButton(undoIcon);
        JButton saveAsButton = new JButton("Save As");
        JButton clearButton = new JButton("Clear All");
        JButton loadButton = new JButton("Load");
        JButton historyButton = new JButton("View history");


        //add actionListeners to buttons
        lineButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.LINE));
        ellipseButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.ELLIPSE));
        rectangleButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.RECTANGLE));
        plotButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.PLOT));
        polygonButton.addActionListener(e -> drawManager.setShapeTool(ShapeTool.POLYGON));
        undoButton.addActionListener(e -> drawManager.undo());
        clearButton.addActionListener(e -> drawManager.clearCanvas());

        historyButton.addActionListener(e -> {
            JDialog historyDialog = new HistoryDialog(parent, drawManager);
            historyDialog.show();
        });

        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //filter on vec files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "VEC files", "vec");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(loadButton) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                drawManager.load(filePath);
            }
        });

        saveAsButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //filter on vec files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "VEC files", "vec");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(saveAsButton) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                drawManager.save(filePath);
            }
        });

        //add buttons to panel
        add(undoButton);
        add(lineButton);
        add(ellipseButton);
        add(rectangleButton);
        add(polygonButton);
        add(plotButton);
        add(clearButton);
        add(saveAsButton);
        add(loadButton);
        add(historyButton);

        // CTRL+Z Keybindings for undo
        Object undoAction = new Object();
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((KeyEvent.VK_Z), InputEvent.CTRL_MASK),undoAction);
        getActionMap().put(undoAction, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                drawManager.undo();
            }
        });

    }
}
