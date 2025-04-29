package gui;

import businessLogic.SelectionPolicy;
import businessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupGui extends JFrame {

    private SimulationManager sim;

    private JTextField timeLimitField = new JTextField(5);
    private JTextField maxProcessingField = new JTextField(5);
    private JTextField minProcessingField = new JTextField(5);
    private JTextField numberOfServersField = new JTextField(5);
    private JTextField numberOfClientsField = new JTextField(5);
    private JTextField maxArrivalField = new JTextField(5);
    private JTextField minArrivalField = new JTextField(5);

    private JComboBox<SelectionPolicy> policyComboBox;

    public SetupGui(SimulationManager sim) {
        setTitle("Simulation Setup");
        this.sim = sim;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10,2,5,5));

        add(new JLabel("Time Limit:")); add(timeLimitField);
        add(new JLabel("Max Processing Time:")); add(maxProcessingField);
        add(new JLabel("Min Processing Time:")); add(minProcessingField);
        add(new JLabel("Number of Servers:")); add(numberOfServersField);
        add(new JLabel("Number of Clients:")); add(numberOfClientsField);
        add(new JLabel("Max Arrival Time:")); add(maxArrivalField);
        add(new JLabel("Min Arrival Time:")); add(minArrivalField);

        add(new JLabel("Policy:"));
        policyComboBox = new JComboBox<>(SelectionPolicy.values());
        add(policyComboBox);

        JButton saveButton = new JButton("Save");
        add(saveButton);
        JButton backButton = new JButton("Back");
        add(backButton);

        setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             try {
                 sim.timeLimit = Integer.parseInt(timeLimitField.getText());
                 sim.maxProcessingTime = Integer.parseInt(maxProcessingField.getText());
                 sim.minProcessingTime = Integer.parseInt(minProcessingField.getText());
                 sim.numberOfServers = Integer.parseInt(numberOfServersField.getText());
                 sim.numberOfClients = Integer.parseInt(numberOfClientsField.getText());
                 sim.maxArrivalTime = Integer.parseInt(maxArrivalField.getText());
                 sim.minArrivalTime = Integer.parseInt(minArrivalField.getText());

                 sim.selectionPolicy = (SelectionPolicy) policyComboBox.getSelectedItem();

                 System.out.println(sim.timeLimit + " " + sim.maxProcessingTime + " " + sim.minProcessingTime + " " + sim.numberOfServers + " " + sim.numberOfClients + " " + sim.maxArrivalTime + " " + sim.minArrivalTime);
                 System.out.println(sim.selectionPolicy);

                 JOptionPane.showMessageDialog(null, "Configurare salvata cu succes!");
             }catch(NumberFormatException nfe){
                 JOptionPane.showMessageDialog(null, "Introduceti doar numere intregi!");
                }
            }

        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGui(sim);
                dispose();
            }
        });

    }

}
