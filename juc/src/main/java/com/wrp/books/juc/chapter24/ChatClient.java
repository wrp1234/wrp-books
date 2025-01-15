package com.wrp.books.juc.chapter24;

import java.io.IOException;

/**
 * @author wrp
 * @since 2025-01-15 22:26
 **/
public class ChatClient {
    public static void main(String[] args) throws IOException {
        new ChatServer().startServer();
    }
}
