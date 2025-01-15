package com.wrp.books.juc.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月15日 19:00
 **/
public abstract class Latch {

    protected int limit;
    public Latch(int limit) {
        this.limit = limit;
    }

    public abstract  void await() throws InterruptedException;

    public abstract void countDown();

    public abstract int getUnarrived();

    public abstract void await(TimeUnit unit, long timeout) throws InterruptedException, WaitTimeoutException;
}
