public class Main {
    public static void main(String[] args) {
        Pasarela pasarela = new Pasarela();

        // Crear 10 personas con direcciones aleatorias
        for (int i = 0; i < 10; i++) {
            int direccion = (int) (Math.random() * 2); // 0 o 1 al azar
            new Persona(i, direccion, pasarela).start();
        }
    }
}
