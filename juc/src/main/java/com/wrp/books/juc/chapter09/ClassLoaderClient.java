package com.wrp.books.juc.chapter09;

/**
 * @author wrp
 * @since 2024年12月10日 14:26
 **/
public class ClassLoaderClient {

    static {
        System.out.println("启动类被加载了");
    }

    public static void main(String[] args) {

//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        m6();
//        m7();
        m8();
    }

    // 引用静态常量不会导致类被加载，因为在编译时会被替换
    private static void m8() {
        System.out.println(Simple.MAX);
    }

    // 创建数组不会导致Simple被加载， 只是分配了块内存
    private static void m7() {
        Simple[] arr = new Simple[10];
    }

    // 启动类会导致类加载
    private static void m6() {
        // 通过子类访问父类的静态变量，只会导致父类被加载
        System.out.println(SubSimple.x);
    }

    // 初始化子类，会导致类加载
    private static void m5() {
        new SubSimple();
    }

    // 反射操作会导致类加载
    private static void m4() {
        try {
            Class.forName("com.wrp.books.juc.chapter09.Simple");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 访问类的静态方法会导致类加载
    private static void m3() {
        Simple.doing();
    }

    // 访问静态变量会导致类加载
    private static void m2() {
        System.out.println(Simple.x);
    }

    // new 对象会导致类加载
    private static void m1() {
        new Simple();
    }
}
