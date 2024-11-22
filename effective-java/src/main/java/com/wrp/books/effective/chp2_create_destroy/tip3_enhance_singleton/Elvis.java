package com.wrp.books.effective.chp2_create_destroy.tip3_enhance_singleton;

/**
 * 公有域的单例实现
 * @author wrp
 * @since 2024-11-22 07:58
 **/
public class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
        throw new RuntimeException();
    }

    public void leaveTheBuilding() {}
}
