package com.wrp.books.basis.spi;

/**
 * @author wrp
 * @since 2025年02月08日 11:52
 **/
public class OracleDriver implements Driver{
    @Override
    public Connection getConnection() {
        return new OracleConnnection();
    }
}
