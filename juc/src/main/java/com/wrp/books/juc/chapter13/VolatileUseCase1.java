package com.wrp.books.juc.chapter13;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月21日 18:14
 */
class ThreadClose extends Thread {
    private volatile boolean closed = false;
    @Override
    public void run() {
        while(!closed) {
            // do work
            System.out.println("do work");
        }
    }

    public void shutdown() {
        closed = true;
        System.out.println("shutdown!");
    }
}

public class VolatileUseCase1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadClose threadClose = new ThreadClose();
        threadClose.start();
        TimeUnit.MILLISECONDS.sleep(20);
        threadClose.shutdown();
    }
}
