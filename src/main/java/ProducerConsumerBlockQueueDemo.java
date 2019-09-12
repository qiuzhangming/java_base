import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ProducerConsumerBlockQueueDemo
 * @Description TODO
 * @Date 2019/9/12 13:43
 * @Created by joe
 */

class MyResource {

    private volatile boolean FLAGE = true;

    private BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);

    public void prodece() {
        while (FLAGE) {
            try {
                blockingQueue.put("a");
                System.out.println("生产一个");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() {
        while (FLAGE) {
            try {
                System.out.println("消费" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFLAGE(boolean FLAGE) {
        this.FLAGE = FLAGE;
    }
}

public class ProducerConsumerBlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        final MyResource myResource = new MyResource();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myResource.prodece();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                myResource.consumer();
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);

        myResource.setFLAGE(false);

    }
}
