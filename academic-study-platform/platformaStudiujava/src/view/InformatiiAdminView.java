package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InformatiiAdminView extends JFrame{

    private JTextField numeField = new JTextField(20);

    private JTextField cnpField = new JTextField(20);
    private JTextField numeInfoField = new JTextField(20);
    private JTextField prenumeInfoField = new JTextField(20);
    private JTextField adresaField = new JTextField(20);
    private JTextField nrTelefonField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField ibanField = new JTextField(20);
    private JTextField nrContract = new JTextField(20);


    private JButton modificareContButton = new JButton("Modifica Informatii");
    private JButton backButton = new JButton("Inapoi");




    public InformatiiAdminView() {
        setTitle("Modifica Informatii");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Panoul principal pentru câmpurile de bază
        JPanel mainPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        mainPanel.add(new JLabel("Nume Utilizator:"));
        mainPanel.add(numeField);
        mainPanel.add(new JLabel("Nume:"));
        mainPanel.add(numeInfoField);
        mainPanel.add(new JLabel("Prenume:"));
        mainPanel.add(prenumeInfoField);
        mainPanel.add(new JLabel("CNP:"));
        mainPanel.add(cnpField);
        mainPanel.add(new JLabel("Adresa:"));
        mainPanel.add(adresaField);
        mainPanel.add(new JLabel("Nr Telefon:"));
        mainPanel.add(nrTelefonField);
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailField);
        mainPanel.add(new JLabel("IBAN:"));
        mainPanel.add(ibanField);
        mainPanel.add(new JLabel("Nr Contract:"));
        mainPanel.add(nrContract);




        // Butoane
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(modificareContButton);
        buttonPanel.add(backButton);

        // Adăugare panouri în fereastra principală
        add(mainPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);



        setLocationRelativeTo(null);

    }

    // Metode getter pentru date
    public String getNumeUtilizator() {
        return numeField.getText();
    }


    public String getNumeInfo() {
        return numeInfoField.getText();
    }

    public String getPrenumeInfo() {
        return prenumeInfoField.getText();
    }

    public String getCNP() {
        return cnpField.getText();
    }

    public String getAdresa() {
        return adresaField.getText();
    }

    public String getNrTelefon() {
        return nrTelefonField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getIban() {
        return ibanField.getText();
    }

    public String getNrContract() {
        return nrContract.getText();
    }



    public void addModificareContListener(ActionListener listener) {
        modificareContButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
