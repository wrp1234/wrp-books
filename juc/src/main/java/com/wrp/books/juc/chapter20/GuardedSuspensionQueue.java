package com.wrp.books.juc.chapter20;

import java.util.ArrayList;
import java.util.List;

/**
 * 确认挂起模式
 * @author wrp
 * @since 2025年01月15日 18:25
 **/
public class GuardedSuspensionQueue {

    private final List<Integer> queue = new ArrayList<Integer>();
    public static final int LIMIT = 100;

    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            while(queue.size() >= LIMIT) {
                // 阻塞
                this.wait();
            }
            queue.add(data);
            // 唤醒
            this.notifyAll();
        }
    }

    public Integer take() throws InterruptedException {
        synchronized (this) {
            while(queue.isEmpty()) {
                // 阻塞
                this.wait();
            }
            // 唤醒
            this.notifyAll();
            return queue.removeFirst();
        }
    }

}
