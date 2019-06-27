package com.hoogia.poc.thread.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * IteratorSafeRemove
 * <p>
 * Created On 2019-06-27
 * Email iyihao@outlook.com
 *
 * @author yihao yang
 */
public class IteratorSafeRemove {

    private static class MappingRemover implements Runnable {

        private Iterator<Map.Entry<Integer, Integer>> iter;

        public MappingRemover(Iterator<Map.Entry<Integer, Integer>> iter) {
            this.iter = iter;
        }

        @Override
        public void run() {
            System.out.println("Mapping Remover starting...");
            for ( ; iter.hasNext(); iter.next() ) {
                iter.remove();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Mapping Remover completed.");
        }
    }

    private static class MapIterator implements Runnable {

        private Iterator<Map.Entry<Integer, Integer>> iter;

        public MapIterator(Iterator<Map.Entry<Integer, Integer>> iter) {
            this.iter = iter;
        }

        @Override
        public void run() {
            System.out.println("Map Iterator starting...");
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = -1; i >= -3; i--) {
                map.put(i, i);
            }
            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entry = iter.next();
                System.out.printf("Entry: <%s, %s>%n", entry.getKey(), entry.getValue());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Map Iterator completed.");
        }
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = -1; i >= -3; i--) {
            map.put(i, i);
        }
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        new Thread(new MapIterator(iter)).start();
        new Thread(new MappingRemover(iter)).start();
    }
}
