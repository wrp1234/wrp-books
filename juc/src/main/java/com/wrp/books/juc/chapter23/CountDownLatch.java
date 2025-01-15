package com.wrp.books.juc.chapter23;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月15日 19:02
 **/
public class CountDownLatch extends Latch{
    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if(limit <= 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        return limit;
    }

    @Override
    public void await(TimeUnit unit, long timeout) throws InterruptedException, WaitTimeoutException {
        if(timeout <= 0) {
            throw new IllegalArgumentException("the timeout must be greater than 0");
        }

        long remaining = unit.toNanos(timeout);
        final long endNanos = System.nanoTime() + remaining;
        synchronized (this) {
            while( limit > 0) {
                // 剩余时间不足一毫秒，抛出超时异常
                if(TimeUnit.NANOSECONDS.toMillis(remaining) <= 0) {
                    throw new WaitTimeoutException("the wait timeout over specify time.");
                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remaining));
                remaining = endNanos - System.nanoTime();
            }
        }
    }
}
