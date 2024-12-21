package com.wrp.books.juc.chapter13;

/**
 * @author wrp
 * @since 2024年12月21日 18:24
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        Object lock = null;
        synchronized (lock){
            System.out.println("aaa");
        }
    }
}
