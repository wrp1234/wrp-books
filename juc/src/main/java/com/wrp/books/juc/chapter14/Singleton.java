package com.wrp.books.juc.chapter14;

/**
 * 饿汉式单例
 * cinit方法中初始化，线程安全
 * 如果成员属性较少，性能较高，反之不适合
 * 无法进行懒加载
 *
 * @author wrp
 * @since 2024年12月23日 12:24
 **/
public class Singleton {
    private byte[] data = new byte[1024];

    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
