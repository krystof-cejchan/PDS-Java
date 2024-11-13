package cz.krystofcejchan.thread_threadpool_forkjoin_parallelmergesort;

public class MyTask implements Runnable{
    private int id;

    public MyTask(int id){
        this.id=id;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("moje id je: "+ id);
    }
}
