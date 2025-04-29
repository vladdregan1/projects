package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DupaLogareAdminView extends JFrame{

    private JPanel mainPanel = new JPanel(new GridLayout(4, 0));
    private JButton delogareButton = new JButton("Delogare");
    private JButton infoButton = new JButton("Modifica Informatii");

    public DupaLogareAdminView() {
        setTitle("Pagina Principala Administrator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(infoButton);
        mainPanel.add(delogareButton);


        setLocationRelativeTo(null);

        add(mainPanel);

    }

    public void addDelogareButtonListener(ActionListener listener) {
        delogareButton.addActionListener(listener);
    }

    public void addInfoButtonListener(ActionListener listener) {
        infoButton.addActionListener(listener);
    }
}
