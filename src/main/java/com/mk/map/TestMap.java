package com.mk.map;

public class TestMap {

    public static void main(String[] args) {
        XMap map = new XHashMap();
        map.put("a1", 1);
        map.put("a2", 2);
        map.put("a1", 3);
        map.put("a3", 4);
        System.out.println("size:" + map.size());
        System.out.println("isEmpty:" + map.isEmpty());
        System.out.println(map.get("a1"));
    }
}
