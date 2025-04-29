package view;

import controller.LogareController;
import controller.UtilizatorController;
import model.LogareModel;
import model.UtilizatorModel;
import view.UtilizatorView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends JFrame {

    private JButton inregistrareButton = new JButton("Inregistrare");
    private JButton logareButton = new JButton("Logare");

    public MainView()
    {
        setTitle("PLATFORMA DE STUDIU");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(inregistrareButton);
        add(logareButton);

        setLocationRelativeTo(null);

        inregistrareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UtilizatorModel utilizatorModel = new UtilizatorModel();
                UtilizatorView utilizatorView = new UtilizatorView();
                UtilizatorController utilizatorController = new UtilizatorController(utilizatorModel, utilizatorView, MainView.this);

                utilizatorView.setVisible(true);
                setVisible(false);
            }
        });

        logareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogareView logareView = new LogareView();
                LogareModel logareModel = new LogareModel();
                LogareController logareController = new LogareController(logareView, logareModel, MainView.this);
                logareView.setVisible(true);
                setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }

}
