package com.wrp.books.interview.spi;

import com.wrp.books.basis.spi.Driver;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wrp
 * @since 2025年02月08日 11:30
 **/
public class DriverTest {

    @Test
    public void test() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            driver.getConnection().connect();
        }
    }
}
