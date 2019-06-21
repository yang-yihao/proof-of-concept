package com.hoogia.poc.thread.taskexec;

/**
 * DecoupledTaskExec
 * <p>
 * Created On 2019-06-21
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class DecoupledTaskExec {

    // 单独定义任务，不与线程类耦合
    private static class Task implements Runnable {

        @Override
        public void run() {
            doSomeTask();
        }

        private void doSomeTask() {
            System.out.println("Executing job...");
        }
    }

    public static void main(String[] args) {
        Task t = new Task();  // 单独创建任务
        new Thread(t).start();  // 创建一个线程来执行指定的任务
    }
}
