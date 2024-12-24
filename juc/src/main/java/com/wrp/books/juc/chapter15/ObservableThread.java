package com.wrp.books.juc.chapter15;

/**
 * 事件源
 * @author wrp
 * @since 2024-12-24 22:39
 **/
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new DefaultTaskLifecycle<>(), task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if(task == null) {
            throw new IllegalArgumentException("task is required.");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    @Override
    public final void run() {
        update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if(lifecycle == null) return;

        try {
            switch (cycle) {
                case STARTED -> lifecycle.onStart(currentThread());
                case RUNNING -> lifecycle.onRunning(currentThread());
                case DONE -> lifecycle.onFinish(currentThread(), result);
                case ERROR -> lifecycle.onError(currentThread(), e);
            }
        } catch (Exception ex) {
            // 如果为ERROR，说明是task中抛出来的错误，需要继续抛出
            if(cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }
}
