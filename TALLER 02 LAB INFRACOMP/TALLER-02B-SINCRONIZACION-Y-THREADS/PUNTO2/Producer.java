package PUNTO2;

public class Producer extends Thread {
    private int id;
    private Buffer buf;
    private int times;

    public Producer(int id, Buffer buf, int times) {
        this.id = id;
        this.buf = buf;
        this.times = times;
    }

    private void sendMessage(int i) {
        this.buf.insertMessage(
                "El thread productor: " + this.id + " saluda con el mensaje: " +
                        i + " de: " + this.times + " mensajes");
    }

    @Override
    public void run() {
        for (int i = 0; i < this.times; i++) {
            this.sendMessage(i);
        }
    }
}
