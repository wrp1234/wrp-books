package com.wrp.books.juc.chapter08;

/**
 * @author wrp
 * @since 2024年12月09日 18:45
 **/
public interface RunnableQueue {

    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();
}
