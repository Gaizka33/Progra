package trabajodefprogramación;

import java.io.*;
import java.util.*;

public class GestorDeArchivos {
    private static final String ARCHIVO_JUGADORES = "jugadores.txt";
    private static final String ARCHIVO_RANKING = "ranking.txt";
    private static final String ARCHIVO_HISTORICO = "historico.txt";

    public static List<Jugador> cargarJugadores() {
        List<Jugador> jugadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_JUGADORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                jugadores.add(new Jugador(linea));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de jugadores.");
        }
        return jugadores;
    }

    public static void guardarJugadores(List<Jugador> jugadores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_JUGADORES))) {
            for (Jugador jugador : jugadores) {
                pw.println(jugador.getNombre());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de jugadores.");
        }
    }

    public static List<Jugador> cargarRanking() {
        List<Jugador> ranking = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_RANKING))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                Jugador jugador = new Jugador(partes[0]);
                for (int i = 0; i < Integer.parseInt(partes[1]); i++) {
                    jugador.sumarPunto();
                }
                ranking.add(jugador);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de ranking.");
        }
        return ranking;
    }

    public static void guardarRanking(List<Jugador> ranking) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_RANKING))) {
            for (Jugador jugador : ranking) {
                pw.println(jugador.getNombre() + " " + jugador.getPuntos());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de ranking.");
        }
    }

    public static List<String> cargarHistorico() {
        List<String> historico = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HISTORICO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                historico.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de histórico.");
        }
        return historico;
    }

    public static void guardarHistorico(List<String> historico) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_HISTORICO))) {
            for (String entrada : historico) {
                pw.println(entrada);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de histórico.");
        }
    }
}
