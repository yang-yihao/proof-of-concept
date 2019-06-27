package com.hoogia.poc.thread.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * IteratorFastFailFix
 * <p>
 * Created On 2019-06-27
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class IteratorFastFailFix {

    private static class MappingModifier implements Runnable {

        private Map<Integer, Integer> map;

        public MappingModifier(Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println("Mapping Modifier starting...");
            for (int i = 0; i < 3; i++) {
                map.put(i, i + 1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Mapping Modifier completed.");
        }
    }

    private static class MapIterator implements Runnable {

        private Map<Integer, Integer> map;

        public MapIterator(Map<Integer, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            System.out.println("Map Iterator starting...");
            synchronized (map) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    System.out.printf("Entry: <%s, %s>%n", entry.getKey(), entry.getValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Map Iterator completed.");
        }
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = -1; i >= -3; i--) {
            map.put(i, i);
        }
        new Thread(new MappingModifier(map)).start();
        new Thread(new MapIterator(map)).start();
    }

}
