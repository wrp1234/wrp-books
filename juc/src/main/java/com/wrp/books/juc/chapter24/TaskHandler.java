package com.wrp.books.juc.chapter24;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025-01-15 22:07
 **/
public class TaskHandler implements Runnable {
    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("begin handle " + request);
        slowly();
        System.out.println("end handle " + request);
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
