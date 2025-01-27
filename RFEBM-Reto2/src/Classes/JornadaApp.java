package Classes;

import java.util.ArrayList;
import java.util.List;

public class JornadaApp {
    private int numero;
    private List<String[]> partido; // Cada partido será representado como un array de String: [equipoLocal, equipoVisitante]

    // Constructor por defecto
    public JornadaApp() {
        this.numero = 0;
        this.partido = new ArrayList<>();
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<String[]> getPartido() {
        return partido;
    }

    public void setPartido(List<String[]> partido) {
        this.partido = partido;
    }

    // Método para agregar un partido
    public void agregarPartido(String equipoLocal, String equipoVisitante) {
        partido.add(new String[]{equipoLocal, equipoVisitante});
    }

    @Override
    public String toString() {
        return "JornadaApp [numero=" + numero + ", partido=" + partido + "]";
    }
}
