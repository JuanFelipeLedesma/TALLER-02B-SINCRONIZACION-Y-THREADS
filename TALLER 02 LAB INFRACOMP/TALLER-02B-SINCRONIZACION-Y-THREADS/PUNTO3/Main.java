package PUNTO3;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        // Crear una matriz de ejemplo
        int[] array = { 3, 5, 9, 1, 8, 7, 2, 4, 6, 10 };

        // Número de threads
        int numThreads = 3;

        // Crear una barrera que se activa cuando todos los threads han llegado
        CyclicBarrier barrier = new CyclicBarrier(numThreads, new Runnable() {
            @Override
            public void run() {
                // Este código se ejecutará cuando todos los threads hayan terminado
                System.out.println("El valor máximo en la matriz es: " + MaximoMatriz.getMax());
            }
        });

        int segmentSize = array.length / numThreads;

        // Crear y comenzar los threads
        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentSize;
            int end = (i == numThreads - 1) ? array.length : start + segmentSize;
            new MaximoMatriz(array, start, end, barrier).start();
        }
    }
}
