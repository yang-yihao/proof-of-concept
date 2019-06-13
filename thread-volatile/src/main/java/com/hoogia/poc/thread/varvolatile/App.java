package com.hoogia.poc.thread.varvolatile;

/**
 * App
 * <p>
 * Created On 2019-06-13
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        VarInvisible vi = new VarInvisible();
        vi.start();
        new Thread(() -> {
            vi.setIsIdle(true);
            System.out.println("vi.idle=" + vi.isIdle());
        }).start();

        Thread.sleep(10000);
        System.out.println("10 seconds elapse, terminate myself.");
        System.exit(0);
    }

}
