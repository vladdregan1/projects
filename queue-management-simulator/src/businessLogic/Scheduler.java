package businessLogic;


import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;



public class Scheduler {

    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private final Object lock = new Object();


    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers = new ArrayList<>();

        for (int i = 0; i < maxNoServers; i++){
            this.servers.add(new Server());
            Thread serverThread = new Thread(servers.get(i));
            serverThread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
            if (policy == SelectionPolicy.SHORTEST_QUEUE) {
                strategy = new ShortestQueueStrategy();
            }

            if (policy == SelectionPolicy.SHORTEST_TIME) {
                strategy = new TimeStrategy();
            }


    }

    public void dispatchTask(Task t){
        synchronized (lock) {
            if (strategy == null) {
                throw new IllegalStateException("strategy not set");
            }
            strategy.addTask(servers, t);
        }

    }

    public synchronized List<Server> getServers(){
        //return servers;
        return new ArrayList<>(servers);
    }

}
