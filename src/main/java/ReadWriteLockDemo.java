import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Classname ReadWriteLockDemo
 * @Description TODO
 * @Date 2019/9/12 10:26
 * @Created by joe
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t===正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t===写入完成：" + key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {

        this.lock.readLock().lock();
        try {
            //System.out.println(Thread.currentThread().getName() + "\t正在读取：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成：" + o);
        } finally {
            this.lock.readLock().unlock();
        }


    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        final MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    myCache.put( finalI+"", finalI);
                }
            },"Thread"+i).start();
        }

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    myCache.get( finalI+"");
                }
            },"Thread"+i).start();
        }
    }
}
