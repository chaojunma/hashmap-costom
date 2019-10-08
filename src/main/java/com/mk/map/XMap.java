package com.mk.map;

/**
 *
 * @param <K>
 * @param <V>
 */
public interface XMap<K,V> {

    // 获取map长度方法
    int size();

    // 判断map对象是否为空
    boolean isEmpty();

    // 通过键key获取value值
    Object get(Object key);

    // 向map对象中存值
    Object put(Object key, Object value);

    interface Entry<k,v>{
        k getkey();
        v getValue();
    }
}
