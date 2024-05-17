package trabajodefprogramación;


public class CPU extends Jugador {
    private static int contadorCPUs;

    public CPU() {
        super(nombreCPUS());
    }

    private static String nombreCPUS() {
        contadorCPUs++;
        return "AI" + contadorCPUs;
    }
}