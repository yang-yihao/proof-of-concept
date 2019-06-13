package com.hoogia.poc.thread.refescape;

import java.util.List;

/**
 * ThisRefEscape
 * <p>
 * Created On 2019-05-27
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class ThisRefEscape {

    public ThisRefEscape(List<EventListener> listeners)
            throws InterruptedException
    {
        System.out.printf("[Thread-%s]ThisRefEscape Constructing.%n",
                Thread.currentThread().getId());
        listeners.add(new EventListener() { // Escape Point
            @Override
            public void doSomething(String message) {
                System.out.printf("message: %s%n", message);
            }
        });
        System.out.printf("[Thread-%s]ThisRefEscape Add Listener Completed.%n",
                Thread.currentThread().getId());
        Thread.sleep(2000);
        System.out.printf(
                "[Thread-%s]ThisRefEscape Constructor Completed.%n",
                Thread.currentThread().getId());
    }

    public void catchYou(String message) {
        System.out.printf("[Thread-%s]%s%n",
                Thread.currentThread().getId(), message);
    }

}
