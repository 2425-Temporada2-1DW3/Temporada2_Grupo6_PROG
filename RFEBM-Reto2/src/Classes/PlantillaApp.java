package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlantillaApp {
    private String equipo; // Nombre del equipo
    private String temporada; // Temporada asociada
    private List<String[]> jugadores; // Lista de jugadores (nombre, posición, etc.)

    // Constructor
    public PlantillaApp(String equipo, String temporada, List<String[]> jugadores) {
        this.equipo = equipo;
        this.temporada = temporada;
        this.jugadores = jugadores;
    }

    // Getters
    public String getEquipo() {
        return equipo;
    }

    public String getTemporada() {
        return temporada;
    }

    public List<String[]> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return "Equipo: " + equipo + ", Temporada: " + temporada + ", Jugadores: " + jugadores.size();
    }
    
    // Método estático para generar plantillas desde una tabla
    public static List<PlantillaApp> generarPlantillasDesdeTabla(JTable tabla, String temporada) {
        Map<String, List<String[]>> equipos = new HashMap<>(); // Agrupación por equipos
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        // Recorrer la tabla para agrupar jugadores por equipo
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String nombre = (String) modelo.getValueAt(i, 0);
            String apellido = (String) modelo.getValueAt(i, 1);
            String DNI = (String) modelo.getValueAt(i, 2); 
            String edad = (String) modelo.getValueAt(i, 3);
            String posicion = (String) modelo.getValueAt(i, 4); 
            String equipo = (String) modelo.getValueAt(i, 5);
            String foto = (String) modelo.getValueAt(i, 6); 
            
            // Crear un array con los datos del jugador
            String[] jugador = {nombre, apellido,DNI,edad, posicion,equipo,foto};

            // Agrupar jugadores por equipo
            equipos.computeIfAbsent(equipo, k -> new ArrayList<>()).add(jugador);
        }

        // Crear las instancias de PlantillaApp para cada equipo
        List<PlantillaApp> plantillas = new ArrayList<>();
        for (Map.Entry<String, List<String[]>> entry : equipos.entrySet()) {
            plantillas.add(new PlantillaApp(entry.getKey(), temporada, entry.getValue()));
        }

        return plantillas;
    }
}