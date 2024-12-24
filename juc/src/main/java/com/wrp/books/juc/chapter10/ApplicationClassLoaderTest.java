package com.wrp.books.juc.chapter10;

/**
 * @author wrp
 * @since 2024年12月10日 16:53
 **/
public class ApplicationClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoaderTest.class.getClassLoader());
    }
}
