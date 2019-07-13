package jvm.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用
 *
 * JVM配置，故意产生大对象并配置小的内存，让它内存不够用了导致OOM，看软引用的回收情况
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * @author zzh
 */
public class TestSoftReference {


    public static void main(String[] args) {
        Object o1 = new Object();

        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        System.gc();

        System.out.println("-------------------System.gc()后 begin-----------------------");

        System.out.println(o1);
        System.out.println(softReference.get());
        System.out.println("-------------------System.gc()后 end-----------------------");


        try {
            //JVM配置，故意产生大对象并配置小的内存，让它内存不够用了导致OOM
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("-------------------模拟内存不足OOM之后 begin-----------------------");
            //看软引用的回收情况
            System.out.println(o1);
            System.out.println(softReference.get());
            System.out.println("-------------------模拟内存不足OOM之后 end-----------------------");
        }

    }

}
