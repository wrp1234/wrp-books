package com.wrp.books.effective.chp2_create_destroy.tip1_static_factory_method;

import com.wrp.books.effective.chp2_create_destroy.tip1_static_factory_method.spi.Traffic;
import lombok.ToString;

import java.util.ServiceLoader;

/**
 * @author wrp
 * @since 2024年11月14日 7:09
 */
@ToString
public class Person {
    private int age;
    private String name;
    private Traffic traffic;
    private final static Person DEFAULT = new Person();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * 优势1: 可以改变名字，更易理解
     * 常用名：
     *      from of valueOf
     *      instance getInstance create newInstance
     *      getType newType type
     */
    public static Person of(int age, String name) {
        return new Person(age, name);
    }

    /**
     * 优势2: 控制对象的返回，如返回相同对象
     */
    public static Person of() {
        return DEFAULT;
    }

    /**
     * 优势3: 可以返回子类
     * 优势4: 可以随时调整方法实现，而不影响使用者
     */
    public static Person generate() {
        return System.currentTimeMillis()%2 == 1 ? new Man() : new Woman();
    }

    /**
     * 优势5: 返回对象所属类可以在编译时不存在，更好的实现SPI，如JDBC
     */
    public static Person generateWithoutClass() {
        ServiceLoader<Traffic> loader = ServiceLoader.load(Traffic.class);
        Person person = generate();
        person.traffic = loader.findFirst().orElseThrow();
        return person;
    }

    private static class Man extends Person {
    }

    private static class Woman extends Person {
    }
}
