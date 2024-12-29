package com.wrp.books.juc.chapter19;

/**
 * @author wrp
 * @since 2024年12月29日 12:29
 */
public interface Future<T> {

    T get() throws InterruptedException;

    // 判断任务是否完成
    boolean done();
}
