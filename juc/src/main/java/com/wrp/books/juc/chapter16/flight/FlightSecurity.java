package com.wrp.books.juc.chapter16.flight;

/**
 * @author wrp
 * @since 2024年12月25日 12:32
 **/
public class FlightSecurity {
    private int count = 0;
    private String boardingPass;
    private String idCard;

    // synchronized 确保线程安全
    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if(boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("===exception===" + toString());
        }
    }

    public String toString() {
        return "The " + count + " passengers, boardingPass=" + boardingPass + ", idCard=" + idCard;
    }
}
