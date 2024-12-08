package com.wrp.books.juc.chapter01;

/**
 * @author wrp
 * @since 2024年12月05日 23:51
 */
public class TicketClient {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket, "T1").start();
        new Thread(ticket, "T2").start();
        new Thread(ticket, "T3").start();
        new Thread(ticket, "T4").start();
    }
}
