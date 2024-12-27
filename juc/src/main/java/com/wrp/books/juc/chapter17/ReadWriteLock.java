package com.wrp.books.juc.chapter17;

/**
 * @author wrp
 * @since 2024年12月27日 12:22
 **/
public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();

    int getWritingWriters();

    int getReadingReaders();

}
