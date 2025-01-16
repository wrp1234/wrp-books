package com.wrp.books.juc.chapter25;

/**
 * @author wrp
 * @since 2025年01月16日 10:03
 **/
public class Reference {
    private Object name;
    // 1M+
    private final byte[] data = new byte[1 << 20];

    public Reference(Object name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("the reference will be gc.");
    }
}
