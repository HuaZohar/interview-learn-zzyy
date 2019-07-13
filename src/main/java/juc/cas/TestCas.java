package juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class TestCas {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        atomicInteger.getAndIncrement();
        /**
         * compareAndSet
         * 参数1：期望值
         * 参数2：设置值
         * 如果期望值与主物理内存相同，则设置，否则不设置
         */
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data : " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2014)+"\t current data : " + atomicInteger.get());
    }
}
