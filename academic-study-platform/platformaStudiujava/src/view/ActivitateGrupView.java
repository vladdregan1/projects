package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class ActivitateGrupView extends JFrame{

    private JButton backButton = new JButton("Inapoi");
    private JButton inscriereButton = new JButton("Inscriere");
    private JButton membrii = new JButton("Membrii");

    private JList<String> activitateList;

    public ActivitateGrupView() {
        setTitle("Activitate Grup");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());
        activitateList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(activitateList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(inscriereButton);
        buttonPanel.add(membrii);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

    public int getSelectedIdActivitate() {
        String selectedValue = activitateList.getSelectedValue();
        if (selectedValue != null) {
            return Integer.parseInt(selectedValue.split(" - ")[0]);
        }
        return -1;
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addInscriereButtonListener(ActionListener listener) {
        inscriereButton.addActionListener(listener);
    }

    public void addMembriiListener(ActionListener listener) {
        membrii.addActionListener(listener);
    }
}
