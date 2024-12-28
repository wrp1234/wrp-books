package com.wrp.books.juc.chapter18;

import java.util.concurrent.TimeUnit;

/**
 * 改进v1
 * @author wrp
 * @since 2024年12月28日 22:28
 */
public class IntegerAccumulator2 {
    private int init;

    public IntegerAccumulator2(int init) {
        this.init = init;
    }

    public IntegerAccumulator2 add(int inc) {
        init += inc;
        return this;
    }

    public int value() {
        return init;
    }

    public static void main(String[] args) {
        IntegerAccumulator2 accumulator = new IntegerAccumulator2(0);
        for (int i = 0; i < 3; i++) {
            new Thread(()-> {
                int inc = 0;
                while(true) {
                    synchronized (accumulator) {
                        int old = accumulator.value();
                        int result = accumulator.add(inc).value();
                        System.out.println(old + " + " + inc + " = " + result);
                        if(old + inc != result) {
                            System.out.println("error: " + old + " + " + inc + " = " + result);
                        }
                        inc++;
                    }
                    slowly();
                }
            }).start();
        }
    }

    private static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
