package com.wrp.books.basis.spi;

/**
 * @author wrp
 * @since 2025年02月08日 11:28
 **/
public class MysqlDriver implements Driver {
    @Override
    public Connection getConnection() {
        return new MysqlConnection();
    }
}
