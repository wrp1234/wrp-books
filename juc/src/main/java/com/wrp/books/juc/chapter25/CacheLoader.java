package com.wrp.books.juc.chapter25;

/**
 * @author wrp
 * @since 2025年01月16日 10:11
 **/
@FunctionalInterface
public interface CacheLoader<K,V> {

    V load(K key);
}
