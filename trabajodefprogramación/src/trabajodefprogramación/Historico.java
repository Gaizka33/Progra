package trabajodefprogramación;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private List<String> historico;

    public Historico() {
        this.historico = new ArrayList<>();
    }

    public void agregarPartida(String resultadoPartida) {
        historico.add(resultadoPartida);
    }

    public void mostrar() {
        for (String partida : historico) {
            System.out.println(partida);
        }
    }

    public List<String> getHistorico() {
        return historico;
    }
}