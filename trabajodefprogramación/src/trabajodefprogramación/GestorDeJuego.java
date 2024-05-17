package trabajodefprogramación;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDeJuego {
    private List<Jugador> jugadores;
    private Ranking ranking;
    private Historico historico;
    private List<Jugador> jugadoresPartida;

    public GestorDeJuego() {
        this.jugadores = GestorDeArchivos.cargarJugadores();
        this.ranking = new Ranking();
        this.historico = new Historico();
        this.jugadoresPartida = new ArrayList<>();

        for (Jugador jugador : GestorDeArchivos.cargarRanking()) {
            ranking.agregarPuntuacion(jugador);
        }
        for (String partida : GestorDeArchivos.cargarHistorico()) {
            historico.agregarPartida(partida);
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            switch (opcion) {
                case 1:
                    configurarPartida(scanner);
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
                    guardarDatos();
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

    private void configurarPartida(Scanner scanner) {
        jugadoresPartida.clear();
        System.out.println("Ingrese el número de jugadores humanos (0-" + jugadores.size() + "): ");
        int numJugadoresHumanos = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        for (int i = 0; i < numJugadoresHumanos; i++) {
            listarJugadores();
            System.out.println("Seleccione el número del jugador humano " + (i + 1) + ":");
            int indiceJugador = scanner.nextInt() - 1;
            scanner.nextLine(); // Consumir la nueva línea

            if (indiceJugador >= 0 && indiceJugador < jugadores.size()) {
                jugadoresPartida.add(jugadores.get(indiceJugador));
            } else {
                System.out.println("Índice de jugador no válido. Inténtalo de nuevo.");
                i--; // Volver a intentar agregar este jugador
            }
        }

        System.out.println("Ingrese el número de jugadores IA: ");
        int numIAs = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        for (int i = 0; i < numIAs; i++) {
            jugadoresPartida.add(new CPU());
        }

        jugarPartida(scanner);
    }

    private void jugarPartida(Scanner scanner) {
        if (jugadoresPartida.isEmpty()) {
            System.out.println("No hay jugadores seleccionados para la partida. Añada jugadores primero.");
            return;
        }

        System.out.println("Ingrese el número de rondas (3, 5, 10, 20): ");
        int rondas = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

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
            scanner.nextLine(); // Consumir la nueva línea
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
                    listarJugadoresPartida();
                    break;
                case 5:
                    return;
            }
        }
    }

    private void mostrarMenuJugadores() {
        System.out.println("1. Ver jugadores");
        System.out.println("2. Añadir jugador");
        System.out.println("3. Eliminar jugador");
        System.out.println("4. Ver jugadores en la partida");
        System.out.println("5. Volver");
    }

    private void listarJugadores() {
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ". " + jugadores.get(i).getNombre());
        }
    }

    private void agregarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del nuevo jugador: ");
        String nombreJugador = scanner.nextLine();
        if (nombreJugador.contains(" ")) {
            System.out.println("No se permiten espacios en el nombre.");
        } else {
            boolean existe = jugadores.stream().anyMatch(j -> j.getNombre().equals(nombreJugador));
            if (existe) {
                System.out.println("El jugador ya existe.");
            } else {
                Jugador nuevoJugador = new Jugador(nombreJugador);
                jugadores.add(nuevoJugador);
                System.out.println("Jugador añadido.");
            }
        }
    }

    private void eliminarJugador(Scanner scanner) {
        System.out.println("Ingrese el nombre del jugador a eliminar: ");
        String nombreJugador = scanner.nextLine();
        jugadores.removeIf(jugador -> jugador.getNombre().equals(nombreJugador));
        jugadoresPartida.removeIf(jugador -> jugador.getNombre().equals(nombreJugador));
        System.out.println("Jugador eliminado.");
    }

    private void listarJugadoresPartida() {
        for (Jugador jugador : jugadoresPartida) {
            System.out.println(jugador.getNombre());
        }
    }

    private void guardarDatos() {
        GestorDeArchivos.guardarJugadores(jugadores);
        GestorDeArchivos.guardarRanking(ranking.getRanking());
        GestorDeArchivos.guardarHistorico(historico.getHistorico());
    }
}