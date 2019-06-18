package com.hoogia.poc.thread.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * NotFashion
 * <p>
 * Created On 2019-06-18
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class NotFashion {

    private static class Task implements Runnable {

        @Override
        public void run() {
            long tId = Thread.currentThread().getId();
            System.out.printf("[%s]Start working...%n", tId);
            try {
                // execute the task here.
                System.out.printf("[%s]Work Done.%n", tId);
                synchronized (this) {
                    wait();  // wait for the latch to open and finish.
                }
                System.out.printf("[%s]Thread Exiting.%n", tId);
            } catch (InterruptedException e) {

            }
        }
    }

    public void go() {
        // start threads
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        tasks.add(new Task());
        for (Task t : tasks) {
            new Thread(t).start();
        }
        // wait for something happens.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Task t : tasks) {
            synchronized (t) { t.notifyAll(); }
        }
    }
}
