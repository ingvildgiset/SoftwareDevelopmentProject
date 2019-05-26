//imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//dependencies
import DrawManager.*;
import Menus.*;


public class Paint {
    private Paint() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
        }

        JFrame frame = new JFrame("Design Tool");
        frame.setSize(750, 900);


        //create all panels
        JPanel fixedPanel = new JPanel(new GridBagLayout());
        fixedPanel.setBackground(Color.lightGray);
        fixedPanel.setSize(frame.getSize());
        DrawManager drawManager = new DrawManager(fixedPanel);
        ToolBar shapeMenu = new ToolBar(drawManager, frame);
        ColorBar colorMenu = new ColorBar(drawManager);
        fixedPanel.add(drawManager);

        //set layout
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(colorMenu, BorderLayout.WEST);
        container.add(fixedPanel, BorderLayout.CENTER);
        container.add(shapeMenu, BorderLayout.NORTH);

        //set features for frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!drawManager.imageEdited()){
                    frame.dispose();
                } else {
                    int confirmed = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to exit?",
                            "User Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmed == JOptionPane.YES_OPTION)
                        frame.dispose();
                }
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Paint myPaint = new Paint();
    }

}







