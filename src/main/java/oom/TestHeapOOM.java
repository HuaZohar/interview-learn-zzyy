package oom;

/**
 * 模拟堆溢出
 * <p>
 * -Xms10m -Xmx10m -XX:PrintGCDetails
 */
public class TestHeapOOM {

    public static void main(String[] args) {
        try {
            byte[] bytes = new byte[30 * 1024 * 1024]; //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
