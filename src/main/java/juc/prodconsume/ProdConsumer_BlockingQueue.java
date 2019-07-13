package juc.prodconsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {

    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product() throws Exception {
        String data = null;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            final boolean retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板叫停了，表示FLAG=FALSE");
    }

    public void consume() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || "".equals(result.trim())) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒，没有获取到结果，退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列 " + result + "成功");
        }
    }

    public void stop(){
        FLAG = false;
    }

}

public class ProdConsumer_BlockingQueue {

    public static void main(String[] args) {

        MyResource myResource = new MyResource(new LinkedBlockingDeque<>(2));


        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 启动");

            try {
                myResource.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 启动");
            try {
                myResource.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
        System.out.println("10秒钟时间到，Main线程暂停！");
    }
}
