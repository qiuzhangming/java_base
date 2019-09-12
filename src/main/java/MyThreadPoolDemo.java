import java.util.concurrent.*;

/**
 * @Classname MyThreadPoolDemo
 * @Description TODO
 * @Date 2019/9/12 14:40
 * @Created by joe
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //System.out.println(Runtime.getRuntime().availableProcessors());

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //ExecutorService executorService2 = Executors.newCachedThreadPool();

        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "ok";
            }
        };

//        Future future = executorService.submit(runnable);
//        System.out.println(future.get());

//        Future<String> submit = executorService.submit(callable);
//        System.out.println(submit.get());


        String resule = null;
        Future<String> submit1 = executorService.submit(runnable, resule);
        System.out.println(submit1.get());


        executorService.shutdown();

    }
}
