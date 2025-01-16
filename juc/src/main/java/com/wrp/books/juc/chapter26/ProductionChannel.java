package com.wrp.books.juc.chapter26;

/**
 * @author wrp
 * @since 2025年01月16日 11:09
 **/
public class ProductionChannel {

    public static final int MAX_PROD = 100;

    private final Production[] productionsQueue;

    private int tail;
    private int head;
    private int total;

    private final Worker[] workers;

    public ProductionChannel(int workerSize) {
        workers = new Worker[workerSize];
        productionsQueue = new Production[MAX_PROD];
        for (int i = 0; i < workerSize; i++) {
            workers[i] = new Worker("Worker-" + i, this);
            workers[i].start();
        }
    }

    public void offerProduction(Production production) throws InterruptedException {
        synchronized (this) {
            while(total >= productionsQueue.length) {
                this.wait();
            }

            productionsQueue[tail] = production;
            tail = (tail + 1) % MAX_PROD;
            total++;
            this.notifyAll();
        }
    }

    public Production takeProduction() throws InterruptedException {
        synchronized (this) {
            while(total <= 0) {
                this.wait();
            }

            Production production = productionsQueue[head];
            head = (head + 1) % MAX_PROD;
            total--;
            this.notifyAll();
            return production;
        }
    }
}
