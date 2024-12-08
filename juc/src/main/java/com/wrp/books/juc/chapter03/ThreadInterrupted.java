package com.wrp.books.juc.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月08日 11:04
 */
public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(interrupted());
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();

    }
}
