package Menus;

//imports
import javax.swing.*;
import java.awt.*;

//dependencies
import DrawManager.DrawManager;

/**
 * Creates a menu to choose pen and fill color. Can choose between 10 different colors in addition to a color chooser
 * to customize own color.
 */
public class ColorBar extends JPanel {

    public ColorBar(DrawManager drawManager) {
        //set layout for panel
        setLayout(new GridLayout(13,1));
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(50, 100));

        //create color buttons
        JButton yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);

        JButton greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);

        JButton blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        blueButton.setForeground(Color.WHITE);

        JButton redButton = new JButton();
        redButton.setBackground(Color.RED);

        JButton blackButton = new JButton();
        blackButton.setBackground(Color.BLACK);
        blackButton.setForeground(Color.WHITE);

        JButton magentaButton = new JButton();
        magentaButton.setBackground(Color.MAGENTA);

        JButton whiteButton = new JButton();
        whiteButton.setBackground(Color.WHITE);

        JButton pinkButton = new JButton();
        pinkButton.setBackground(Color.PINK);

        JButton cyanButton = new JButton();
        cyanButton.setBackground(Color.CYAN);

        Icon noFill = new ImageIcon(getClass().getResource("../images/nocolor.png"));
        JButton noFillButton = new JButton(noFill);

        // Button for color chooser
        Icon colorWheel = new ImageIcon(getClass().getResource("../images/wheel.png"));
        JButton colorButton = new JButton(colorWheel);
        colorButton.setBackground(Color.WHITE);

        // Create pen and fill color button
        JButton penColorButton = new JButton("1.");
        penColorButton.setBackground(null);
        JButton fillColorButton = new JButton("2.");
        fillColorButton.setBackground(null);

        // Selection of pen and fill color.
        JRadioButton penColor = new JRadioButton("Pen");
        penColor.setSelected(true);
        penColor.setBackground(null);
        penColor.setOpaque(true);
        JRadioButton fillColor = new JRadioButton("Fill");
        fillColor.setBackground(null);
        fillColor.setOpaque(true);

        //make sure only one of the button is chosen at the same time
        ButtonGroup group = new ButtonGroup();
        group.add(penColor);
        group.add(fillColor);

        //Add actionListeners for buttons
        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a different color", Color.BLACK);
            if (penColor.isSelected()){
                drawManager.setPenColor(newColor);
                penColor.setBackground(newColor);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(newColor);
                fillColor.setBackground(newColor);
            }
        });

        yellowButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.YELLOW);
                penColor.setBackground(Color.YELLOW);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.YELLOW);
                fillColor.setBackground(Color.YELLOW);
                fillColor.setForeground(Color.BLACK);
            }
        });

        blueButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.BLUE);
                penColor.setBackground(Color.BLUE);
                penColor.setForeground(Color.WHITE);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.BLUE);
                fillColor.setBackground(Color.BLUE);
                fillColor.setForeground(Color.WHITE);
            }
        });

        greenButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.GREEN);
                penColor.setBackground(Color.GREEN);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.GREEN);
                fillColor.setBackground(Color.GREEN);
                fillColor.setForeground(Color.BLACK);
            }
        });

        redButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.RED);
                penColor.setBackground(Color.RED);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.RED);
                fillColor.setBackground(Color.RED);
                fillColor.setForeground(Color.BLACK);
            }
        });

        blackButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.BLACK);
                penColor.setBackground(Color.BLACK);
                penColor.setForeground(Color.WHITE);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.BLACK);
                fillColor.setBackground(Color.BLACK);
                fillColor.setForeground(Color.WHITE);
            }
        });

        magentaButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.MAGENTA);
                penColor.setBackground(Color.MAGENTA);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.MAGENTA);
                fillColor.setBackground(Color.MAGENTA);
                fillColor.setForeground(Color.BLACK);
            }
        });

        whiteButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.WHITE);
                penColor.setBackground(Color.WHITE);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.WHITE);
                fillColor.setBackground(Color.WHITE);
                fillColor.setForeground(Color.BLACK);
            }
        });

        pinkButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.PINK);
                penColor.setBackground(Color.PINK);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.PINK);
                fillColor.setBackground(Color.PINK);
                fillColor.setForeground(Color.BLACK);
            }
        });

        cyanButton.addActionListener(e -> {
            if (penColor.isSelected()){
                drawManager.setPenColor(Color.CYAN);
                penColor.setBackground(Color.CYAN);
                penColor.setForeground(Color.BLACK);
            } else if (fillColor.isSelected()){
                drawManager.setFillColor(Color.CYAN);
                fillColor.setBackground(Color.CYAN);
                fillColor.setForeground(Color.BLACK);
            }
        });

        noFillButton.addActionListener(e -> {
            if (fillColor.isSelected()){
                drawManager.fillOff();
                fillColor.setBackground(null);
                fillColor.setForeground(Color.BLACK);
            }
        });

        //Adding color buttons to panel
        add(penColor);
        add(fillColor);
        add(noFillButton);
        add(blackButton);
        add(yellowButton);
        add(whiteButton);
        add(blueButton);
        add(greenButton);
        add(redButton);
        add(magentaButton);
        add(pinkButton);
        add(cyanButton);
        add(colorButton);
    }

}

