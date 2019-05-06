package com.codebind;

import javax.swing.*;
import java.awt.event.*;

public class App{

    private JPanel mainPanel;
    private JButton button1;
    private JToolBar toolBar;
    private JCheckBox lineCheckBox;
    private JCheckBox squareCheckBox;
    private JCheckBox circleCheckBox;
    private JPanel drawArea;


    public App() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello world");
            }
        });

        toolBar.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        lineCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Du har valgt firkant");
            }
        });

        drawArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                double x1 = e.getX();
                double y1 = e.getY();
                System.out.println(x1);
                System.out.println("press");
                System.out.println(y1);
                super.mouseDragged(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                double x2 = e.getX();
                double y2 = e.getY();
                System.out.println(x2);
                System.out.println("slipp");
                System.out.println(y2);
                super.mouseReleased(e);
            }
        });




        squareCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        circleCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        drawArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double x2 = e.getX();
                double y2 = e.getY();
                //vil få linja til å følge etter musa
                super.mouseDragged(e);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("App"); //Title on the frame
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true); //makes the new frame visible
    }
}
