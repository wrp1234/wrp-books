package com.wrp.books.juc.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月21日 11:10
 */
public class VolatileDemo {
    final static int MAX = 5;
    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(()-> {
            int localValue = init_value;
            while(localValue <= MAX) {
                if(init_value != localValue) {
                    System.out.printf("The init_value is updated to [%d]\n", init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        new Thread(()-> {
            int localValue = init_value;
            while(localValue <= MAX) {
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Updater").start();
    }
}
