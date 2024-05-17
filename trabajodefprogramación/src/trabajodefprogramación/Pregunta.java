package trabajodefprogramación;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public abstract class Pregunta {
    protected String pregunta;
    protected String respuestaCorrecta;

    public abstract void generar();

    public String getPregunta() {
        return pregunta;
    }

    public boolean verificarRespuesta(String respuesta) {
        if (this instanceof PreguntaIngles) {
            switch (respuesta) {
                case "A":
                    return respuestaCorrecta.equalsIgnoreCase("A");
                case "B":
                    return respuestaCorrecta.equalsIgnoreCase("B");
                case "C":
                    return respuestaCorrecta.equalsIgnoreCase("C");
                case "D":
                    return respuestaCorrecta.equalsIgnoreCase("D");
                default:
                    return false;
            }
        }
        return respuestaCorrecta.equalsIgnoreCase(respuesta);
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}

class PreguntaMates extends Pregunta {
    @Override
    public void generar() {
        Random rand = new Random();
        int num1 = rand.nextInt(11) + 2;
        int num2 = rand.nextInt(11) + 2;
        pregunta = num1 + " + " + num2;
        respuestaCorrecta = String.valueOf(num1 + num2);
    }
}

class PreguntaLetras extends Pregunta {
    static List<String> palabras = new ArrayList<>();

    public static void cargarPalabras(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de diccionario.");
        }
    }

    @Override
    public void generar() {
        if (palabras.isEmpty()) {
            throw new IllegalArgumentException("La lista de palabras está vacía.");
        }
        Random rand = new Random();
        String palabra = palabras.get(rand.nextInt(palabras.size()));
        int letrasOcultas = palabra.length() / 3;
        StringBuilder palabraOculta = new StringBuilder(palabra);

        for (int i = 0; i < letrasOcultas; i++) {
            int indice = rand.nextInt(palabra.length());
            palabraOculta.setCharAt(indice, '*');
        }

        pregunta = "Adivina la palabra: " + palabraOculta;
        respuestaCorrecta = palabra;
    }
}

class PreguntaIngles extends Pregunta {
    static List<String[]> preguntas = new ArrayList<>();

    public static void cargarPreguntas(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] preguntaCompleta = new String[5];
                preguntaCompleta[0] = linea;
                for (int i = 1; i < 5; i++) {
                    preguntaCompleta[i] = br.readLine();
                }
                preguntas.add(preguntaCompleta);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de preguntas en inglés.");
        }
    }

    @Override
    public void generar() {
        if (preguntas.isEmpty()) {
            throw new IllegalArgumentException("La lista de preguntas está vacía.");
        }
        Random rand = new Random();
        String[] preguntaSeleccionada = preguntas.get(rand.nextInt(preguntas.size()));
        pregunta = preguntaSeleccionada[0];
        respuestaCorrecta = preguntaSeleccionada[1];

        List<String> opciones = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            opciones.add(preguntaSeleccionada[i]);
        }
        Collections.shuffle(opciones);

        pregunta += "\nA) " + opciones.get(0) + "\nB) " + opciones.get(1) + "\nC) " + opciones.get(2) + "\nD) " + opciones.get(3);
    }
}