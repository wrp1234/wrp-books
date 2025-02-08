package com.wrp.books.basis.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 有了泛型之后的好处
 * 1. 安全
 *
 *
 * 有了泛型List<String> 为什么还要支持原生类型List？
 * 为了向前兼容
 *
 *
 * @author wrp
 * @since 2025年02月08日 14:37
 **/
public class AfterGeneric {

    // 简化相似的方法。
    public static class Calculator {
        public <T extends Number> double add(T a, T b) {
            return a.doubleValue() + b.doubleValue();
        }
    }

    // 运行时异常提前到编译时就能发现。集合仅能添加指定的类型
    public void collection() {
        List<Calculator> list = new ArrayList<>();
//        list.add("abc");
//        list.add(123);
        list.add(new AfterGeneric.Calculator());

        for (Object obj : list) {
            if(obj instanceof Calculator calculator) {
                System.out.println(calculator.add(1, 2));
            }
        }
    }
}
