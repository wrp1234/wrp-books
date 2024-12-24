package com.wrp.books.juc.chapter15;

/**
 * 空实现
 * @author wrp
 * @since 2024-12-24 22:48
 **/
public class DefaultTaskLifecycle<T> implements TaskLifecycle<T> {
    @Override
    public void onStart(Thread thread) {

    }

    @Override
    public void onRunning(Thread thread) {

    }

    @Override
    public void onFinish(Thread thread, T result) {

    }

    @Override
    public void onError(Thread thread, Exception e) {

    }
}
