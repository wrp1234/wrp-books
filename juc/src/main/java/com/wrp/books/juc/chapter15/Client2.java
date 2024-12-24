package com.wrp.books.juc.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024-12-24 22:54
 **/
public class Client2 {
    public static void main(String[] args) {
        new ObservableThread<>(new DefaultTaskLifecycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("result is " + result);
            }
        }, ()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("over.");
            return "hello";
        }).start();
    }
}
