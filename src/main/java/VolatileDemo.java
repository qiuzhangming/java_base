import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Data
class MyData {

    /**
     * 只能保证可见性，不能保证原子性
     */
    private volatile int num;


    private AtomicInteger atomicInteger = new AtomicInteger();

    public /*synchronized*/ void addPlusPlus() {
        num++;
    }

    public void atomicPlusPlus() {
        atomicInteger.getAndIncrement();
    }

}

public class VolatileDemo {

    public static void main(String[] args) {

        final MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        myData.addPlusPlus();
                        myData.atomicPlusPlus();
                    }
                }
            }).start();
        }

        /**
         * 等待线程执行完毕
         */
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println("结果1：" + myData.getNum());
        System.out.println("结果2：" + myData.getAtomicInteger().get());

    }

    /**
     * 可见性验证
     */
    private static void seeOkByVolatile() {
        final MyData myData = new MyData();

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "\t线程进入");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "\t修改值");
                //先睡眠后修改
                myData.setNum(60);

            }
        }, "ThreadA").start();


        while (myData.getNum() == 0) {
        }

        System.out.println(Thread.currentThread().getName() + "\t修改后：" + myData.getNum());
    }
}
