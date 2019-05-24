import DrawManager.DrawManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class HistoryDialog extends JDialog {
    private DrawManager drawManager;
    private Vector<String> listData;
    private JList historyList;

    public HistoryDialog(JFrame parent, DrawManager drawManager) {
        super(parent, "History", true);
        this.drawManager = drawManager;
        this.listData = drawManager.revealImageHistory();

        Container cp = getContentPane();

        cp.setLayout(new BorderLayout());



        this.historyList = new JList(listData);
        historyList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane listScroller = new JScrollPane(historyList);
        listScroller.setSize(new Dimension(10, 10));
        historyList.setSelectedIndex(listData.size()-1);

        JButton selectButton = new JButton("Select");

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawManager.newImageFromHistory(historyList.getSelectedIndex());
                dispose(); // Closes the dialog
            }
        });


        historyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                drawManager.previewImageFromHistory(historyList.getSelectedIndex());
            }
        });

        cp.add(listScroller, BorderLayout.CENTER);
        cp.add(selectButton, BorderLayout.SOUTH);
        setSize(170, 250);
    }


}