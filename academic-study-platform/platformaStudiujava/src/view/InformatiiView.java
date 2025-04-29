package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.InformatiiModel;

public class InformatiiView extends JFrame {

    private JLabel numeLabel = new JLabel("Nume: ");
    private JLabel prenumeLabel = new JLabel("Prenume: ");
    private JLabel CNPLabel = new JLabel("CNP: ");
    private JLabel adresaLabel = new JLabel("Adresa: ");
    private JLabel telefonLabel = new JLabel("Număr Telefon: ");
    private JLabel emailLabel = new JLabel("Email: ");
    private JLabel ibanLabel = new JLabel("IBAN: ");
    private JLabel contractLabel = new JLabel("Număr Contract: ");
    private JButton backButton = new JButton("Inapoi");

    public InformatiiView() {
        setTitle("Informatii");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        mainPanel.add(new JLabel("Nume:"));
        mainPanel.add(numeLabel);
        mainPanel.add(new JLabel("Prenume:"));
        mainPanel.add(prenumeLabel);
        mainPanel.add(new JLabel("CNP:"));
        mainPanel.add(CNPLabel);
        mainPanel.add(new JLabel("Adresa:"));
        mainPanel.add(adresaLabel);
        mainPanel.add(new JLabel("Număr Telefon:"));
        mainPanel.add(telefonLabel);
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailLabel);
        mainPanel.add(new JLabel("IBAN:"));
        mainPanel.add(ibanLabel);
        mainPanel.add(new JLabel("Număr Contract:"));
        mainPanel.add(contractLabel);
        mainPanel.add(backButton);

        setLocationRelativeTo(null);
        add(mainPanel);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void setInformatii(String nume, String prenume, String CNP, String adresa, String telefon, String email, String iban, String contract) {
        numeLabel.setText(nume);
        prenumeLabel.setText(prenume);
        CNPLabel.setText(CNP);
        adresaLabel.setText(adresa);
        telefonLabel.setText(telefon);
        emailLabel.setText(email);
        ibanLabel.setText(iban);
        contractLabel.setText(contract);
    }

}
