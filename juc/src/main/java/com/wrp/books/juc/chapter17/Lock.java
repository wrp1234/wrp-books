package com.wrp.books.juc.chapter17;

/**
 * @author wrp
 * @since 2024年12月27日 12:22
 **/
public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
