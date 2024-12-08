package com.wrp.books.juc.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月08日 11:08
 */
public class ThreadInterruptedTest {
    public static void main(String[] args) {
//        System.out.println("Main thread is interrupted? " + Thread.interrupted());
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("Main thread is interrupted? " + Thread.currentThread().isInterrupted());
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I will be interrupted still.");
        }
    }
}
