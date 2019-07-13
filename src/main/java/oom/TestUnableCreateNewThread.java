package oom;

/**
 *
 *
 * 故障现象：
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 */
public class TestUnableCreateNewThread {

    public static void main(String[] args) {

        for (int i = 0; ; i++) {

            System.out.println("***i:" + i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
