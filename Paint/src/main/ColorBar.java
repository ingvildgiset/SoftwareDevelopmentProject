import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorBar extends JPanel {

    public ColorBar() {

        setLayout(new GridLayout(5, 2, 2, 2));
        JButton yellowButton = new JButton("Yellow");
        yellowButton.setBackground(Color.YELLOW);

        JButton greenButton = new JButton("green");
        greenButton.setBackground(Color.GREEN);

        JButton blueButton = new JButton("blue");
        blueButton.setBackground(Color.BLUE);
        blueButton.setForeground(Color.WHITE);
        blueButton.setOpaque(true); //setter knappen gjennomsiktig
        blueButton.setBorderPainted(false);

        JButton redButton = new JButton("red");
        redButton.setBackground(Color.RED);
        redButton.setOpaque(true);
        redButton.setBorderPainted(false);

        JButton blackButton = new JButton("black");
        blackButton.setBackground(Color.BLACK);
        blackButton.setPreferredSize(new Dimension(40, 40));
        blackButton.setForeground(Color.WHITE);

        JButton magentaButton = new JButton("magenta");
        magentaButton.setBackground(Color.MAGENTA);
        magentaButton.setOpaque(true);
        magentaButton.setBorderPainted(false);

        JButton whiteButton = new JButton("white");
        whiteButton.setBackground(Color.WHITE);
        whiteButton.setOpaque(true);
        whiteButton.setBorderPainted(false);


// Button for color chooser
        JButton colorButton = new JButton("Choose color");
        colorButton.setBackground(Color.lightGray);
        colorButton.setOpaque(true);
        colorButton.setBorderPainted(false);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("color chooser skal komme her");
                Color newColor = JColorChooser.showDialog(null, "Choose a different color", Color.BLACK);
                // Over her, skal null være this??
                // Asigns the chosen color to colorButton
                colorButton.setBackground(newColor);
            }
        });


        // The selected color buttons
        JButton firstColorButton = new JButton("Color 1");
        firstColorButton.setBackground(null);
        firstColorButton.setOpaque(true);
        firstColorButton.setBorderPainted(false);

        firstColorButton.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("Outline color");
        Font font = new Font(null, Font.BOLD, 12);
        label1.setFont(font);
        firstColorButton.add(BorderLayout.NORTH, label1);


        JButton secondColorButton = new JButton();
        secondColorButton.setBackground(null);
        secondColorButton.setOpaque(true);
        secondColorButton.setBorderPainted(false);
        secondColorButton.setLayout(new BorderLayout());
        JLabel label2 = new JLabel("Filling color");
        label2.setFont(font);
        secondColorButton.add(BorderLayout.NORTH, label2);


        //ActionListeners for buttons
        yellowButton.addActionListener(e -> firstColorButton.setBackground(Color.YELLOW));
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstColorButton.setBackground(Color.BLUE);
                System.out.println("update");
            }
        });
        greenButton.addActionListener(e -> firstColorButton.setBackground(Color.GREEN));
        redButton.addActionListener(e -> firstColorButton.setBackground(Color.RED));
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstColorButton.setBackground(Color.BLACK);
                firstColorButton.setForeground(Color.WHITE);
            }
        });
        magentaButton.addActionListener(e -> firstColorButton.setBackground(Color.MAGENTA));
        whiteButton.addActionListener(e -> firstColorButton.setBackground(Color.WHITE));

// Adding color buttons to panel
        add(blackButton);
        add(yellowButton);
        add(whiteButton);
        add(blueButton);
        add(greenButton);
        add(redButton);
        add(magentaButton);
        add(colorButton);
        add(firstColorButton);
        add(secondColorButton);

    }

}

