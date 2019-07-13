package jvm.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * 只要发生gc，就会回收
 *
 * @author zzh
 */
public class TestWeakReference {

    public static void main(String[] args) {

        Object o = new Object();

        WeakReference<Object> weakReference = new WeakReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());

        o = null;
        System.gc();
        System.out.println("-----------System.gc()之后-------------");

        System.out.println(o);
        System.out.println(weakReference.get());

    }
}
