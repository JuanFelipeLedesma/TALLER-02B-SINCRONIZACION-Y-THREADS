package PUNTO2VARIANTE;

public class Consumer extends Thread {
    private int id;
    private Buffer buf;

    public Consumer(int id, Buffer buf) {
        this.id = id;
        this.buf = buf;
    }

    private void printMessage(String message) {
        // Mostrar el mensaje que obtuvimos
        String prot = "El consumidor %d recupero el siguiente mensaje: %s";
        System.out.println(String.format(prot, this.id, message));
    }

    @Override
    public void run() {
        // Vamos a emplear una bandera para frenar el proceso
        while (!Main.isFinished() || this.buf.hasMessages()) {
            // Retirar el mensaje
            String message = this.buf.retrieveMessage();
            if (message == null) {
                return;
            }
            // Mostrar el mensaje
            this.printMessage(message);
        }
    }
}
