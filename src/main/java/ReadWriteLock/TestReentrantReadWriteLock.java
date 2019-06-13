package ReadWriteLock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自己实现缓存
 */
class MyCache {
    //必须保证可见性
    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始写入：" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        //加读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始读取");
            final Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}


public class TestReentrantReadWriteLock {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            new Thread(() -> myCache.put(tmp + "", tmp + "")).start();
        }

        for (int i = 0; i < 10; i++) {
            final int tmp = i;
            new Thread(() -> myCache.get(tmp + "")).start();
        }
    }

}
