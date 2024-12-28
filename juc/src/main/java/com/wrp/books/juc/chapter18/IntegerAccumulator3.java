package com.wrp.books.juc.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 改进 不可变对象
 * @author wrp
 * @since 2024年12月28日 22:28
 */
public final class IntegerAccumulator3 {
    private final int init;

    public IntegerAccumulator3(int init) {
        this.init = init;
    }

    public IntegerAccumulator3(IntegerAccumulator3 accumulator, int init) {
        this.init = accumulator.value() + init;
    }

    public IntegerAccumulator3 add(int inc) {
        return new IntegerAccumulator3(this, inc);
    }

    public int value() {
        return init;
    }

    public static void main(String[] args) {
        AtomicReference<IntegerAccumulator3> accumulator = new AtomicReference<>(new IntegerAccumulator3(0));
        for (int i = 0; i < 3; i++) {
            new Thread(()-> {
                int inc = 0;
                while(true) {
                    int old = accumulator.get().value();
                    IntegerAccumulator3 newAccumulator = accumulator.get().add(inc);
                    int result = newAccumulator.value();
                    System.out.println(old + " + " + inc + " = " + result);
                    if(old + inc != result) {
                        System.out.println("error: " + old + " + " + inc + " = " + result);
                    }
                    inc++;
                    slowly();
                    accumulator.set(newAccumulator);
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
