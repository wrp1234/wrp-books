package com.wrp.books.juc.chapter09;

/**
 * @author wrp
 * @since 2024年12月10日 14:25
 **/
public class Simple {
    static {
        System.out.println("Simple is initialized.");
    }

    public static int x = 10;
    public static final int MAX = 100;


    public static void doing() {
        System.out.println("Simple is doing.");
    }
}
