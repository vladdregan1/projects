package businessLogic;

import gui.SimulationFrame;
import gui.SimulationGui;
import model.Server;
import model.Task;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationManager implements Runnable {
    public int timeLimit = 60;
    public int maxProcessingTime = 7;
    public int minProcessingTime = 1;
    public int numberOfServers = 5;
    public int numberOfClients = 50;
    public int maxArrivalTime = 40;
    public int minArrivalTime = 2;

    private double averageWaitingTime;
    private double averageServiceTime;
    private int peakHour;


    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    private final Object lock = new Object();

    private SimulationGui simulationGui;

    public SimulationManager(){
       /* scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);

        generatedTasks = new ArrayList<>();
        generateNRandomTasks();
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        frame = new SimulationFrame(); */
    }

    public void initialize(){
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);

        generatedTasks = new ArrayList<>();
        generateNRandomTasks();
        averageWaitingTime = 0.0;
        averageServiceTime = 0.0;
        peakHour = 0;
        generatedTasks.sort(Comparator.comparingInt(Task::getArrivalTime));
        frame = new SimulationFrame();
    }

    public void generateNRandomTasks(){
        Random rand  = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int processingTime = rand.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            //int arrivalTime = rand.nextInt(timeLimit);
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            generatedTasks.add(new Task(i, arrivalTime, processingTime));
        }

    }

    public int getServersLength()
    {
        int total = 0;
        for (Server server : scheduler.getServers()) {
            total = total + server.getTasksLength().get();
        }
        return total;
    }

    @Override
    public void run() {
        initialize();
        int currentTime = 0;
        int maxLength = 0;
        while (currentTime <= timeLimit) {

            synchronized (lock) {
                Iterator<Task> iterator = generatedTasks.iterator();
                while (iterator.hasNext()) {
                    Task task = iterator.next();
                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        iterator.remove();
                    }
                }
            }

            int serversLength;
            serversLength = getServersLength();
            if (serversLength > maxLength) {
                maxLength = serversLength;
                peakHour = currentTime;
            }
            frame.update(currentTime, scheduler.getServers(), new ArrayList<>(generatedTasks));
            simulationGui.update(scheduler.getServers(), new ArrayList<>(generatedTasks), currentTime);

            if (generatedTasks.isEmpty()) {
                int ok = 0;
                for (Server server : scheduler.getServers()) {
                    if (server.getQueueSize() != 0 || server.getCurrentTask() != null){
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    break;
                }
            }

            currentTime++;
            try{
                TimeUnit.MILLISECONDS.sleep(1025);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
        findAverageWaitingServiceTime();

        System.out.println("Average waiting time: " + averageWaitingTime + "\n" + "Average service time: " + averageServiceTime);
        System.out.println("Peak hour: " + peakHour);
        frame.displayFinalStats(averageWaitingTime, averageServiceTime, peakHour);
        frame.close();

    }

    public void findAverageWaitingServiceTime(){
        double totalNbFhTasks = 0;
        for (Server server : scheduler.getServers()){
            double doubleConverter;

            AtomicInteger serviceTime = server.getServiceTime();
            doubleConverter = (double) serviceTime.get();
            averageServiceTime = averageServiceTime + doubleConverter;


            AtomicInteger waitingTime = server.getWaitingTime();
            doubleConverter = (double) waitingTime.get();
            averageWaitingTime = averageWaitingTime + doubleConverter;

            totalNbFhTasks = totalNbFhTasks + (double)server.getTasksFinished().get() + (double)server.getTasksLength().get();
        }
        System.out.println("Total Finished Tasks: " + totalNbFhTasks);

            averageWaitingTime = averageWaitingTime / totalNbFhTasks;
            averageServiceTime = averageServiceTime / totalNbFhTasks;


       // averageWaitingTime = averageWaitingTime / totalNbFhTasks;
    }


    public Scheduler getScheduler() {
        return scheduler;
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public static void main(String[] args) {
        SimulationManager gen = new SimulationManager();
        Thread t = new Thread(gen);
        t.start();
    }

    public void setSimulationGui(SimulationGui simulationGui) {
        this.simulationGui = simulationGui;
    }
    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public double getAverageServiceTime() {
        return averageServiceTime;
    }

    public double getPeakHour() {
        return peakHour;
    }

}
