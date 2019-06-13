package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 可重入锁证明
 * t1	 invoke sendMsg()
 * t1	 invoke ###sendEmail()
 * t2	 invoke sendMsg()
 * t2	 invoke ###sendEmail()
 * <p>
 * <p>
 * ReentrantLock可重入锁证明
 * t3	 invoke get()
 * t3	 invoke ###set()
 * t4	 invoke get()
 * t4	 invoke ###set()
 */
class Phone {

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getName() + "\t invoke sendMsg()");
        sendEmail();


    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoke ###sendEmail()");
    }

    /********************ReentrantLock***************************************************************/

    Lock lock = new ReentrantLock();

    public void get() {
//        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke get()");
            //注意
            set();
        } finally {
//            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {

        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t invoke ###set()");
        } finally {
            lock.unlock();
        }
    }

/*    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntColatile(var1, var2);
        } while (this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }*/

}

public class ReentrantLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendMsg();
        }, "t1").start();

        new Thread(() -> {
            phone.sendMsg();
        }, "t2").start();

        //保证上面线程执行完成 睡眠
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------");


        new Thread(() -> phone.get(), "t3").start();
        new Thread(() -> phone.get(), "t4").start();

    }


}
