package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CreareActivitateView extends JFrame{

    private JButton submitButton = new JButton("Aplica");
    private JButton backButton = new JButton("Inapoi");

    private JTextField disciplina = new JTextField(20);
    private JComboBox<String> tipActivitate;
    private JTextField dataInceput = new JTextField(20);
    private JTextField dataSfarsit = new JTextField(20);
    private JTextField frecventa = new JTextField(20);
    private JComboBox<Integer> procent;

    public CreareActivitateView() {
        setTitle("Creare Activitate");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        mainPanel.add(new JLabel("Disciplina:"));
        mainPanel.add(disciplina);

        mainPanel.add(new JLabel("Tip activitate:"));
        String[] tipuriActivitate = {"Seminar", "Laborator", "Curs"};
        tipActivitate = new JComboBox<>(tipuriActivitate);
        mainPanel.add(tipActivitate);

        mainPanel.add(new JLabel("Data inceput (yyyy-MM-dd HH:mm:ss):"));
        mainPanel.add(dataInceput);

        mainPanel.add(new JLabel("Data sfarsit (yyyy-MM-dd HH:mm:ss):"));
        mainPanel.add(dataSfarsit);

        mainPanel.add(new JLabel("Frecventa:"));
        mainPanel.add(frecventa);

        mainPanel.add(new JLabel("Procent:"));
        procent = new JComboBox<>();
        for (int i = 0 ; i <= 100; i++) {
            procent.addItem(i);
        }
        mainPanel.add(procent);

        mainPanel.add(submitButton);
        mainPanel.add(backButton);

        setLocationRelativeTo(null);

        add(mainPanel);

    }

    public String getDisciplina() {
        return disciplina.getText();
    }

    public String getTipActivitate() {
        return (String) tipActivitate.getSelectedItem();
    }

    public String getDataInceput() {
        return dataInceput.getText();
    }

    public String getDataSfarsit() {
        return dataSfarsit.getText();
    }

    public String getFrecventa() {
        return frecventa.getText();
    }

    public int getProcent() {
        return (int) procent.getSelectedItem();
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

}
