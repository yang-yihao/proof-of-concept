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

    public static void main(String[] args) {
        VarInvisible vi = new VarInvisible();
        vi.start();
        new Thread(() -> {
            vi.setIsIdle(true);
            System.out.println("vi.idle=" + vi.isIdle());
        }).start();
    }

}
