package gui;

import businessLogic.SimulationManager;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame {

    private SimulationManager simulationManager;

   public MainGui(SimulationManager simulationManager) {
       this.simulationManager = simulationManager;
       setTitle("Queue Management");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(800, 600);
       setLocationRelativeTo(null);
       setLayout(new BorderLayout());


       JPanel panel = new JPanel();
       panel.setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
       c.fill = GridBagConstraints.HORIZONTAL;

       JButton setupButton = new JButton("Setup");
       c.gridx = 0;
       c.gridy = 0;
       panel.add(setupButton, c);

       JButton startButton = new JButton("Start");
       c.gridx = 0;
       c.gridy = 1;
        panel.add(startButton, c);

        JButton averageWaitingTimeButton = new JButton("Average Waiting Time");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(averageWaitingTimeButton, c);

        JButton averageServiceTimeButton = new JButton("Average Service Time");
        c.gridx = 0;
        c.gridy = 3;
        panel.add(averageServiceTimeButton, c);

        JButton peakHourButton = new JButton("Peek Hour");
        c.gridx = 0;
        c.gridy = 4;
        panel.add(peakHourButton, c);

       add(panel, BorderLayout.CENTER);

       setVisible(true);

       setupButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new SetupGui(simulationManager);
               dispose();
           }
       });

       startButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               simulationManager.initialize();

               SimulationGui simulationGui = new SimulationGui(simulationManager.getScheduler().getServers(), simulationManager.getGeneratedTasks());
               simulationManager.setSimulationGui(simulationGui);

               JScrollPane scrollPane = new JScrollPane(simulationGui, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
               scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

               JFrame simulationDrawFrame = new JFrame("Simulation Draw");
               simulationDrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               simulationDrawFrame.add(scrollPane);
               simulationDrawFrame.pack();
               simulationDrawFrame.setVisible(true);

               System.out.println(simulationManager.timeLimit + " " + simulationManager.maxProcessingTime + " " + simulationManager.minProcessingTime + " " + simulationManager.numberOfServers + " " + simulationManager.numberOfClients + " " + simulationManager.maxArrivalTime + " " + simulationManager.minArrivalTime);
                Thread t = new Thread(simulationManager);
                t.start();
           }
       });

       averageWaitingTimeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double averageWaitingTime = simulationManager.getAverageWaitingTime();
               JOptionPane.showMessageDialog(null, "Average Waiting Time: " + averageWaitingTime);
           }
       });

       averageServiceTimeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double averageServiceTime = simulationManager.getAverageServiceTime();
               JOptionPane.showMessageDialog(null, "Average Service Time: " + averageServiceTime);
           }
       });

       peakHourButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double peakHour = simulationManager.getPeakHour();
               JOptionPane.showMessageDialog(null, "Peak Hour: " + peakHour);
           }
       });
   }

   public static void main(String[] args) {
       SimulationManager simulationManager = new SimulationManager();
       new MainGui(simulationManager);
   }

}
