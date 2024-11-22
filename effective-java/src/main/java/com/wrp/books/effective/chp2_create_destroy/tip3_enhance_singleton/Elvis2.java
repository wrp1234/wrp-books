package com.wrp.books.effective.chp2_create_destroy.tip3_enhance_singleton;

/**
 * 静态工厂方法单例实现
 * @author wrp
 * @since 2024-11-22 07:58
 **/
public class Elvis2 {

    private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
        throw new RuntimeException();
    }

    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {}
}
