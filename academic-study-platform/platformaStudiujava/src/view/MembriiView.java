package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class MembriiView extends JFrame {

    private JButton backButton = new JButton( "Inapoi");
    private JList<String> memList;

    public MembriiView() {
        setTitle("Membrii");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        memList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(memList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void setMemList(List<String[]> memDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] memDetail : memDetails) {
            String displayText = memDetail[0] + " - " + memDetail[1];
            listModel.addElement(displayText);
        }
        memList.setModel(listModel);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
