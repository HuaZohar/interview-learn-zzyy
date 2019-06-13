package prodconsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int num = 0;
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * 生产
     */
    public void increme() {
        lock.lock();
        try {
            //防止虚假唤醒
            while (num != 0) { //有货就不生产
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费
     */
    public void decreme() {
        lock.lock();
        try {
            while (num == 0) {//没有货，等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class TestProdConsume1 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increme();
            }
        },"A").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decreme();
            }
        },"B").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increme();
            }
        },"C").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decreme();
            }
        },"D").start();

    }

}
