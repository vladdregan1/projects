package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UtilizatorView extends JFrame {

    ///UTILIZATOR
    private JTextField numeField = new JTextField(20);
    private JPasswordField parolaField = new JPasswordField(20);
    private JComboBox<String> rolComboBox = new JComboBox<>(new String[]{"Student", "Profesor"});

    ///UTILIZATOR_INFO
    private JTextField cnpField = new JTextField(20);
    private JTextField numeInfoField = new JTextField(20);
    private JTextField prenumeInfoField = new JTextField(20);
    private JTextField adresaField = new JTextField(20);
    private JTextField nrTelefonField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField ibanField = new JTextField(20);
    private JTextField nrContract = new JTextField(20);

    ///BUTOANE
    private JButton creareContButton = new JButton("Creeaza Cont");
    private JButton backButton = new JButton("Inapoi");

    ///PROFESOR
    private JTextField idDepartamentField = new JTextField(20);
    private JTextField nrMinOreField = new JTextField(20);
    private JTextField nrMaxOreField = new JTextField(20);

    ///STUDENT
    private JTextField anStudiuField = new JTextField(20);
    private JTextField numarOreField = new JTextField(20);

    private JPanel rolSpecificPanel; // Panou pentru profesor/student
    private CardLayout cardLayout;  // Layout pentru comutare între profesor/student

    public UtilizatorView() {
        setTitle("Creare Cont");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panoul principal pentru câmpurile de bază
        JPanel mainPanel = new JPanel(new GridLayout(11, 2, 5, 5));
        mainPanel.add(new JLabel("Nume Utilizator:"));
        mainPanel.add(numeField);
        mainPanel.add(new JLabel("Parola:"));
        mainPanel.add(parolaField);
        mainPanel.add(new JLabel("Grad didactic:"));
        mainPanel.add(rolComboBox);
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

        // Panoul pentru câmpurile specifice
        rolSpecificPanel = new JPanel();
        cardLayout = new CardLayout();
        rolSpecificPanel.setLayout(cardLayout);

        // Panou pentru profesor
        JPanel profesorPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        profesorPanel.add(new JLabel("Departament:"));
        profesorPanel.add(idDepartamentField);
        profesorPanel.add(new JLabel("Nr Minim Ore:"));
        profesorPanel.add(nrMinOreField);
        profesorPanel.add(new JLabel("Nr Maxim Ore:"));
        profesorPanel.add(nrMaxOreField);

        // Panou pentru student
        JPanel studentPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        studentPanel.add(new JLabel("An Studiu:"));
        studentPanel.add(anStudiuField);
        studentPanel.add(new JLabel("Numar Ore:"));
        studentPanel.add(numarOreField);

        // Adaugăm panourile în CardLayout
        rolSpecificPanel.add(studentPanel, "Student");
        rolSpecificPanel.add(profesorPanel, "Profesor");

        // Butoane
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(creareContButton);
        buttonPanel.add(backButton);

        // Adăugare panouri în fereastra principală
        add(mainPanel, BorderLayout.NORTH);
        add(rolSpecificPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurare listener pentru combo box
        rolComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) rolComboBox.getSelectedItem();
                if ("Profesor".equals(selectedRole)) {
                    cardLayout.show(rolSpecificPanel, "Profesor");
                } else {
                    cardLayout.show(rolSpecificPanel, "Student");
                }
            }
        });

        setLocationRelativeTo(null); // Centrează fereastra
        cardLayout.show(rolSpecificPanel, "Student"); // Setare implicită pe Student
    }

    // Metode getter pentru date
    public String getNumeUtilizator() {
        return numeField.getText();
    }

    public String getParola() {
        return new String(parolaField.getPassword());
    }

    public int getRol() {
        return rolComboBox.getSelectedIndex() + 1;
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

    public String getIdDepartament() {
        return idDepartamentField.getText();
    }

    public int getNrMinOre() {
        return Integer.parseInt(nrMinOreField.getText());
    }

    public int getNrMaxOre() {
        return Integer.parseInt(nrMaxOreField.getText());
    }

    public int getAnStudiu() {
        return Integer.parseInt(anStudiuField.getText());
    }

    public int getNumarOre() {
        return Integer.parseInt(numarOreField.getText());
    }

    public void addCreareContListener(ActionListener listener) {
        creareContButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
