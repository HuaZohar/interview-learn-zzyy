package juc.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TestArrayList {

    public static void main(String[] args) {
        List<String> arrayList = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                //写
                arrayList.add(UUID.randomUUID().toString().substring(0, 6));
                //读
                System.out.println(arrayList);
            }).start();
        }


        Collections.synchronizedSet(new HashSet<>());

        Collections.synchronizedMap(new HashMap<>());


        new ConcurrentHashMap<>();
    }

}
