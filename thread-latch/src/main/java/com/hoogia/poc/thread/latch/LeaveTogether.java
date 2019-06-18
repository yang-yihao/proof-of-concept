package com.hoogia.poc.thread.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * LeaveTogether
 * <p>
 * Created On 2019-06-18
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class LeaveTogether {

    private static final int N = 3;
    private CountDownLatch latch = new CountDownLatch(N);
    private Random random = new Random();

    private class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s]I'm done.%n", Thread.currentThread().getId());
            latch.countDown();
        }

    }

    public void go() {
        for (int i = 0; i < N; i++)
            new Thread(new Task()).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Going into next step.");
    }
}
