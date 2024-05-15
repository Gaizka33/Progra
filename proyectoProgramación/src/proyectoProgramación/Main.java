package proyectoProgramación;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Jugadores> listaJugadores = new ArrayList<>();
		Humanos h1 = new Humanos("pepe");
		listaJugadores.add(h1); // Agregar el jugador inicial a la lista de jugadores
		Scanner abielto = new Scanner(System.in);

		System.out.println("1. Jugar Partida");
		System.out.println("2. Ranking");
		System.out.println("3. Histórico");
		System.out.println("4. Jugadores");
		System.out.println("5. Salir ");
		int i = abielto.nextInt();

		switch (i) {
		case 4:
			System.out.println("1. Ver Jugadores");
			System.out.println("2. Añadir Jugador");
			System.out.println("3. Eliminar Jugador");
			System.out.println("4. Volver");

			int j = abielto.nextInt();

			switch (j) {
			case 1:
				// Mostrar jugadores
				System.out.println("Lista de jugadores:");
				for (Jugadores jugador : listaJugadores) {
					System.out.println(jugador.getNombre());
				}
				break;
			case 2:
				// Añadir jugador
				System.out.println("Dame El Nombre");
				String nombre = abielto.next();
				boolean existe = false;

				for (Jugadores jugador : listaJugadores) {
					if (jugador.getNombre().equalsIgnoreCase(nombre)) {
						existe = true;
						break;
					}
				}

				if (existe) {
					System.out.println("Ya existe un jugador con ese nombre. Inténtalo de nuevo");
					break;
				} else {
					listaJugadores.add(new Humanos(nombre));
					System.out.println("Jugador añadido correctamente");
				}
				break;
			case 3:
				// Eliminar jugador
				System.out.println("Dame El Nombre del Jugador a Eliminar");
				String nombreEliminar = abielto.next();
				boolean eliminado = false;

				for (Jugadores jugador : listaJugadores) {
					if (jugador.getNombre().equalsIgnoreCase(nombreEliminar)) {
						listaJugadores.remove(jugador);
						eliminado = true;
						System.out.println("Jugador eliminado correctamente");
						break;
					}
				}

				if (!eliminado) {
					System.out.println("No se encontró ningún jugador con ese nombre.");
				}
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}

		System.out.println("Lista de jugadores:");
		for (Jugadores jugador : listaJugadores) {
			System.out.println(jugador.getNombre());
		}

		CPU c = new CPU();
		CPU c1 = new CPU();
		CPU c2 = new CPU();

		System.out.println(c.getNombre());
		System.out.println(c1.getNombre());
		System.out.println(c2.getNombre());

		System.out.println(h1.getNombre() + " " + h1.getPuntos());
	}
}
