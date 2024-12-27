package com.wrp.books.juc.chapter17;

/**
 * @author wrp
 * @since 2024年12月27日 12:25
 **/
public class ReadWriteLockImpl implements ReadWriteLock{

    private final Object MUTEX = new Object();

    private int writingWriters = 0;
    private int waitingWriters = 0;
    private int readingReaders = 0;

    // 偏好设置
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return writingWriters;
    }

    @Override
    public int getReadingReaders() {
        return readingReaders;
    }

    private void incrementReadingReaders() {
        readingReaders++;
    }

    private void decrementReadingReaders() {
        readingReaders--;
    }

    private void changePrefer(Boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    private void decrementWritingWriters() {
        writingWriters--;
    }

    private void incrementWritingWriters() {
        writingWriters++;
    }

    private void decrementWaitingWriters() {
        waitingWriters--;
    }

    private void incrementWaitingWriters() {
        waitingWriters++;
    }


    static class ReadLock implements Lock {

        private final ReadWriteLockImpl readWriteLock;

        public ReadLock(ReadWriteLockImpl readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void lock() throws InterruptedException {
            synchronized (readWriteLock.MUTEX) {
                while (readWriteLock.getWritingWriters() > 0 ||
                        (readWriteLock.preferWriter && readWriteLock.waitingWriters > 0)) {
                    readWriteLock.MUTEX.wait();
                }
                // 成功获取读锁
                readWriteLock.incrementReadingReaders();
            }
        }

        @Override
        public void unlock() {
            synchronized (readWriteLock.MUTEX) {
                readWriteLock.decrementReadingReaders();
                // 让写线程有更多机会
                readWriteLock.changePrefer(true);
                readWriteLock.MUTEX.notifyAll();
            }
        }
    }

    static class WriteLock implements Lock {
        private final ReadWriteLockImpl readWriteLock;
        public WriteLock(ReadWriteLockImpl readWriteLock) {
            this.readWriteLock = readWriteLock;
        }


        @Override
        public void lock() throws InterruptedException {
            synchronized (readWriteLock.MUTEX) {
                try {
                    readWriteLock.incrementWaitingWriters();
                    // 正在读或正在写都会阻塞写线程
                    while( readWriteLock.getReadingReaders() > 0 ||
                            readWriteLock.getWritingWriters() > 0) {
                        readWriteLock.MUTEX.wait();
                    }
                } finally {
                    readWriteLock.decrementWaitingWriters();
                }
                readWriteLock.incrementWritingWriters();
            }
        }

        @Override
        public void unlock() {
            synchronized (readWriteLock.MUTEX) {
                readWriteLock.decrementWritingWriters();
                // 让读锁有更多机会
                readWriteLock.changePrefer(false);
                readWriteLock.MUTEX.notifyAll();
            }
        }
    }
}
