package com.wrp.books.effective.chp2_create_destroy.tip1_static_factory_method;

/**
 * @author wrp
 * @since 2024年11月14日 7:45
 */
public class Client {
    public static void main(String[] args) {
        Person person = Person.generateWithoutClass();
        System.out.println(person);
    }
}
