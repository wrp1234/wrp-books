package com.wrp.books.collection.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 快速失败机制
 * @author wrp
 * @since 2025年02月08日 16:06
 **/
public class Failfast {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if(next % 3 == 0) {
//                list.remove(next);// fail-fast
                iterator.remove();
                continue;
            }
            System.out.println(next);
        }
    }
}
