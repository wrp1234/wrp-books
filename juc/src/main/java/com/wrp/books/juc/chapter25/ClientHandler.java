package com.wrp.books.juc.chapter25;

import java.io.*;
import java.net.Socket;

/**
 * 两阶段提交
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
            // 阶段1：business
            chat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 阶段2：释放资源
            this.release();
        }
    }

    private void release() {
        try {
            if(socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = wrap2Reader(this.socket.getInputStream());
        PrintStream printStream = wrap2Print(this.socket.getOutputStream());
        String received;
        while((received = bufferedReader.readLine()) != null) {
            System.out.printf("client:%s-message:%s\n", clientIdentify, received);
            if(received.equals("q")) {
                write2Client(printStream, "client will close");
                break;
            }
            write2Client(printStream, "Server:" + received);
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
