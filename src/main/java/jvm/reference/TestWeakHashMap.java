package jvm.reference;

import java.util.WeakHashMap;

public class TestWeakHashMap {

    public static void main(String[] args) {

        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "value";
        weakHashMap.put(key, value);
        System.out.println("weakHashMap 原始数据:" + weakHashMap + ", map size : " + weakHashMap.size());
        key = null;
        System.out.println("weakHashMap key=null之后：" + weakHashMap);
        System.gc();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("weakHashMap 发生GC之后：" + weakHashMap + ", map size : " + weakHashMap.size());

    }
}
