package com.wrp.books.juc.chapter13;

import java.util.concurrent.CountDownLatch;

/**
 * @author wrp
 * @since 2024年12月21日 18:02
 */
public class VolatileAtomicDemo {
    private static volatile int i = 0;
    private static final CountDownLatch downLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int c = 0; c < 10; c++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j++) {
                    i++;
                }
                downLatch.countDown();
            }).start();
        }

        downLatch.await();
        System.out.println(i);
    }
}
