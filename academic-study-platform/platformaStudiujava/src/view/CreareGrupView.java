package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreareGrupView extends JFrame {

    private JPanel mainPanel = new JPanel(new GridLayout(8, 2, 5, 5));
    private JButton backButton = new JButton("Inapoi");
    private JTextField disciplina = new JTextField(20);
    private JTextField numeGrup = new JTextField(20);
    private JButton creareGrup = new JButton("Creare Grup");



    public CreareGrupView() {
        setTitle("Creare Grup");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(new JLabel("Disciplina:"));
        mainPanel.add(disciplina);
        mainPanel.add(new JLabel("Nume Grup:"));
        mainPanel.add(numeGrup);
        mainPanel.add(creareGrup);
        mainPanel.add(backButton);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addCreareGrupListener(ActionListener listener) {
        creareGrup.addActionListener(listener);
    }

    public String getDisciplina() {
        return disciplina.getText();
    }

    public String getNumeGrup() {
        return numeGrup.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
