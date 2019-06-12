package com.hoogia.poc.thread;

import java.util.List;

/**
 * NoRefEscape
 * <p>
 * Created On 2019-06-11
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class NoRefEscape {

    private EventListener eventListener;

    public static NoRefEscape getInstance(List<EventListener> listeners)
            throws InterruptedException
    {
        NoRefEscape noRefEscape = new NoRefEscape();
        listeners.add(noRefEscape.eventListener);
        return noRefEscape;
    }

    private NoRefEscape() throws InterruptedException {
        eventListener  = new EventListener() {
            @Override
            public void doSomething(String message) {
                System.out.printf("message: %s%n", message);
            }
        };
        Thread.sleep(2000);
    }

    public void catchYou(String message) {
        System.out.printf("[Thread-%s]%s%n", Thread.currentThread().getId(), message);
    }
}
