import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class ColorBar extends Applet {

    public ColorBar() {


        setLayout(new GridLayout(4,3));
        JButton yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);;
        yellowButton.setOpaque(true); //setter knappen gjennomsiktig
        yellowButton.setBorderPainted(false);


        JButton greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);;
        greenButton.setOpaque(true); //setter knappen gjennomsiktig
        greenButton.setBorderPainted(false);

        JButton blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);;
        blueButton.setOpaque(true); //setter knappen gjennomsiktig
        blueButton.setBorderPainted(false);

        JButton redButton = new JButton();
        JButton blackButton = new JButton();


        yellowButton.addActionListener(e -> System.out.println("update shape"));
        blueButton.addActionListener(e -> System.out.println("update"));
        greenButton.addActionListener(e -> System.out.println("update"));
        redButton.addActionListener(e -> System.out.println("update"));

        add(yellowButton);
        add(blueButton);
        add(greenButton);
        add(redButton);
        add(blackButton);



    }

}

