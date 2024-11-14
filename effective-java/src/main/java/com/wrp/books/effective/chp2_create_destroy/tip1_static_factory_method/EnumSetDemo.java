package com.wrp.books.effective.chp2_create_destroy.tip1_static_factory_method;

import java.util.EnumSet;

/**
 * @author wrp
 * @since 2024年11月14日 7:29
 */
public class EnumSetDemo {
    public static void main(String[] args) {
        EnumSet<Gender> enumSet = EnumSet.of(Gender.MALE);
        System.out.println(enumSet);
    }

    enum Gender {
        MALE,FEMALE
    }
}
