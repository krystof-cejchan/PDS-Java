import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerExample {

    public static void main(String[] args){
        AtomicInteger counter = new AtomicInteger();

        long beforePar = System.currentTimeMillis();
        List<Thread> threads =
                IntStream.range(0,100_000)
                        .mapToObj(_ -> Thread.ofVirtual().factory()
                                .newThread(() -> IntStream.range(0,1000).forEach(_ -> counter.incrementAndGet()))).toList();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        long after = System.currentTimeMillis();

        System.out.println(counter.get());
        System.out.println("AtomicInteger: " + (after - beforePar) + " ms ");
    }
}
