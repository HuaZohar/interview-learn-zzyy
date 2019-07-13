package jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用
 * @author zzh
 */
public class TestPhantomReference {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o, referenceQueue);

        System.out.println(o);//java.lang.Object@4554617c
        System.out.println(phantomReference.get());//null
        System.out.println(referenceQueue.poll());//null

        System.out.println("================================================");

        o = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o); //null
        System.out.println(phantomReference.get()); //null
        System.out.println(referenceQueue.poll()); //java.lang.ref.PhantomReference@74a14482
    }
}
