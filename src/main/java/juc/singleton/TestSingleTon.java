package juc.singleton;

public class TestSingleTon {

    private volatile static TestSingleTon instance;

    private TestSingleTon() {
        System.out.println("我是构造方法。。");
    }

    public static TestSingleTon getInstance() {
        if (instance == null) {
            synchronized (TestSingleTon.class) {
                if (instance == null) {
                    instance = new TestSingleTon();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                TestSingleTon.getInstance();
            }).start();
        }

    }
}
