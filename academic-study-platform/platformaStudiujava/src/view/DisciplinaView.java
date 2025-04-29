package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class DisciplinaView extends JFrame {

    private JButton backButton = new JButton("Inapoi");
    private JButton assignButton = new JButton("Asignare Disciplina");
    private JList<String> disciplinaList;

    public DisciplinaView() {
        setTitle("Lista Disciplina");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        disciplinaList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(disciplinaList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(assignButton, BorderLayout.NORTH);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        add(mainPanel);
    }

    public void setDisciplinaList(List<String[]> disciplinaDetails) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String[] details : disciplinaDetails) {
            String displayText = details[0] + " - " + details[1];
            listModel.addElement(displayText);
        }

        disciplinaList.setModel(listModel);
    }


    public String getSelectedDisciplina() {
        String selectedValue = disciplinaList.getSelectedValue();
        if (selectedValue != null) {
            return selectedValue.split(" - ")[0];
        }
        return null;
    }


    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addAssignButtonListener(ActionListener listener) {
        assignButton.addActionListener(listener);
    }
}
