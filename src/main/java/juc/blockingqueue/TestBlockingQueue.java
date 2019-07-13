package juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {
    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        try {
            System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("b", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));

            System.out.println(blockingQueue.offer("c", 2, TimeUnit.SECONDS));//超过2秒 false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
