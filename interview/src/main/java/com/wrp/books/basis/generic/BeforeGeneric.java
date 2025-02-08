package com.wrp.books.basis.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么需要泛型?
 * 泛型之前，如果编程的
 * @author wrp
 * @since 2025年02月08日 14:32
 **/
public class BeforeGeneric {

    // 1.相似的逻辑，只是类型改变了，就需要方法重载的形式实现
    public static class Calculator {
        public int add(int a, int b) {
            return a + b;
        }

        public double add(double a, double b) {
            return a + b;
        }

        public float add(float a, float b) {
            return a + b;
        }
    }

    // 任何类型的数据都可以添加到集合中，在使用时必须进行类型转换，容易出现运行时异常
    public void collection() {
        List list = new ArrayList();
        list.add("abc");
        list.add(123);
        list.add(new Calculator());

        for (Object obj : list) {
            if(obj instanceof Calculator) {
                Calculator calculator = (Calculator) obj;
                System.out.println(calculator.add(1, 2));
            }
        }
    }
}
