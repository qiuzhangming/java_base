import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

        System.out.println(atomicInteger.compareAndSet(0, 2) + "\tcurrent data:" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(0, 2) + "\tcurrent data:" + atomicInteger.get());



    }


}
