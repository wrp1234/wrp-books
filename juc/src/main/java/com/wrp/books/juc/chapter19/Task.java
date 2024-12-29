package com.wrp.books.juc.chapter19;

/**
 * @author wrp
 * @since 2024年12月29日 13:12
 */
@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN input);
}
