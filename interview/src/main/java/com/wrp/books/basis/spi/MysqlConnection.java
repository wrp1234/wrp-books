package com.wrp.books.basis.spi;

/**
 * @author wrp
 * @since 2025年02月08日 11:28
 **/
public class MysqlConnection implements Connection {
    @Override
    public void connect() {
        System.out.println("Mysql connection established");
    }
}
