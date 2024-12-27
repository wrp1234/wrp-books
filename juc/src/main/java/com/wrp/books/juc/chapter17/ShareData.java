package com.wrp.books.juc.chapter17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2024年12月27日 12:46
 **/
public class ShareData {
    private final List<Character> container = new ArrayList<>();
    private final ReadWriteLock lock = new ReadWriteLockImpl();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add('c');
        }
    }

    public char[] read() throws InterruptedException {
        try{
            readLock.lock();
            char[] result = new char[length];
            for (int i = 0; i < length; i++) {
                result[i] = container.get(i);
            }
            slowly();
            return result;
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try{
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                container.add(i, c);
            }
            slowly();
        } finally {
            writeLock.unlock();
        }
    }

    private void slowly() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

}
