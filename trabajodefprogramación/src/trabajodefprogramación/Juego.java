package trabajodefprogramación;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Juego {
    private List<Jugador> jugadores;
    private List<Pregunta> preguntas;
    private int rondas;

    public Juego(List<Jugador> jugadores, int rondas) {
        this.jugadores = jugadores;
        this.rondas = rondas;
        this.preguntas = new ArrayList<>();
    }

    public void iniciar() {
        Collections.shuffle(jugadores);
        for (int i = 0; i < rondas; i++) {
            jugarRonda();
        }
        mostrarResultados();
    }

    private void jugarRonda() {
        for (Jugador jugador : jugadores) {
            Pregunta pregunta = generarPreguntaAleatoria();
            preguntas.add(pregunta);
            System.out.println("Pregunta para " + jugador.getNombre() + ":");
            boolean correcta = jugador.responderPregunta(pregunta);
            if (correcta) {
                jugador.sumarPunto();
                System.out.println("¡Correcto!");
            } else {
                System.out.println("Incorrecto. La respuesta correcta era: " + pregunta.getRespuestaCorrecta());
            }
        }
    }

    private Pregunta generarPreguntaAleatoria() {
        Random rand = new Random();
        int tipoPregunta = rand.nextInt(3);
        Pregunta pregunta = null;
        switch (tipoPregunta) {
            case 0:
                pregunta = new PreguntaMates();
                break;
            case 1:
                pregunta = new PreguntaLetras();
                break;
            case 2:
                pregunta = new PreguntaIngles();
                break;
        }
        pregunta.generar();
        return pregunta;
    }

    private void mostrarResultados() {
        System.out.println("Resultados finales:");
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntos() + " puntos");
        }
    }
}
