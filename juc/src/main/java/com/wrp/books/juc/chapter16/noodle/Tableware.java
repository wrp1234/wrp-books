package com.wrp.books.juc.chapter16.noodle;

/**
 * @author wrp
 * @since 2024年12月25日 12:40
 **/
public class Tableware {
    private String name;

    public Tableware(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tableware{" +
                "name='" + name + '\'' +
                '}';
    }
}
