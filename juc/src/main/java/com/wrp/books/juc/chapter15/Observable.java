package com.wrp.books.juc.chapter15;

/**
 * 可观察对象
 * @author wrp
 * @since 2024-12-24 22:35
 **/
public interface Observable {

    Cycle getCycle();

    // 模拟Thread方法
    void start();

    // 模拟Thread方法
    void interrupt();

    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }
}
