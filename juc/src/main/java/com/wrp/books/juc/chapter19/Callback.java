package com.wrp.books.juc.chapter19;

/**
 * @author wrp
 * @since 2024年12月29日 13:50
 */
public interface Callback<T> {

    void call(T t);
}
