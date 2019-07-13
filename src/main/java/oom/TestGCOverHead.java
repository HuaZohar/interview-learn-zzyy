package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 频繁GC 导致
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * GC回收时间过长时会抛出OutOfMemoryError。时间过长的定义是，超出98%的时间用来做GC，并且回收了不到2%的堆内存
 * 连续多次GC都只回收了不到2%的极端情况才会抛出。
 * 假如不抛出GC overhead limit 错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会再次填满，迫使GC再次执行，这样就形成恶性循环，
 * CPU使用率一直是100%，而GC却没有任何成果
 */
public class TestGCOverHead {

    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());   //java.lang.OutOfMemoryError: GC overhead limit exceeded
            }
        } catch (Exception e) {
            System.out.println("*****************i:" + i);
            e.printStackTrace();
            throw e;
        }
    }

}
