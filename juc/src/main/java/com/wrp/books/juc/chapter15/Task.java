package com.wrp.books.juc.chapter15;

/**
 * @author wrp
 * @since 2024-12-24 22:39
 **/
@FunctionalInterface
public interface Task<T> {

    T call();
}
