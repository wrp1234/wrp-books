package com.wrp.books.juc.chapter26;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author wrp
 * @since 2025年01月16日 11:15
 **/
public class Test {
    public static void main(String[] args) {
        final ProductionChannel channel = new ProductionChannel(5);
        AtomicInteger no = new AtomicInteger();
        IntStream.range(1, 8).forEach(i ->
                new Thread(()-> {
                    while(true) {
                        try {
                            channel.offerProduction(new Production(no.getAndIncrement()));
                            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start());
    }
}
