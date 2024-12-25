package com.wrp.books.juc.chapter16.noodle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wrp
 * @since 2024年12月25日 12:51
 **/
@Getter
@ToString
@AllArgsConstructor
public class TablewarePair {
    private final Tableware left;
    private final Tableware right;
}
