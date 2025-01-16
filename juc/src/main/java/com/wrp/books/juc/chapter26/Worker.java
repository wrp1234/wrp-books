package com.wrp.books.juc.chapter26;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月16日 11:13
 **/
public class Worker extends Thread {

    private final ProductionChannel channel;

    private final static Random random = new Random(System.currentTimeMillis());

    public Worker(String name, ProductionChannel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the" + production);
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
