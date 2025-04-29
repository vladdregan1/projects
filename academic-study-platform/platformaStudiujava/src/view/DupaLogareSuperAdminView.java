package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DupaLogareSuperAdminView extends JFrame{

    private JPanel mainPanel = new JPanel(new GridLayout(4, 0));
    private JButton delogareButton = new JButton("Delogare");
    private JButton adaugaAdmin = new JButton("Adauga/Sterge Administrator");


    public DupaLogareSuperAdminView() {
        setTitle("Pagina Principala Super Administrator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(adaugaAdmin);
        mainPanel.add(delogareButton);


        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void addDelogareButtonListener(ActionListener listener) {
        delogareButton.addActionListener(listener);
    }

    public void addAdaugaAdminListener(ActionListener listener) {
        adaugaAdmin.addActionListener(listener);
    }
}
