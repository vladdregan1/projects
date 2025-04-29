package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdaugaAdminView extends JFrame{

    private JPanel mainPanel = new JPanel(new GridLayout(4, 0));
    private JButton creareAdmin = new JButton("Creare");
    private JButton stergeAdmin = new JButton("Sterge");
    private JButton inapoiButton = new JButton("Inapoi");

    private JTextField username = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);

    public AdaugaAdminView() {
        setTitle("Creare/Stergere Administrator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(new JLabel("Nume utilizator:"));
        mainPanel.add(username);
        mainPanel.add(new JLabel("Parola:"));
        mainPanel.add(password);
        mainPanel.add(creareAdmin);
        mainPanel.add(stergeAdmin);
        mainPanel.add(inapoiButton);


        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public void addCreareAdminListener(ActionListener listener) {
        creareAdmin.addActionListener(listener);
    }

    public void addStergereAdminListener(ActionListener listener) {
        stergeAdmin.addActionListener(listener);
    }

    public void addInapoiButtonListener(ActionListener listener) {
        inapoiButton.addActionListener(listener);
    }


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
