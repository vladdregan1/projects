package gui;

import model.Server;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SimulationFrame {

    private PrintWriter writer;

    public SimulationFrame() {
        try{
            writer = new PrintWriter(new FileWriter("test2Time2.txt"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void update(int currentTime, List<Server> servers, List<Task> waitingTasks) {
        writer.println("Time: " + currentTime);
        writer.print("Waiting tasks: ");
        for (Task task : waitingTasks) {
            writer.print("(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ")");
        }
        writer.println();
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            Task current = server.getCurrentTask();
            List<Task> queueTasks = server.getAllTasks();
            writer.print("Queue: " + i + " clients: ");

            ///afiseaza cel curent
            if (current != null){

                int processTime = current.getServiceTime() - current.getProcessingTime();
                if (processTime > 0) {
                    writer.print("(" + current.getID() + "," + current.getArrivalTime() + "," + processTime + ")");
                    //current.setProcessingTime(current.getProcessingTime() + 1);
                }else {
                    current = null;
                }
            }
            else {
                writer.print("closed");
            }
            ///afiseaza restul taskurilor care asteapta
            for (Task task : queueTasks) {
                if (current == null || task.getID() != current.getID()) {
                    int remainingTime = task.getServiceTime() - task.getProcessingTime();
                    writer.print("(" + task.getID() + "," + task.getArrivalTime() + "," + remainingTime + ")");
                }
            }


            writer.println();

        }
        writer.println("-------------------------");
        writer.flush();
    }

    public void displayFinalStats(double avgWait, double avgService, int peakHour) {
        writer.println("==== Final Statistics ====");
        writer.println("Average Waiting Time: " + avgWait);
        writer.println("Average Service Time: " + avgService);
        writer.println("Peak Hour: " + peakHour);
        writer.flush();
    }


    public void close() {
        if (writer != null)
            writer.close();
    }
}
