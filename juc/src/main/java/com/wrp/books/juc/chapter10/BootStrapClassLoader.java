package com.wrp.books.juc.chapter10;

/**
 * @author wrp
 * @since 2024年12月10日 15:50
 **/
public class BootStrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap:" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
