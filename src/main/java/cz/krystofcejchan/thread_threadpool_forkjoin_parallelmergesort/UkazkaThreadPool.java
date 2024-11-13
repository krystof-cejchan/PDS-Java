package cz.krystofcejchan.thread_threadpool_forkjoin_parallelmergesort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UkazkaThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for(int i =0;i<5;i++){
            pool.submit(new MyTask(i));
        }
        pool.shutdown();
        while (!pool.isTerminated()){

        }
        System.out.println("vsechna vlakna skoncila");
    }
}
