package com.wrp.books.juc.chapter08;

/**
 * @author wrp
 * @since 2024年12月09日 18:49
 **/
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }
}
