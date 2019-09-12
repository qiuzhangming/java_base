import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Classname SpinlockDemo
 * @Description 自旋锁
 * @Date 2019/9/12 9:50
 * @Created by joe
 */
public class SpinlockDemo {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t线程进入，尝试获取锁。");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName() + "\t线程获得锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t线程进入，尝试释放锁。");
        System.out.println(atomicReference.compareAndSet(thread, null) + "\t" +thread.getName() + "\t线程释放锁");
    }

    public static void main(String[] args) {

        final SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinlockDemo.myLock();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                spinlockDemo.myUnLock();
            }

        }, "ThreadA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinlockDemo.myLock();
                spinlockDemo.myUnLock();
            }
        }, "ThreadB").start();

    }
}
