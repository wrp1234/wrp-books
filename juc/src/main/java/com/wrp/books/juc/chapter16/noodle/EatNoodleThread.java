package com.wrp.books.juc.chapter16.noodle;

/**
 * @author wrp
 * @since 2024年12月25日 12:41
 **/
public class EatNoodleThread extends Thread {

    private final String name;
    private final Tableware left;
    private final Tableware right;

    public EatNoodleThread(String name, Tableware left, Tableware right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while( true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (left) {
            System.out.println(name + " take up " + left + "(left)");
            synchronized (right) {
                System.out.println(name+ " take up " + right + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + right + "(right)");
            }
            System.out.println(name + " put down " + left + "(left)");
        }
    }
}
