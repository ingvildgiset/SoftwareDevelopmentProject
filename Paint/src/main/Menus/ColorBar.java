package Menus;

import DrawManager.DrawManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorBar extends JPanel {
    private DrawManager drawManager;


    public ColorBar(DrawManager drawManager) {
        this.drawManager = drawManager;

        setLayout(new GridLayout(13,1));
        setBackground(Color.lightGray);

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

        // The selected color buttons
        JButton penColorButton = new JButton("1.");
        penColorButton.setBackground(null);
        JButton fillColorButton = new JButton("2.");
        fillColorButton.setBackground(null);


        JRadioButton penColor = new JRadioButton("Pen");
        penColor.setSelected(true);
        penColor.setBackground(null);
        penColor.setOpaque(true);
        JRadioButton fillColor = new JRadioButton("Fill");
        fillColor.setBackground(null);
        fillColor.setOpaque(true);

        ButtonGroup group = new ButtonGroup();
        group.add(penColor);
        group.add(fillColor);

        Icon noFill = new ImageIcon(getClass().getResource("../images/nocolor.png"));
        JButton noFillButton = new JButton(noFill);


// Button for color chooser
        Icon colorwheel = new ImageIcon(getClass().getResource("../images/wheel.png"));
        JButton colorButton = new JButton(colorwheel);
        colorButton.setBackground(Color.WHITE);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("color chooser skal komme her");
                Color newColor = JColorChooser.showDialog(null, "Choose a different color", Color.BLACK);
                if (penColor.isSelected()){
                    drawManager.setPenColor(newColor);
                    penColor.setBackground(newColor);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(newColor);
                    fillColor.setBackground(newColor);
                }
            }
        });

        //ActionListeners for buttons
        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.YELLOW);
                    penColor.setBackground(Color.YELLOW);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.YELLOW);
                    fillColor.setBackground(Color.YELLOW);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.BLUE);
                    penColor.setBackground(Color.BLUE);
                    penColor.setForeground(Color.WHITE);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.BLUE);
                    fillColor.setBackground(Color.BLUE);
                    fillColor.setForeground(Color.WHITE);
                }
            }
        });
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.GREEN);
                    penColor.setBackground(Color.GREEN);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.GREEN);
                    fillColor.setBackground(Color.GREEN);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.RED);
                    penColor.setBackground(Color.RED);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.RED);
                    fillColor.setBackground(Color.RED);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.BLACK);
                    penColor.setBackground(Color.BLACK);
                    penColor.setForeground(Color.WHITE);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.BLACK);
                    fillColor.setBackground(Color.BLACK);
                    fillColor.setForeground(Color.WHITE);
                }
            }
        });

        magentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.MAGENTA);
                    penColor.setBackground(Color.MAGENTA);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.MAGENTA);
                    fillColor.setBackground(Color.MAGENTA);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        whiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.WHITE);
                    penColor.setBackground(Color.WHITE);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.WHITE);
                    fillColor.setBackground(Color.WHITE);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.PINK);
                    penColor.setBackground(Color.PINK);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.PINK);
                    fillColor.setBackground(Color.PINK);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        cyanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (penColor.isSelected()){
                    drawManager.setPenColor(Color.CYAN);
                    penColor.setBackground(Color.CYAN);
                    penColor.setForeground(Color.BLACK);
                } else if (fillColor.isSelected()){
                    drawManager.setFillColor(Color.CYAN);
                    fillColor.setBackground(Color.CYAN);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

        noFillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fillColor.isSelected()){
                    drawManager.fillOff();
                    fillColor.setBackground(null);
                    fillColor.setForeground(Color.BLACK);
                }
            }
        });

// Adding color buttons to panel
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

