package PUNTO2;

import java.util.List;
import java.util.LinkedList;

public class Buffer {
    // Lista de mensajes
    private List<String> buffer;
    // Capacidad del buffer
    private int size;

    public Buffer(int size) {
        this.size = size;
        this.buffer = new LinkedList<>();
    }

    public synchronized boolean hasMessages() {
        return this.buffer.size() > 0;
    }

    public synchronized void insertMessage(String message) {
        while (this.buffer.size() == this.size) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Guardar mensaje
        this.buffer.add(message);

        // Notificar a los consumidores
        notify();
    }

    public synchronized String retrieveMessage() {
        while (this.buffer.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Retirar el mensaje
        String message = this.buffer.remove(0);
        notify();
        return message;
    }
}
