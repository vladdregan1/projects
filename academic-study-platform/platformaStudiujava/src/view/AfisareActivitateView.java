package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;


public class AfisareActivitateView extends JFrame{

    private JButton backButton = new JButton("Inapoi");
    private JButton descarcaButton = new JButton("Descarca");
    private JList<String> activitateList;

    public AfisareActivitateView() {
        setTitle("Afisare Activitate");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        activitateList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(activitateList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(descarcaButton, BorderLayout.NORTH);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void setActivitateList(List<String[]> activitateDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String[] details : activitateDetails) {
            String displayText = details[0] + " - " + details[1] + " - " + details[2] + " - " + details[3] + " - " + details[4] + " - " + details[5] + " - " + details[6] + " - " + details[7];
            listModel.addElement(displayText);
        }

        activitateList.setModel(listModel);
    }


    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addDescarcaButtonListener(ActionListener listener) {
        descarcaButton.addActionListener(listener);
    }
}
