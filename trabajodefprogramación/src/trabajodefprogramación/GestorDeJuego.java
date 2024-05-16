package trabajodefprogramación;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDeJuego {
    private List<Jugador> jugadores;
    private Ranking ranking;
    private Historico historico;

    public GestorDeJuego() {
        this.jugadores = GestorDeArchivos.cargarJugadores();
        this.ranking = new Ranking();
        for (Jugador jugador : GestorDeArchivos.cargarRanking()) {
            ranking.agregarPuntuacion(jugador);
        }
        this.historico = new Historico();
        for (String partida : GestorDeArchivos.cargarHistorico()) {
            historico.agregarPartida(partida);
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea
            switch (opcion) {
                case 1:
                    jugarPartida(scanner);
                    break;
                case 2:
                    ranking.mostrar();
                    break;
                case 3:
                    historico.mostrar();
                    break;
                case 4:
                    gestionarJugadores(scanner);
                    break;
                case 5:
                    GestorDeArchivos.guardarJugadores(jugadores);
                    GestorDeArchivos.guardarRanking(ranking.getRanking());
                    GestorDeArchivos.guardarHistorico(historico.getHistorico());
                    System.exit(0);
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("1. Jugar Partida");
        System.out.println("2. Ranking");
        System.out.println("3. Histórico");
        System.out.println("4. Jugadores");
        System.out.println("5. Salir");
    }

    private void jugarPartida(Scanner scanner) {
        System.out.println("Ingrese el número de jugadores (1-6): ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        List<Jugador> jugadoresPartida = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            System.out.println("Ingrese el nombre del jugador " + (i + 1) + " (use 'AI1', 'AI2', etc. para CPU): ");
            String nombreJugador = scanner.nextLine();
            jugadoresPartida.add(new Jugador(nombreJugador));
        }

        System.out.println("Ingrese el número de rondas (3, 5, 10, 20): ");
        int rondas = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        Juego juego = new Juego(jugadoresPartida, rondas);
        juego.iniciar();

        // Registrar resultados en el ranking y el histórico
        for (Jugador jugador : jugadoresPartida) {
            ranking.agregarPuntuacion(jugador);
        }
        StringBuilder resultadoPartida = new StringBuilder();
        for (Jugador jugador : jugadoresPartida) {
            resultadoPartida.append(jugador.getNombre()).append(" ").append(jugador.getPuntos()).append(" ");
        }
        historico.agregarPartida(resultadoPartida.toString().trim());
    }

    private void gestionarJugadores(Scanner scanner) {
        while (true) {
            mostrarMenuJugadores();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea
            switch (opcion) {
                case 1:
                    listarJugadores();
                    break;
                case 2:
                    agregarJugador(scanner);
                    break;
                case 3:
                    eliminarJugador(scanner);
                    break;
                case 4:
                    return;
            }
        }
    }

    private void mostrarMenuJugadores() {
        System.out.println("1. Ver jugadores");
        System.out.println("2. Añadir jugador");
        System.out.println("3. Eliminar jugador");
        System.out.println("4. Volver");
    }

    private void listarJugadores() {
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre());
        }
    }

    private void agregarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del nuevo jugador: ");
        String nombreJugador = scanner.nextLine();
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equals(nombreJugador)) {
                System.out.println("El jugador ya existe.");
                return;
            }
        }
        jugadores.add(new Jugador(nombreJugador));
        System.out.println("Jugador añadido.");
    }

    private void eliminarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador a eliminar: ");
        String nombreJugador = scanner.nextLine();
        jugadores.removeIf(jugador -> jugador.getNombre().equals(nombreJugador));
        System.out.println("Jugador eliminado.");
    }
}
