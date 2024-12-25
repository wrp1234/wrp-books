package com.wrp.books.juc.chapter16.flight;

/**
 * @author wrp
 * @since 2024年12月25日 12:34
 **/
public class FlightSecurityTest {

    public static void main(String[] args) {
        FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123", "AF123").start();
        new Passengers(flightSecurity, "B123", "BF123").start();
        new Passengers(flightSecurity, "C123", "CF123").start();
    }

    static class Passengers extends Thread {
        private final FlightSecurity flightSecurity;

        private final String idCard;
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        public void run() {
            while(true) {
                flightSecurity.pass(idCard, boardingPass);
            }
        }
    }
}
