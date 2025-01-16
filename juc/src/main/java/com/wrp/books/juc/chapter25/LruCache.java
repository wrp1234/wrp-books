package com.wrp.books.juc.chapter25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author wrp
 * @since 2025年01月16日 10:04
 **/
public class LruCache<K,V> {
    // 维护key的顺序，越近使用的key越在后面
    private final LinkedList<K> keyList = new LinkedList<>();

    private final Map<K, V> cache = new HashMap<>();

    private final int capacity;

    private final CacheLoader<K,V> cacheLoader;

    public LruCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value) {
        if(keyList.size() >= capacity) {
            K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }
        // 维护key的顺序，最新使用的在keyList最后
        if(keyList.contains(key)) {
            keyList.remove(key);
        }
        keyList.addLast(key);

        cache.put(key, value);
    }

    public V get(K key) {
        boolean ok = keyList.remove(key);
        if(ok) {
            keyList.addLast(key);
            return cache.get(key);
        } else {
            V value = cacheLoader.load(key);
            this.put(key, value);
            return value;
        }
    }

    @Override
    public String toString() {
        return "LruCache{" +
                "keyList=" + keyList +
                '}';
    }
}
