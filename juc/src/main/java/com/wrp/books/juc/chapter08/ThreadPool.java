package com.wrp.books.juc.chapter08;

/**
 * @author wrp
 * @since 2024年12月09日 18:44
 **/
public interface ThreadPool {
    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutdown();
}
