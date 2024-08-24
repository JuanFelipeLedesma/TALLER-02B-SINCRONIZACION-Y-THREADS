public class Pasarela {
    private int personasCaminandoIzqDer = 0;
    private int personasCaminandoDerIzq = 0;

    public synchronized void entrar(int id, int direccion) {
        if (direccion == 1) { // izquierda a derecha
            System.out.println("Entra la persona " + id + " en dirección: " + direccion + " =====>");
            while (personasCaminandoDerIzq > 0) { // hay personas caminando en sentido contrario
                try {
                    System.out.println("La persona " + id + " en dirección: =====> se durmió");
                    wait();
                } catch (InterruptedException e) {
                }
            }
            personasCaminandoIzqDer++;
            System.out.println("Personas caminando: " + personasCaminandoIzqDer);
        } else { // derecha a izquierda
            System.out.println("Entra la persona " + id + " en dirección: " + direccion + " <=====");
            while (personasCaminandoIzqDer > 0) { // hay personas caminando en sentido contrario
                try {
                    System.out.println("La persona " + id + " en dirección: <===== se durmió");
                    wait();
                } catch (InterruptedException e) {
                }
            }
            personasCaminandoDerIzq++;
            System.out.println("Personas caminando: " + personasCaminandoDerIzq);
        }
    }

    public synchronized void salir(int id, int direccion) {
        if (direccion == 1) { // izquierda a derecha
            personasCaminandoIzqDer--;
            System.out.println("La persona " + id + " ha salido en dirección: " + direccion + " =====>");
        } else { // derecha a izquierda
            personasCaminandoDerIzq--;
            System.out.println("La persona " + id + " ha salido en dirección: " + direccion + " <=====");
        }
        notifyAll();
    }

    public void caminar(int id) {
        try {
            System.out.println("La persona " + id + " está caminando...");
            Thread.sleep(1000); // Simula el tiempo caminando en la pasarela
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
