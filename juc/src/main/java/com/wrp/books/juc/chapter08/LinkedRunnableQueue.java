package com.wrp.books.juc.chapter08;

import java.util.LinkedList;

/**
 * @author wrp
 * @since 2024年12月09日 18:53
 **/
public class LinkedRunnableQueue implements RunnableQueue {

    private final int limit;
    private final DenyPolicy denyPolicy;
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();
    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList) {
            if(runnableLinkedList.size() >= limit) {
                denyPolicy.reject(runnable, threadPool);
            } else {
                runnableLinkedList.add(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableLinkedList) {
            while(runnableLinkedList.isEmpty()) {
                runnableLinkedList.wait();
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList) {
            return runnableLinkedList.size();
        }
    }
}
