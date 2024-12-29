package com.wrp.books.juc.chapter19;


/**
 * @author wrp
 * @since 2024年12月29日 12:31
 */
public interface FutureService<IN, OUT> {

    Future<Void> submit(Runnable runnable);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);
}
