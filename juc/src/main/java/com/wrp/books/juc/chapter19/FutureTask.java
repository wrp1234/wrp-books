package com.wrp.books.juc.chapter19;


/**
 * @author wrp
 * @since 2024年12月29日 13:14
 */
public class FutureTask<T> implements Future<T> {

    private T result;

    private boolean done = false;
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            while(!done()) {
                LOCK.wait();
            }

            return result;
        }
    }

    protected void finish(T result) {
        synchronized (LOCK) {
            if(done) {
                return;
            }

            this.result = result;
            this.done = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return done;
    }
}
