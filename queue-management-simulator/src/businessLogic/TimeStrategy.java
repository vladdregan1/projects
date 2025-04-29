package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TimeStrategy implements Strategy {


    @Override
    public void addTask(List<Server> servers, Task task) {
        Server bestServer = null;
        int minWaitingTime = Integer.MAX_VALUE;

        List<Server> serversCpy = new ArrayList<>(servers);
        for (Server server : serversCpy) {
            int currentWaitingTime = server.getWaitingPeriod();
            if (currentWaitingTime < minWaitingTime) {
                minWaitingTime = currentWaitingTime;
                bestServer = server;
            }
        }
         if (bestServer != null) {
             bestServer.addTask(task);
         }
    }
}
