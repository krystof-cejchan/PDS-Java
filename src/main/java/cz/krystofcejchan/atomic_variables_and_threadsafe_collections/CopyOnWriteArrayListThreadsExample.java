package cz.krystofcejchan.atomic_variables_and_threadsafe_collections;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListThreadsExample {
    public static void main(String[] args){
        Random random = new Random();
        List<Integer> list = new CopyOnWriteArrayList<>();
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
