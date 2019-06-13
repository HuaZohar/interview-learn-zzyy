package synchroAndreentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。。。
 * 来10轮
 */


class ShareData {

    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (num != 1) {
                c1.await();
            }
            //打印
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知2
            num = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (num != 2) {
                c2.await();
            }
            //打印
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知3
            num = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print15() {
        lock.lock();
        try {
            //判断
            while (num != 3) {
                c3.await();
            }
            //打印
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知1
            num = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}


public class TestSychAndReentrantLock {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                shareData.print5();
            }, "AA").start();

            new Thread(() -> {
                shareData.print10();
            }, "BB").start();

            new Thread(() -> {
                shareData.print15();
            }, "CC").start();

        }

    }

}
