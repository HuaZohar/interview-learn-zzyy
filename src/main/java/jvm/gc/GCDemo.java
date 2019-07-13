package jvm.gc;

import java.util.Random;

/**
 * 1.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC (DefNew+Tenured)
 * 2.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC (ParNew+Tenured)
 * <p>
 * 3/4.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC (PSYoungGen+ParOldGen)
 * <p>
 * 5.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 * <p>
 * 6. 被删除
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 * 7.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 */
public class GCDemo {

    public static void main(String[] args) {

        System.out.println("GC DEMO HELLO!!!");

        try {
            String str = "zzhua";
            while (true) {
                str += str + new Random().nextInt(777777777) + new Random().nextInt(888888888);
                //str.intern();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }

}
