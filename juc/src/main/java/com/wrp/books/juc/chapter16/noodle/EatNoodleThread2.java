package com.wrp.books.juc.chapter16.noodle;

/**
 * 改进版
 * @author wrp
 * @since 2024年12月25日 12:52
 **/
public class EatNoodleThread2 extends Thread {
    private final String name;
    private final TablewarePair pair;

    public EatNoodleThread2(String name, TablewarePair pair) {
        this.name = name;
        this.pair = pair;
    }

    @Override
    public void run() {
        while( true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (pair) {
            System.out.println(name + " take up " + pair.getLeft());
            System.out.println(name+ " take up " + pair.getRight());
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + pair.getRight());
            System.out.println(name + " put down " + pair.getLeft());
        }
    }
}
