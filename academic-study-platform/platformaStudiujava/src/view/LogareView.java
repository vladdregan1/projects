package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogareView extends JFrame {

    private JTextField numeUtilizatorField = new JTextField(20);
    private JPasswordField parolaField = new JPasswordField(20);

    private JButton logareButton = new JButton("Logare");
    private JButton backButton = new JButton("Inapoi");

    public LogareView() {
        setTitle("Logare Utilizator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(new JLabel("Nume Utilizator:"));
        mainPanel.add(numeUtilizatorField);
        mainPanel.add(new JLabel("Parola:"));
        mainPanel.add(parolaField);
        mainPanel.add(logareButton);
        mainPanel.add(backButton);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public String getNumeUtilizator() {
        return numeUtilizatorField.getText();
    }

    public String getParola() {
        return new String(parolaField.getPassword());
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addLogareButtonListener(ActionListener listener) {
        logareButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


}
