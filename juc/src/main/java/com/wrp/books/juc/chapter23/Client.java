package com.wrp.books.juc.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月15日 19:13
 **/
public class Client {
    public static void main(String[] args) {
        Latch latch = new CountDownLatch(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Gavin", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();
        try {
            latch.await(TimeUnit.SECONDS, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=== all of programmer arrived ===");
    }
}
