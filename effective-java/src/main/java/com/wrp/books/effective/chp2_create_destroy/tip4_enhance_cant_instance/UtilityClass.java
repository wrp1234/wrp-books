package com.wrp.books.effective.chp2_create_destroy.tip4_enhance_cant_instance;

/**
 * 工具类，不应该被实例化
 * @author wrp
 * @since 2024-11-22 08:17
 **/
public class UtilityClass {
    private UtilityClass() {
        throw new AssertionError();
    }
}
