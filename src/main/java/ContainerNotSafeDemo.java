import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * java.util.ConcurrentModificationException
 *
 *
 */
public class ContainerNotSafeDemo {



    public static void main(String[] args) {
//        final List<String> list = new ArrayList<>();
//        final List<String> list = new Vector<>();
//        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        final List<String> list = new CopyOnWriteArrayList<>();

        Set<String> set = new CopyOnWriteArraySet<>();


        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,6));
                    System.out.println(list);
                }
            }).start();
        }
    }


}
