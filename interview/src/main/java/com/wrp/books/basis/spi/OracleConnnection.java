package com.wrp.books.basis.spi;

/**
 * @author wrp
 * @since 2025年02月08日 11:53
 **/
public class OracleConnnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Oracle connection established");
    }
}
