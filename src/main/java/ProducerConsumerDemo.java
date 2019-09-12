import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname ProducerConsumerDemo
 * @Description TODO
 * @Date 2019/9/12 12:37
 * @Created by joe
 */

class ShareData {

    private volatile int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {

        lock.lock();
        try {
            // 判断
            while (number != 0) {
                //等待，不能生产
//            this.wait();
                condition.await();
            }
            // 生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t 生产：" + number);

            //通知唤醒
            condition.signalAll();
//        this.notifyAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {

        lock.lock();
        try {
            // 判断
            while (number == 0) {
                //等待
                condition.await();
            }

            // 消费
            System.out.println(Thread.currentThread().getName()+"\t 消费：" + number);
            number--;

            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        final ShareData shareData = new ShareData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "ThreadA").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "ThreadB").start();

    }
}
