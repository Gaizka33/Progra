package proyectoProgramación;

public class CPU extends Jugadores {
	private static int contadorCPUs = 0;

	public CPU() {
		super(nombreCPUS(), 0);
	}

	private static String nombreCPUS() {
		contadorCPUs++;
		return "AI" + contadorCPUs;
	}
	
	

}
