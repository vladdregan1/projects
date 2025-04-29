package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class GrupView extends JFrame{

    private JButton backButton = new JButton("Inapoi");
    private JButton creareActivitateGrupButton = new JButton("Creare Activitate Grup");
    private JButton inscriereGrupButton = new JButton("Inscriere Activitate Grup");
    private JButton mesaje = new JButton("Mesaje");
    private JList<String> grupList;

    public GrupView() {
        setTitle("Grupuri");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout());

        grupList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(grupList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panou pentru butoane
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(backButton);
        buttonPanel.add(creareActivitateGrupButton);
        buttonPanel.add(inscriereGrupButton);
        buttonPanel.add(mesaje);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void setGrupList(List<String[]> grupListt) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] grup : grupListt) {
            String displayText = grup[0] + " - " + grup[1];
            listModel.addElement(displayText);
        }
        grupList.setModel(listModel);
    }

    public int getSelectedIdGrup() {
        String selectedValue = grupList.getSelectedValue();
        if (selectedValue != null) {
            return Integer.parseInt(selectedValue.split(" - ")[0]);
        }
        return -1;
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addCreareActivitateGrupButtonListener(ActionListener listener) {
        creareActivitateGrupButton.addActionListener(listener);
    }

    public void addInscriereGrupButtonListener(ActionListener listener) {
        inscriereGrupButton.addActionListener(listener);
    }

    public void addMesajeListener(ActionListener listener) {
        mesaje.addActionListener(listener);
    }
}

