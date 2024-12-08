package com.wrp.books.juc.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月08日 10:58
 */
public class ThreadIsInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    // 可中断方法会捕获中断异常，并清除中断信息
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf("I'm be interrupted? %s \n",
                            Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted? %s \n",
                thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted? %s \n",
                thread.isInterrupted());
    }
}
