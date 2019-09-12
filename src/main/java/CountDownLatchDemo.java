import java.util.concurrent.CountDownLatch;

/**
 * @Classname CountDownLatchDemo
 * @Description TODO
 * @Date 2019/9/12 11:04
 * @Created by joe
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {

            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "\t" +CountryEnum.foreach_CountryEnum(finalI));
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName());
    }

}
