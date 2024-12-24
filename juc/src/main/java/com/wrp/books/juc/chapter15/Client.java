package com.wrp.books.juc.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024-12-24 22:49
 **/
public class Client {
    public static void main(String[] args) {
        Observable observable = new ObservableThread<>(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("over.");
            return 1;
        });
        observable.start();
    }
}
