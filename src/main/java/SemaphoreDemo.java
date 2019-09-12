import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SemaphoreDemo
 * @Description TODO
 * @Date 2019/9/12 11:54
 * @Created by joe
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception e) {

                    } finally {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }

}
