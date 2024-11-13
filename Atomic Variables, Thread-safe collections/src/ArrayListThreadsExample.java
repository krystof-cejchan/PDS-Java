import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayListThreadsExample {
    public static void main(String[] args){
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
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
                        for(int i : list){
                            System.out.println(i);
                        }
                    }
                }).start();
    }
}
