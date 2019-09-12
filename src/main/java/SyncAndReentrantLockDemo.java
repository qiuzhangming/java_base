import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname SyncAndReentrantLockDemo
 * @Description TODO
 * 多线程之间按顺序调用，实现A-B-C三个线程启动，
 * A打印5次，B打印10次，C打印15次
 * 打印10轮
 * @Date 2019/9/12 13:06
 * @Created by joe
 */

class ShareResource {

    private volatile int flag = 1;//a:1 b:2 c:3
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();


    public void printA() {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                conditionA.await();
            }
            // 干活
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            flag = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                conditionB.await();
            }
            // 干活
            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            flag = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                conditionC.await();
            }
            // 干活
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 通知
            flag = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {

        final ShareResource shareResource = new ShareResource();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    shareResource.printA();
                    System.out.println("==="+ i);
                }

            }
        },"ThreadA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    shareResource.printB();
                    System.out.println("==="+ i);
                }
            }
        },"ThreadB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10 ; i++) {
                    shareResource.printC();
                    System.out.println("==="+ i);
                }
            }
        },"ThreadC").start();

    }

}
