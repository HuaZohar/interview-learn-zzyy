package juc.callable;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return Thread.currentThread().getName() + "\t hello world";
    }
}

public class TestCallable {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        FutureTask future = new FutureTask<>(myThread);

//        FutureTask future2 = new FutureTask<>(myThread);

        new Thread(future, "AA").start();

//        new Thread(future2, "BB").start();

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&");
        //如果没有执行完成，则循环等待
//        while (!future.isDone()){
//            //System.out.println("$$");
//        }

        System.out.println("******************");
        try {
            //这里会阻塞等待  建议放在最后
            final Object o = future.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------");

    }
}
