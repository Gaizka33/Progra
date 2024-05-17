package trabajodefprogramación;

public class Main {
    public static void main(String[] args) {
        // Cargar datos de archivos
        PreguntaLetras.cargarPalabras("src\\diccionario.txt");
        PreguntaIngles.cargarPreguntas("src\\ingles.txt");

        // Verificar si las listas están vacías
        if (PreguntaLetras.palabras.isEmpty()) {
            System.out.println("Error: la lista de palabras está vacía.");
            return;
        }

        if (PreguntaIngles.preguntas.isEmpty()) {
            System.out.println("Error: la lista de preguntas está vacía.");
            return;
        }

        GestorDeJuego gestorDeJuego = new GestorDeJuego();
        gestorDeJuego.iniciar();
    }
}
