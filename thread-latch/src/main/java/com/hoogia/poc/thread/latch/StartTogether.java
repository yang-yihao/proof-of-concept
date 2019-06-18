package com.hoogia.poc.thread.latch;

import java.util.concurrent.CountDownLatch;

/**
 * StartTogether
 * <p>多个线程先后启动，但一起执行</p>
 * Created On 2019-06-18
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class StartTogether {

    private CountDownLatch door = new CountDownLatch(1);

    private class Task implements Runnable {

        @Override
        public void run() {
            long tId = Thread.currentThread().getId();
            System.out.printf("I'm [%s]. Start running!%n", tId);
            try {
                door.await();  // wait for the door to open.
                System.out.printf("I'm [%s]. Start working!%n", tId);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void go() {
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start(); // threads start running and wait for judge to start working.
        }
        try {
            Thread.sleep(3000);
            System.out.println("About 3 seconds elapsed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        door.countDown();  // open the door, all threads wait out of this door now enters and start working.
    }

}
