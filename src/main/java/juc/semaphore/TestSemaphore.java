package juc.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 模拟抢占车位
 */
public class TestSemaphore {

    public static void main(String[] args) {
        //3个车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6部车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {

                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");

                    //车停3秒
                    TimeUnit.SECONDS.sleep(3);

                    System.out.println(Thread.currentThread().getName() + "\t 停下来3秒，离开车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放车位
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
