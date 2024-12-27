package com.wrp.books.juc.chapter17;

/**
 * @author wrp
 * @since 2024年12月27日 12:50
 **/
public class ReadWriteLockTest {
    private final static String text = "thisistheexampleforereadwriteLock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < text.length(); j++) {
                    try {
                        char c = text.charAt(j);
                        shareData.write(c);
                        System.out.println(Thread.currentThread().getName() + " write: " + c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "writer-" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while(true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " read: " + new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "read-" + i).start();
        }
    }
}
