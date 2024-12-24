package com.wrp.books.juc.chapter15;

/**
 * 事件回调的响应者
 * @author wrp
 * @since 2024-12-24 22:37
 **/
public interface TaskLifecycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);
}
