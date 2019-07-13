package juc.deadlock;

import java.util.concurrent.TimeUnit;


/**
 * 死锁模拟
 *
 * jvisualvm.exe
 */
class MyResource {

    public void lock(String lock1, String lock2) {
        synchronized (lock1) {

            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lock1 + "\t 尝试获得：" + lock2);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lock2 + "\t 尝试获得：" + lock1);
            }
        }
    }
}

public class TestDeadLock {

    public static void main(String[] args) {

        MyResource myResource = new MyResource();

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(() -> myResource.lock(lockA, lockB), "AAA").start();
        new Thread(() -> myResource.lock(lockB, lockA), "BBB").start();
    }
}
