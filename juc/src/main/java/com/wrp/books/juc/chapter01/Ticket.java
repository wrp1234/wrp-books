package com.wrp.books.juc.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月05日 23:49
 */
public class Ticket implements Runnable {

    public static final int MAX = 50;

    private int index = 1;

    @Override
    public void run() {
        while(MAX >= index) {
            System.out.println("售票机：" + Thread.currentThread().getName() + "卖出第：" + (index++) + "张票");
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
