package com.wrp.books.juc.chapter25;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 聊天服务
 * @author wrp
 * @since 2025-01-15 22:11
 **/
public class ChatServer {

    private final int port;

    private ExecutorService executorService;

    private ServerSocket serverSocket;

    public ChatServer(int port) {
        this.port = port;
    }

    public ChatServer() {
        this(13312);
    }

    public void startServer() throws IOException {
        this.executorService = Executors.newFixedThreadPool(3);
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("chat server is started and listen at port:" + port);
        this.listen();
    }

    private void listen() throws IOException {
        while(true) {
            Socket client = serverSocket.accept();
            this.executorService.execute(new ClientHandler(client));
        }
    }


}
