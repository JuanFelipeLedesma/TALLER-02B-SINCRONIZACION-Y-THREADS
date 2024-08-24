package PUNTO2VARIANTE;

public class Main {
    private static boolean finished = false;

    public static void main(String[] args) {
        // Tamaño del buffer
        int bufferSize = 5;
        Buffer buffer = new Buffer(bufferSize);

        // Crear e iniciar tres productores
        Producer producer1 = new Producer(1, buffer, 10);
        Producer producer2 = new Producer(2, buffer, 10);
        Producer producer3 = new Producer(3, buffer, 10);

        producer1.start();
        producer2.start();
        producer3.start();

        // Crear e iniciar dos consumidores
        Consumer consumer1 = new Consumer(1, buffer);
        Consumer consumer2 = new Consumer(2, buffer);
        Consumer consumer3 = new Consumer(3, buffer);

        consumer1.start();
        consumer2.start();
        consumer3.start();

        // Esperar a que los productores terminen
        try {
            producer1.join();
            producer2.join();
            producer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Indicar que la producción ha terminado
        finished = true;

        // Despertar a los consumidores por si están esperando
        synchronized (buffer) {
            buffer.notifyAll();
        }
    }

    public static boolean isFinished() {
        return finished;
    }
}
