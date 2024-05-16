package trabajodefprogramación;

import java.util.Random;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPunto() {
        this.puntos++;
    }

    public boolean esCPU() {
        return nombre.startsWith("AI");
    }

    public boolean responderPregunta(Pregunta pregunta) {
        if (esCPU()) {
            return cpuResponder(pregunta);
        } else {
            return humanoResponder(pregunta);
        }
    }

    private boolean humanoResponder(Pregunta pregunta) {
        System.out.println(pregunta.getPregunta());
        System.out.print("Respuesta: ");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine();
        return pregunta.verificarRespuesta(respuesta);
    }

    private boolean cpuResponder(Pregunta pregunta) {
        if (pregunta instanceof PreguntaMates) {
            return true;
        } else if (pregunta instanceof PreguntaLetras) {
            return false;
        } else if (pregunta instanceof PreguntaIngles) {
            Random rand = new Random();
            return rand.nextInt(4) == 0;
        }
        return false;
    }
}
