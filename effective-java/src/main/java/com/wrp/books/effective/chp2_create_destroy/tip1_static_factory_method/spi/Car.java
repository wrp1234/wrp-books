package com.wrp.books.effective.chp2_create_destroy.tip1_static_factory_method.spi;

/**
 * @author wrp
 * @since 2024年11月14日 7:50
 */
public class Car implements Traffic {
    @Override
    public String getName() {
        return "小汽车";
    }
}
