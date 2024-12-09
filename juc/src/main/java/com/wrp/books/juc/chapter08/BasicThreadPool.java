package com.wrp.books.juc.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wrp
 * @since 2024年12月09日 18:57
 **/
public class BasicThreadPool implements ThreadPool {
    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;
    private final ThreadFactory threadFactory;
    private final RunnableQueue runnableQueue;
    private volatile boolean isShutdown = false;
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();
    private final long keepAliveTime;
    private final TimeUnit timeUnit;
    private final Thread managerThread;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize,
                DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        managerThread = new Thread(()-> {
            while(!isShutdown && !Thread.currentThread().isInterrupted()) {
                try {
                    timeUnit.sleep(keepAliveTime);
                } catch (InterruptedException e) {
                    isShutdown = true;
                    break;
                }

                synchronized (this) {
                    if(isShutdown) {
                        break;
                    }

                    if(runnableQueue.size() > 0 && activeCount < coreSize) {
                        for (int i = initSize; i < coreSize; i++) {
                            newThread();
                        }
                        continue;
                    }

                    if(runnableQueue.size() > 0 && activeCount < maxSize) {
                        for (int i = coreSize; i < maxSize; i++) {
                            newThread();
                        }
                    }

                    if(runnableQueue.size() == 0 && activeCount > coreSize) {
                        for (int i = coreSize; i < activeCount; i++) {
                            removeThread();
                        }
                    }
                }
            }
        });
        this.init();
    }

    private void init() {
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
        managerThread.start();
    }

    private void newThread() {
        InternalTask task = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(task);
        ThreadTask threadTask = new ThreadTask(thread, task);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.thread.interrupt();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        checkStatus();
        this.runnableQueue.offer(runnable);
    }

    private void checkStatus() {
        if(this.isShutdown) {
            throw new IllegalStateException("ThreadPool is closed");
        }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if(isShutdown) {
                return;
            }

            isShutdown = true;
            threadQueue.forEach(task -> {
//                task.internalTask.stop();
                task.thread.interrupt();
            });
            managerThread.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        checkStatus();
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        checkStatus();
        return maxSize;
    }

    @Override
    public int getCoreSize() {
        checkStatus();
        return coreSize;
    }

    @Override
    public int getQueueSize() {
        checkStatus();
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    private static class ThreadTask {
        final Thread thread;
        final InternalTask internalTask;
        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private final ThreadGroup group = new ThreadGroup("basic-thread-pool-" + GROUP_COUNTER.getAndIncrement());
        private final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "basic-thread-pool-" + COUNTER.getAndIncrement());
        }
    }
}
