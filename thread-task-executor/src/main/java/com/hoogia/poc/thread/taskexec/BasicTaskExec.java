package com.hoogia.poc.thread.taskexec;

/**
 * BasicDemo
 * <p>
 * Created On 2019-06-21
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class BasicTaskExec {

    // 任务与执行器（线程）耦合在一起
    private static class CoupledTaskExecutor extends Thread {

        @Override
        public void run() {
            // TODO 把线程要执行的任务写在这
            doSomeTask();
        }

        private void doSomeTask() {
            System.out.println("Doing work of CoupledTaskExecutor...");
        }
    }

    public static void main(String[] args) {
        new CoupledTaskExecutor().start();  // 启动线程，线程本身固化了（耦合）任务
    }
}
