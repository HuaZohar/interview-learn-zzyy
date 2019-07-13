package juc.volatile1;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {

    volatile int num = 0;

    public void increment() {
        num = 60;
    }

    public void add() {
        num++;
    }


    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void atoPlus(){
        atomicInteger.getAndIncrement();
    }

}

public class TestVolatile {

    public static void main(String[] args) {


        //test1();

        MyData myData = new MyData();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.add();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally num is " + myData.num);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.atoPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally num is " + myData.atomicInteger.get());


    }


    //通过检查volatile的可见性
    private static void test1() {

        MyData myData = new MyData();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.increment();
            System.out.println(Thread.currentThread().getName() + " update end");
        }, "AAA").start();


        while (myData.num == 0) {
            //main线程一直等待摘掉num不为0
        }

        System.out.println(Thread.currentThread().getName() + " \t missing is ....");

    }
}
