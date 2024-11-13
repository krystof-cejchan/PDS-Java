import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SynchronizedListThreadsExample {
    public static void main(String[] args){
        Random random = new Random();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread.ofPlatform().factory()
                .newThread(() ->
                {
                    while(true) {
                        list.add(random.nextInt(Integer.MAX_VALUE));
                    }
                }).start();

        Thread.ofPlatform().factory()
                .newThread(() ->
                {
                    while(true) {
                        //synchronized (list){
                            for(int i : list){
                                System.out.println(i);
                            }
                        //}
                    }

                }).start();
    }
}
