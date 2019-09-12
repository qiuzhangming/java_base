import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname BlockingQueueDemo
 * @Description TODO
 * @Date 2019/9/12 12:04
 * @Created by joe
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("插入");
                        synchronousQueue.put(UUID.randomUUID().toString().substring(0,6));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("取出：\t" + synchronousQueue.take());
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private static void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
//        arrayBlockingQueue.add("a");//异常

        System.out.println(arrayBlockingQueue.element());

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
//        System.out.println(arrayBlockingQueue.remove());

        System.out.println("========================================");


        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        System.out.println("========================================");


        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
//        arrayBlockingQueue.put("a");//阻塞


        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
//        System.out.println(arrayBlockingQueue.take());

        System.out.println("===================offer=====================");


        System.out.println(arrayBlockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("d", 2, TimeUnit.SECONDS));

        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2, TimeUnit.SECONDS));
    }


}
