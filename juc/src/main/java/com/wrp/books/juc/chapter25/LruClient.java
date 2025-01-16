package com.wrp.books.juc.chapter25;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月16日 10:12
 **/
public class LruClient {
    public static void main(String[] args) {
        //test1();
//        maxMemery();
        softMaxMemery();
    }

    private static void softMaxMemery() {
        SoftLruCache<Integer, Reference> cache = new SoftLruCache<>(200, Reference::new);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache.get(i);
            // 如果数据插入太快，也会导致内存溢出
            slowly();
            System.out.println("the " + i + " reference stored at cache.");
        }
    }

    private static void maxMemery() {
        LruCache<Integer, Reference> cache = new LruCache<>(200, Reference::new);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cache.get(i);
            slowly();
            System.out.println("the " + i + " reference stored at cache.");
        }
    }

    private static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void test1() {
        LruCache<String, Reference> cache = new LruCache<>(5, Reference::new);
        cache.get("Alex");
        cache.get("Jack");
        cache.get("Gavin");
        cache.get("Dillon");
        cache.get("Leo");
        cache.get("Jenny");
        System.out.println(cache);
    }
}
