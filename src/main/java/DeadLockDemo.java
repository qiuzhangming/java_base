import java.util.concurrent.TimeUnit;

/**
 * @Classname DeadLockDemo
 * @Description 死锁
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互斥等待的现象，
 * 若无外力干涉那他们都将无法推进下去
 *
 * 1. jps -l 找到进程编号
 * 2. jstack 进程编号
 *
 * @Date 2019/9/11 10:48
 * @Created by joe
 */

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t自己持有：" + lockA +"\t尝试获得:" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t自己持有：" + lockB +"\t尝试获得:" + lockA);
            }
        }
    }

}


public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        // 加锁顺序不一致
        new Thread(new HoldLockThread(lockA, lockB), "ThreadA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadB").start();

    }


}
