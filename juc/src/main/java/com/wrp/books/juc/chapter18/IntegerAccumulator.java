package com.wrp.books.juc.chapter18;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月28日 22:28
 */
public class IntegerAccumulator {
    private int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public IntegerAccumulator add(int inc) {
        init += inc;
        return this;
    }

    public int value() {
        return init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        for (int i = 0; i < 3; i++) {
            new Thread(()-> {
                int inc = 0;
                while(true) {
                    int old = accumulator.value();
                    int result = accumulator.add(inc).value();
                    System.out.println(old + " + " + inc + " = " + result);
                    if(old + inc != result) {
                        System.out.println("error: " + old + " + " + inc + " = " + result);
                    }
                    inc++;
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
