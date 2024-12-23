package com.wrp.books.juc.chapter10;

import java.nio.file.Path;

/**
 * @author wrp
 * @since 2024年12月10日 18:06
 **/
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader extClassLoader = MyClassLoaderTest.class.getClassLoader().getParent();
        // 绕过系统类加载器，避免调用项目内的HelloWorld
//        MyClassLoader classLoader = new MyClassLoader(Path.of("E:\\temp\\classloader1"), extClassLoader);
        MyClassLoader classLoader = new MyClassLoader(Path.of("E:\\temp\\classloader1"), null);
        Class<?> aClass = classLoader.loadClass("com.wrp.books.juc.chapter10.HelloWorld");
        System.out.println(aClass.getClassLoader());
        Object helloWorld = aClass.getDeclaredConstructor().newInstance();
        System.out.println(helloWorld);
        Object result = aClass.getMethod("welcome").invoke(helloWorld);
        System.out.println("result: " + result);
    }
}
