package cz.krystofcejchan.atomic_variables_and_threadsafe_collections;

import java.util.List;
import java.util.stream.LongStream;

public class ParallelStreamsExample {
    public static void main(String[] args){
        List<Long> numbers = LongStream.rangeClosed(1, 100000000).boxed().toList();

        long beforeSeq = System.currentTimeMillis();
        long squareSumSeq = numbers.stream().map(i -> i * i).mapToLong(Long::intValue).sum();
        long afterSeq = System.currentTimeMillis();

        long beforePar = System.currentTimeMillis();
        long squareSumPar = numbers.stream().parallel().map(i -> i * i).mapToLong(Long::intValue).sum();
        long afterPar = System.currentTimeMillis();

        System.out.println("Sequential: " + (afterSeq - beforeSeq) + " ms Parallel: " + (afterPar - beforePar) + " ms");
    }
}
