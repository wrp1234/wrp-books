package com.wrp.books.juc.chapter24;

/**
 * 接线员
 * @author wrp
 * @since 2025-01-15 22:10
 **/
public class Operator {

    // TODO 改用线程池
    public void call(String business) {
        new Thread(new TaskHandler(new Request(business))).start();
    }
}
