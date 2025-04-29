package businessLogic;

import model.Server;
import model.Task;

import java.util.List;

public interface Strategy {

    void addTask(List<Server> servers, Task task);
}

/*public enum SelectionPolicy{
    SHORTEST_QUEUE, SHORTEST_TIME
}*/