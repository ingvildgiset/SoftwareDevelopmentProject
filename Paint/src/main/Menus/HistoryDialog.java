package Menus;

//imports
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

//dependencies
import DrawManager.DrawManager;

/**
 * Creates a dialog box to view the edit history. The commands listed are returned from the drawManager object
 * passed to the constructor. The commands are displayed in a scrollable JList object.
 * A preview of the image chosen from the history will be showed to the user, the image chosen when pressing
 * select button is the image user can continue to draw on.
 */
class HistoryDialog extends JDialog {

    HistoryDialog(JFrame parent, DrawManager drawManager) {
        super(parent, "History", true);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setSize(170, 250);

        //get command history from drawManager
        Vector<String> listData = drawManager.revealImageHistory();

        //create JList object with command history
        JList<String> historyList = new JList<>(listData);
        historyList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScroller = new JScrollPane(historyList);
        listScroller.setSize(new Dimension(10, 10));
        if(listData.size() > 0){
            //make sure to not set index = -1
            historyList.setSelectedIndex(listData.size()-1);
        }

        //create listSelectionListener
        historyList.addListSelectionListener(e -> drawManager.previewImageFromHistory(historyList.getSelectedIndex()));

        //create select button with actionListener
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(e -> {
            drawManager.newImageFromHistory(historyList.getSelectedIndex());
            dispose(); // Closes the dialog
        });

        //add to panel
        cp.add(listScroller, BorderLayout.CENTER);
        cp.add(selectButton, BorderLayout.SOUTH);
    }


}