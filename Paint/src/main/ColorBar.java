import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorBar extends JPanel {

    public ColorBar() {
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


// Button for color chooser
        Icon colorwheel = new ImageIcon(getClass().getResource("images/wheel.png"));
        JButton colorButton = new JButton(colorwheel);
        colorButton.setBackground(Color.WHITE);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("color chooser skal komme her");
                Color newColor = JColorChooser.showDialog(null, "Choose a different color", Color.BLACK);
                // Over her, skal null være this??
                // Asigns the chosen color to colorButton
            }
        });


        // The selected color buttons
        JButton firstColorButton = new JButton("1.");
        firstColorButton.setBackground(null);

        JButton secondColorButton = new JButton("2.");
        secondColorButton.setBackground(null);


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
        add(firstColorButton);
        add(secondColorButton);
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

