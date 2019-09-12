import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Classname CallableDemo
 * @Description TODO
 * @Date 2019/9/12 14:01
 * @Created by joe
 */

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "callable";
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();

        FutureTask<String> futureTask = new FutureTask<>(myThread);
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }
}
