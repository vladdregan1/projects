package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DupaLogareView extends JFrame {
    private JButton infoButton = new JButton("Informatii");
    private JButton delogareButton = new JButton("Delogare");
    protected JPanel mainPanel = new JPanel(new GridLayout(4, 0));


    public DupaLogareView() {
        setTitle("Pagina Principala");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(infoButton);
        mainPanel.add(delogareButton);

        setLocationRelativeTo(null);

        add(mainPanel);
    }

    public void addInfoButtonListener(ActionListener listener) {
        infoButton.addActionListener(listener);
    }


    public void addDelogareButtonListener(ActionListener listener) {
        delogareButton.addActionListener(listener);
    }

}
