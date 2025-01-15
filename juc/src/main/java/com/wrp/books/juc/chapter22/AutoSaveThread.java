package com.wrp.books.juc.chapter22;

import java.util.concurrent.TimeUnit;

/**
 * @author wrp
 * @since 2025年01月15日 18:46
 **/
public class AutoSaveThread extends Thread {
    private final Document doc;
    public AutoSaveThread(Document doc) {
        this.doc = doc;
    }

    @Override
    public void run() {
        while (true) {
            try {
                doc.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                break;
            }
        }
    }
}
