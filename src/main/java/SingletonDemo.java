/**
 * 单例模式
 */

public class SingletonDemo {

    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t构造方法。");
    }


    public static SingletonDemo getInstance() {

//        if (singletonDemo == null) {
//            singletonDemo = new SingletonDemo();
//        }
//        return singletonDemo;


        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }

        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                public void run() {
                    SingletonDemo.getInstance();
                }
            }).start();
        }
    }


}
