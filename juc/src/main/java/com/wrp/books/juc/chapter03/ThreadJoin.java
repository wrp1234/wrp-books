package com.wrp.books.juc.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wrp
 * @since 2024年12月08日 10:06
 */
public class ThreadJoin {
    public static void main(String[] args) {
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::create).toList();

        threads.forEach(Thread::start);

        // 阻塞main线程，等待threads全部执行完毕后，再继续执行main
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
