package com.wrp.books.juc.chapter24;

/**
 * @author wrp
 * @since 2025-01-15 22:07
 **/
public class Request {

    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }

}
