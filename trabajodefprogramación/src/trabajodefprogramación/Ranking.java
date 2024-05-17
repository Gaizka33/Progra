package trabajodefprogramación;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {
    private List<Jugador> ranking;

    public Ranking() {
        this.ranking = new ArrayList<>();
    }

    public void agregarPuntuacion(Jugador jugador) {
        for (Jugador j : ranking) {
            if (j.getNombre().equals(jugador.getNombre())) {
                j.sumarPuntos(jugador.getPuntos());
                return;
            }
        }
        ranking.add(jugador);
    }

    public void mostrar() {
        ranking.sort(Comparator.comparingInt(Jugador::getPuntos).reversed());
        for (Jugador jugador : ranking) {
            System.out.println(jugador.getNombre() + ": " + jugador.getPuntos() + " puntos");
        }
    }

    public List<Jugador> getRanking() {
        return ranking;
    }
}


