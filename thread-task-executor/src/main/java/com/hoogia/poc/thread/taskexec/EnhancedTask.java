package com.hoogia.poc.thread.taskexec;

import java.util.concurrent.*;

/**
 * EnhancedTask
 * <p>
 * Created On 2019-06-21
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class EnhancedTask {

    private static class Task implements Callable<String> {

        private String user;
        private boolean throwException = false;

        public Task(String user, boolean throwException) {
            this.user = user;
            this.throwException = throwException;
        }

        @Override  // 相当于Runnable的run()，但是多了返回值和异常声明
        public String call() throws Exception {
            if (throwException)
                throw new RuntimeException(
                        user + ", exception throws from Callable.") ;
            return fetchSomething();
        }

        public String fetchSomething() {
            return "Hi " + user + ", this message returns from a callable task.";
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        Task normalTask = new Task("yihao", false);
        Task exceptionTask = new Task("coffee", true);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        // 即使任务执行过程有异常，这里也不会接收到
        Future<String> normalFuture = exec.submit(normalTask);
        Future<String> exceptionFuture = exec.submit(exceptionTask);
        exec.shutdown();
        try {
            // 如果Callable任务执行有异常，会在get()结果时抛出，而不会在任务执行时抛出。
            String result = normalFuture.get();
            System.out.println("Normal Task Result: " + result);
            exceptionFuture.get();  // 此处会抛出ExecutionException
        } catch (ExecutionException e) {
            System.out.printf(
                    "Exception Task Result: %s%n",e.getCause().getMessage());
        }
    }
}
