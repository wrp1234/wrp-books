package com.wrp.books.juc.chapter14;

/**
 * 枚举类不允许被继承，线程安全的单例, 不能懒加载
 * @author wrp
 * @since 2024年12月23日 13:01
 **/
public enum EnumSingleton {
    INSTANCE;

    private byte[] data = new byte[1024];

    EnumSingleton() {
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method() {
        // 调用方法，会主动实例化
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        EnumSingleton.method();
    }
}
