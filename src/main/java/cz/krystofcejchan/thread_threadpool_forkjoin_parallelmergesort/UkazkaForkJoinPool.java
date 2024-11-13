package cz.krystofcejchan.thread_threadpool_forkjoin_parallelmergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class UkazkaForkJoinPool extends RecursiveAction {
    private final int[] array;
    private final int left;
    private final int right;
    private static final int THRESHOLD = 1;  // Práh pro přímé řazení

    public UkazkaForkJoinPool(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (right - left <= THRESHOLD) {
            // Pokud je velikost pole malá, použijeme sekvenční řazení
            Arrays.sort(array, left, right + 1);
        } else {
            // Rozdělení pole na dvě poloviny
            int mid = (left + right) / 2;

            // Vytvoření dvou úkolů pro levou a pravou část pole
            UkazkaForkJoinPool leftTask = new UkazkaForkJoinPool(array, left, mid);
            UkazkaForkJoinPool rightTask = new UkazkaForkJoinPool(array, mid + 1, right);

            // Paralelní spuštění obou částí
            invokeAll(leftTask, rightTask);

            // Sjednocení (merge) obou seřazených částí
            merge(array, left, mid, right);
        }
    }

    // Metoda pro sjednocení dvou seřazených částí pole
    private void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        // Vytvoření instance ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Spuštění paralelního merge sortu
        UkazkaForkJoinPool task = new UkazkaForkJoinPool(array, 0, array.length - 1);
        pool.invoke(task);

        // Výpis seřazeného pole
        System.out.println("Seřazené pole: " + Arrays.toString(array));
    }
}
