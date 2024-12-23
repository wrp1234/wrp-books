package com.wrp.books.juc.chapter14;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 原始的懒汉式单例，线程不安全
 * +synchronized方法 性能低
 * + double check， 线程不安全
 * + volatile, 禁止指令重排，线程安全
 *
 * @author wrp
 * @since 2024年12月23日 12:27
 **/
public class LazySingleton {

    private byte[] data = new byte[1024];

    Object connection;

    private static volatile LazySingleton instance = null;

    private LazySingleton() {
        connection = new Object();
    }

    // 线程不安全
    public static LazySingleton getInstance1() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    // 性能低
    public synchronized static LazySingleton getInstance2() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    // 指令重排导致的线程不安全

    /**
     * instance = new LazySingleton();
     * 1. 分配空间 1xx
     * 2. LazySingleton实例化
     * 3. instance 指向分配的空间1xx
     * 如果发生指令重排，会变成 1 3 2，初始化未完成导致成员变量出现NPE
     *
     */
    public static LazySingleton getInstance3() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if(instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    // 线程安全，性能高，懒加载
    public static LazySingleton getInstance4() {
        return Holder.instance;
    }

    public static LazySingleton getInstance5() {
        return EnumHolder.INSTANCE.getInstance();
    }

    // 类加载机制实现
    private static class Holder {
        private static final LazySingleton instance = new LazySingleton();
    }

    private enum EnumHolder {
        INSTANCE;
        private LazySingleton instance;
        EnumHolder() {
            this.instance = new LazySingleton();
        }

        private LazySingleton getInstance() {
            return instance;
        }
    }

    // 并没有出现线程安全的问题，但是不能说就没有线程安全的问题
    public static void main(String[] args) throws InterruptedException {
        int size = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int j = 0; j < size; j++) {
            Thread.ofVirtual().name("thread" + j).start(()-> {
                for (int i = 0; i < 10; i++) {
                    if(LazySingleton.getInstance5().connection == null) {
                        System.out.println("no!");
                    }
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
