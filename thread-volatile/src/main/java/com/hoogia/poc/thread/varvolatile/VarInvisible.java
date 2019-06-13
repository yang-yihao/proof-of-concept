package com.hoogia.poc.thread.varvolatile;

/**
 * VarInvisible
 * <p>
 * Created On 2019-06-13
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class VarInvisible extends Thread {

    private boolean idle = false;

    @Override
    public void run() {
        while (true) {
            if (idle) {
                // would never come in even if changing the variable idle
                // to true in other thread.
                // use "volatile" when declare variable idle to solve the problem.
                System.out.println("Hey, I'm working now.");
            }
        }
    }

    public void setIsIdle(boolean isIdle) {
        this.idle = isIdle;
    }

    public boolean isIdle() {
        return this.idle;
    }
}
