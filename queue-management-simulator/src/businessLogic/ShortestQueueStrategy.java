package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class ShortestQueueStrategy implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task task) {
        Server bestServer = null;
        int minTotalLoad = Integer.MAX_VALUE;  // load = currentTask + queueSize

        for (Server server : servers) {


            int currentLoad = 0;

            if (server.getCurrentTask() != null) {
                currentLoad = currentLoad + 1;
            }
            currentLoad = currentLoad + server.getQueueSize();
            //System.out.println(currentLoad);

            if (currentLoad < minTotalLoad) {
                minTotalLoad = currentLoad;
                bestServer = server;
            }
        }

        if (bestServer != null) {
            bestServer.addTask(task);
        }
    }
}
