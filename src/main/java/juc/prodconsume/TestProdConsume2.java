package juc.prodconsume;

class ShareData2 {

    private int num = 0;

    /**
     * 生产
     */
    public synchronized void increme() {
        try {
            //防止虚假唤醒
            while (num != 0) { //有货就不生产
                this.wait();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费
     */
    public synchronized void decreme() {
        try {
            while (num == 0) {//没有货，等待
                this.wait();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class TestProdConsume2 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increme();
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decreme();
            }
        }, "B").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increme();
            }
        }, "C").start();


        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decreme();
            }
        }, "D").start();

    }

}
