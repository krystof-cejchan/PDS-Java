import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class LongAdderExample {

    public static void main(String[] args){
        LongAdder counter = new LongAdder();

        long before = System.currentTimeMillis();
        List<Thread> threads =
                IntStream.range(0,100_000)
                        .mapToObj(_ -> Thread.ofVirtual().factory()
                                .newThread(() -> IntStream.range(0,1000).forEach(_ -> counter.increment()))).toList();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        long after = System.currentTimeMillis();

        System.out.println(counter.sum());
        System.out.println("AtomicInteger: " + (after - before) + " ms ");
    }
}
