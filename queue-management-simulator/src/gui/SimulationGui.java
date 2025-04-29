package gui;

import model.Server;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationGui extends JPanel {
    private List<Server> servers;
    private List<Task> waitingTasks;
    private int currentTime = 0;

    public SimulationGui(List<Server> servers, List<Task> waitingTasks) {
        this.servers = servers;
        this.waitingTasks = waitingTasks;


        int preferredWidth = Math.max(800, servers.size() * 150);
        setPreferredSize(new Dimension(preferredWidth, 600));
        //setPreferredSize(new Dimension(800, 600));

    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xOffset = 50;
        int yOffset = 100;

        g.drawString("Waiting Tasks:", 50, 50);
        int taskX = 150;
        for (Task task : waitingTasks) {
            g.setColor(Color.RED);
            g.fillPolygon(new int[] {taskX, taskX + 20, taskX - 20}, new int[] {70, 110, 110}, 3); ///triunghi
            taskX = taskX + 40;
        }

        int queueY = yOffset;
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            int queueX = xOffset + i * 120;


            g.setColor(Color.BLUE);
            g.fillRect(queueX, queueY, 60, 60); ///patrat
            g.setColor(Color.WHITE);
            g.drawString("Queue: " + i, queueX + 10, queueY + 30);

            ///taskul in curs de procesare
            Task currentTask = server.getCurrentTask();
            int nb1 = 0;
            if (currentTask != null) {
                nb1 = 1;
                int processingTaskY = queueY + 80;
                g.setColor(new Color(0, 100, 0));
                g.fillPolygon(new int[] {queueX + 30, queueX + 50, queueX + 10}, new int[] {processingTaskY, processingTaskY, processingTaskY + 20}, 3);
            }

            ///desenez taskurile pentru fiecare queue
            int taskY = queueY + 130;
            for (Task task : server.getAllTasks()) {
                g.setColor(Color.GREEN);
                g.fillPolygon(new int[] {queueX + 30, queueX + 50, queueX + 10}, new int[] {taskY, taskY, taskY + 20}, 3); ///triunghi
                taskY = taskY + 50;
            }
            int nbf = nb1 + server.getQueueSize();
            System.out.println(nbf);

        }
        g.setColor(Color.BLACK);
        g.drawString("Current Time: " + currentTime, getWidth() - 150, 20);
    }

    public void update(List<Server> servers, List<Task> waitingTasks, int currentTime) {
        this.servers = servers;
        this.waitingTasks = waitingTasks;
        this.currentTime = currentTime;
        System.out.println("Current Time: " + currentTime) ;
        repaint();
    }

}
