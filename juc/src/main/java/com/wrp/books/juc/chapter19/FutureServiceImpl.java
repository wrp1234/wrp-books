package com.wrp.books.juc.chapter19;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wrp
 * @since 2024年12月29日 13:18
 */
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    public static final String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nexId = new AtomicInteger(0);

    @Override
    public Future<Void> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        Thread.ofVirtual().name(getNextName()).start(()-> {
            runnable.run();
            // 任务完成，通知其他线程
            future.finish(null);
        });
        return future;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {

        final FutureTask<OUT> future = new FutureTask<>();
        Thread.ofVirtual().name(getNextName()).start(()-> {
            OUT out = task.get(input);
            // 任务完成，通知其他线程
            future.finish(out);
            if(null != callback) {
                callback.call(out);
            }
        });
        return future;
    }

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nexId.incrementAndGet();
    }
}
