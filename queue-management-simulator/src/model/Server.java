package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private final Object lock = new Object();
    private volatile Task currentTask = null;


    private AtomicInteger tasksFinished;
    private AtomicInteger tasksLength;
    ///for average
    private AtomicInteger waitingTime;
    private AtomicInteger serviceTime;


    public Server(){
        this.tasks = new ArrayBlockingQueue<>(200);
        this.waitingPeriod = new AtomicInteger(0);

        this.waitingTime = new AtomicInteger(0);
        this.serviceTime = new AtomicInteger(0);
        this.tasksFinished = new AtomicInteger(0);
        this.tasksLength = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        synchronized (lock) {
            try {
                tasks.put(newTask);
                waitingPeriod.addAndGet(newTask.getServiceTime());
                tasksLength.incrementAndGet();


            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void run(){
        while(true){

            try{
                Task task;
                task = tasks.take();
                currentTask = task;
                int serviceTime = task.getServiceTime();
                this.serviceTime.addAndGet(serviceTime);
                for (int i = 0; i < serviceTime; i++) {
                   // currentTask.setProcessingTime(currentTask.getProcessingTime() + 1);
                    if (!tasks.isEmpty()) {
                        waitingTime.addAndGet(tasks.size());
                    }
                    Thread.sleep(1000);
                    synchronized (lock) {
                        waitingPeriod.decrementAndGet();
                        currentTask.setProcessingTime(currentTask.getProcessingTime() + 1);
                    }
                }
                tasksFinished.incrementAndGet();
                tasksLength.decrementAndGet();
                currentTask = null;

            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


    public Task[] getTasks(){
        synchronized (lock) {
            return tasks.toArray(new Task[0]);
        }
    }

    public Task getCurrentTask(){
        return currentTask;
    }

    public synchronized int getWaitingPeriod(){
        return waitingPeriod.get();
    }

    public synchronized int getQueueSize(){
        synchronized (lock) {
            return tasks.size();
        }
    }

    public List<Task> getAllTasks(){
        synchronized (lock) {
            return new ArrayList<>(tasks);
        }
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public AtomicInteger getTasksFinished() {
        return tasksFinished;
    }

    public AtomicInteger getTasksLength() {
        return tasksLength;
    }

    public AtomicInteger getServiceTime() {
        return serviceTime;
    }
}
