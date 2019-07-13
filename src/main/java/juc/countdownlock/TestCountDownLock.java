package juc.countdownlock;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLock {

    public static void main(String[] args) {

        //注
        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "，被灭！");
                //线程减1
                latch.countDown();
            }, CityEnum.getNameByCode(finalI).getName()).start();
        }

        try {
            //等待latch减为0   阻塞中
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("秦国，统一");

    }
}
