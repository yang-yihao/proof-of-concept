package com.hoogia.poc.thread;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class App 
{
    public static void main( String[] args )
            throws IllegalAccessException, InterruptedException
    {
        final List<EventListener> listeners = new ArrayList<>();
        Runnable task = () -> {
            try {
                ThisRefEscape thisRefEscape = new ThisRefEscape(listeners);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(task).start();

        // Wait for the above thread to create EventListener object.
        Thread.sleep(1000);

        System.out.printf(
                "[Thread-%s]Start hacking...%n", Thread.currentThread().getId());
        for(EventListener l : listeners) {
            for (Field f : l.getClass().getDeclaredFields()) {
                String name = f.getName();
                String type = f.getType().getName();
                Object value = f.get(l);
                System.out.printf(
                        " Field name: %s, %n Field Type: %s, %n Field value: %s%n",
                        name , type, value);
                if (f.getName().startsWith("this$") && value instanceof ThisRefEscape) {
                    ((ThisRefEscape)value)
                            .catchYou("HACKED! thisRefEscape is escaped!");
                }
            }
        }
    }
}
