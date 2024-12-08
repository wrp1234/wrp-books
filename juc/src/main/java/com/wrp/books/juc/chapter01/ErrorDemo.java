package com.wrp.books.juc.chapter01;

import java.util.concurrent.TimeUnit;

/**
 *
 * Thread Demo
 * @author wrp
 * @since 2024年12月05日 23:36
 */
public class ErrorDemo {

    public static void main(String[] args) throws InterruptedException {
        startTwice();
//        startAfterOver();
    }

    private static void startAfterOver() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.start();
    }

    private static void startTwice() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        thread.start();
    }
}
