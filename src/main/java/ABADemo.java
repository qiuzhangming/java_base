import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@AllArgsConstructor
class User {
    private String name;
    private int age;
}

public class ABADemo {

    //static AtomicInteger atomicInteger = new AtomicInteger(100);

    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<Integer>(100,1);

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                //System.out.println(atomicInteger.compareAndSet(100, 200) + "\t当前值：" + atomicInteger.get());
                //System.out.println(atomicInteger.compareAndSet(200, 100) + "\t当前值：" + atomicInteger.get());

                System.out.println("===========================");
                int stamp = stampedReference.getStamp();
                Integer reference = stampedReference.getReference();
                System.out.println(stampedReference.compareAndSet(reference, 200, stamp, stamp+1));

                stamp = stampedReference.getStamp();
                reference = stampedReference.getReference();

                System.out.println(stampedReference.compareAndSet(reference, 100, stamp, stamp+1));


                System.out.println(stampedReference.getReference());
                System.out.println(stampedReference.getStamp());

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===========================");

                //System.out.println(Thread.currentThread().getName()+"\t" +atomicInteger.compareAndSet(100, 2019) + "\t当前值：" + atomicInteger.get());

                System.out.println(stampedReference.compareAndSet(100, 200, 1, 2));

                System.out.println(stampedReference.getReference());
                System.out.println(stampedReference.getStamp());

            }
        }).start();
    }


    private static void myAtomicReference() {
        User user1 = new User("zhangsan", 23);
        User user2 = new User("lisi", 24);


        AtomicReference<User> atomicReference = new AtomicReference<User>();

        atomicReference.set(user1);


        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t 当前类容：" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(user1, user2) + "\t 当前类容：" + atomicReference.get());
    }

}
