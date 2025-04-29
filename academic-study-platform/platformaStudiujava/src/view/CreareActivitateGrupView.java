package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.*;

public class CreareActivitateGrupView extends JFrame {

    private JButton backButton = new JButton("Inapoi");
    private JButton submitButton = new JButton("Submit");

    private JTextField nume = new JTextField(20);
    private JTextField dataCreare = new JTextField(20);
    private JTextField dataInceput = new JTextField(20);
    private JTextField dataFinal = new JTextField(20);
    private JTextField nrMinParticipanti = new JTextField(20);
    private JTextField dataValidarii = new JTextField(20);

    public CreareActivitateGrupView() {
        setTitle("Creare Activitate Grup");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel (new GridLayout(8,2,5,5));
        mainPanel.add(new JLabel("Nume:"));
        mainPanel.add(nume);
        mainPanel.add(new JLabel("Data Creare YYYY-MM-DD HH:mm:ss:"));
        mainPanel.add(dataCreare);
        mainPanel.add(new JLabel("Data Inceput YYYY-MM-DD HH:mm:ss:"));
        mainPanel.add(dataInceput);
        mainPanel.add(new JLabel("Data Final YYYY-MM-DD HH:mm:ss:"));
        mainPanel.add(dataFinal);
        mainPanel.add(new JLabel("Nr Min Participanti:"));
        mainPanel.add(nrMinParticipanti);
        mainPanel.add(new JLabel("Data Validarii YYYY-MM-DD HH:mm:ss:"));
        mainPanel.add(dataValidarii);
        mainPanel.add(submitButton);
        mainPanel.add(backButton);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public String getNume() {
        return nume.getText();
    }

    public String getDataCreare() {
        return dataCreare.getText();
    }

    public String getDataInceput() {
        return dataInceput.getText();
    }

    public String getDataFinal() {
        return dataFinal.getText();
    }

    public int getNrMinParticipanti() {
        return Integer.parseInt(nrMinParticipanti.getText());
    }

    public String getDataValidarii() {
        return dataValidarii.getText();
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
