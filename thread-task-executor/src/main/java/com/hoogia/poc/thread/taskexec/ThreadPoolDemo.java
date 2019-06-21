package com.hoogia.poc.thread.taskexec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolDemo
 * <p>
 * Created On 2019-06-21
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class ThreadPoolDemo {

    private static class Task implements Runnable {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            doIt();
        }

        private void doIt() {
            System.out.printf("Thread[%s] executing job[%s]...%n",
                    Thread.currentThread().getId(), name);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建6个任务
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tasks.add(new Task("Task " + i));
        }
        // 创建一个有2个线程的线程池，用于执行以上6个任务
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (Task t : tasks) {
            exec.execute(t);
        }
        exec.shutdown();  // 如果不shutdown，exec里的线程还活着，程序就不会退出
    }
}
