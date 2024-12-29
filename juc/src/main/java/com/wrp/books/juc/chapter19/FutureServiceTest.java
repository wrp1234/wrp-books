package com.wrp.books.juc.chapter19;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月29日 13:25
 */
public class FutureServiceTest {
    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = new FutureServiceImpl<>();
        Future<Void> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("I a finish done.");
        });

        future.get();

        FutureService<String, Integer> service2 = new FutureServiceImpl<>();
        Future<Integer> future2 = service2.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return input.length();
        }, "hello world", r -> System.out.println("callback: " + r));
        System.out.println(future2.get());
    }
}
