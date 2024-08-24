public class Persona extends Thread {
    private int id;
    private int direccion;
    private Pasarela pasarela;

    public Persona(int id, int direccion, Pasarela pasarela) {
        this.id = id;
        this.direccion = direccion;
        this.pasarela = pasarela;
    }

    @Override
    public void run() {
        pasarela.entrar(id, direccion);
        pasarela.caminar(id);
        pasarela.salir(id, direccion);
    }
}
