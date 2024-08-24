package PUNTO3;

import java.util.concurrent.CyclicBarrier;

public class MaximoMatriz extends Thread {
    private int[] array;
    private int start;
    private int end;
    private CyclicBarrier barrier;
    private static int max = Integer.MIN_VALUE;

    public MaximoMatriz(int[] array, int start, int end, CyclicBarrier barrier) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int localMax = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (array[i] > localMax) {
                localMax = array[i];
            }
        }

        synchronized (MaximoMatriz.class) {
            if (localMax > max) {
                max = localMax;
            }
        }

        System.out.println("Thread " + this.getName() + " encontró el máximo local: " + localMax);

        try {
            barrier.await(); // Espera a que todos los threads terminen
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getMax() {
        return max;
    }
}
