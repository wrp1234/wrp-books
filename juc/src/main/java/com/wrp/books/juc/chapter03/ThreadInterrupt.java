package com.wrp.books.juc.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月08日 10:49
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
//        interruptedWhenRunning();
//        interruptedWhenOver();
        interruptedBeforeStart();
    }


    // 未启动的线程被打断，会直接被忽略
    private static void interruptedBeforeStart() {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("oh, i'm be interrupted.");
            }
        });
        thread.interrupt();
    }

    // 结束的线程被打断，会直接忽略interrupt
    private static void interruptedWhenOver() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("oh, i'm be interrupted.");
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }

    private static void interruptedWhenRunning() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("oh, i'm be interrupted.");
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
