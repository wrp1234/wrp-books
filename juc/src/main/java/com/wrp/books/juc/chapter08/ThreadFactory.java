package com.wrp.books.juc.chapter08;

/**
 * @author wrp
 * @since 2024年12月09日 18:46
 **/
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
