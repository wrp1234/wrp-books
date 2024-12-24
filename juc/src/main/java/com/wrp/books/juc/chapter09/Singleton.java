package com.wrp.books.juc.chapter09;

/**
 * 静态变量初始化是从上到下的
 * 静态代码块先于静态变量初始化，从上到下
 * @author wrp
 * @since 2024年12月10日 14:38
 **/
public class Singleton {

    static {
        System.out.println("before");
    }

    private static int a = 0;
    private static int b;
    private int c = 0;
    private int d;
    private static Singleton instance = new Singleton();

    static {
        System.out.println("after");
    }

    private Singleton() {
        a++;
        b++;
        c++;
        d++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        System.out.println(s1.a);
        System.out.println(s1.b);
        System.out.println(s1.c);
        System.out.println(s1.d);
    }
}
