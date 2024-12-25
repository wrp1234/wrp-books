package com.wrp.books.juc.chapter16.noodle;

/**
 * @author wrp
 * @since 2024年12月25日 12:44
 **/
public class Client {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleThread("A", fork, knife).start();
        new EatNoodleThread("B", knife, fork).start();
    }

    private static void test2() {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        TablewarePair tablewarePair = new TablewarePair(fork, knife);
        new EatNoodleThread2("A", tablewarePair).start();
        new EatNoodleThread2("B", tablewarePair).start();
    }
}
