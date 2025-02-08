package com.wrp.books.basis.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符枚举
 *
 * PECS规则；生产者，读数据用 ? extend ； 消费者，写数据用 ? super
 * @author wrp
 * @since 2025年02月08日 14:43
 **/
public class WildcardGeneric<T> {

    public static void main(String[] args) {
        WildcardGeneric<Integer> w = new WildcardGeneric<Integer>();
        List lista = new ArrayList();
        lista.add(1);
        w.rawList(lista);
        w.objList(lista);
        w.questionList(lista);
        w.upperList(lista);
        w.lowerList(lista);
        w.tList(lista);
        w.upperTList(lista);
        w.lowerTList(lista);

    }

    public void rawList(List list) {
        System.out.println(list);
    }

    public void objList(List<Object> list) {
        System.out.println(list);
    }

    public void questionList(List<?> list) {
//        list.add(1);
        System.out.println(list);
    }

    public void upperList(List<? extends Number> list) {
//        list.add(2);
        Number number = list.get(0);
        System.out.println(list);
    }

    public void lowerList(List<? super Number> list) {
        list.add(2);
        Object o = list.get(0);
        System.out.println(list);
    }

    public void tList(List<T> list) {
        System.out.println(list);
    }

    public void upperTList(List<? extends T> list) {
        System.out.println(list);
    }

    public void lowerTList(List<? super T> list) {
        System.out.println(list);
    }
}
