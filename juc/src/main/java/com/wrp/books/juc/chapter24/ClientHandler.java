package com.wrp.books.juc.chapter24;

import java.io.*;
import java.net.Socket;

/**
 * @author wrp
 * @since 2025-01-15 22:15
 **/
public class ClientHandler implements Runnable {
    Socket socket;
    private final String clientIdentify;

    public ClientHandler(Socket client) {
        socket = client;
        this.clientIdentify = client.getInetAddress().getHostAddress() +
                ":" + client.getPort();
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = wrap2Reader(this.socket.getInputStream());
            PrintStream printStream = wrap2Print(this.socket.getOutputStream());
            String received;
            while((received = bufferedReader.readLine()) != null) {
                System.out.printf("client:%s-message:%s\n", clientIdentify, received);
                if(received.equals("q")) {
                    write2Client(printStream, "client will close");
                    socket.close();
                    break;
                }
                write2Client(printStream, "Server:" + received);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write2Client(PrintStream printStream, String message) {
        printStream.println(message);
        printStream.flush();
    }

    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    private BufferedReader wrap2Reader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
