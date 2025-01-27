package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemporadaApp implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private añoTemporadaApp añoTemporada;
    private String campeon;
    private EstadoTemporada estado;  // Nuevo campo para controlar el estado de la temporada
    private static List<TemporadaApp> temporadas = new ArrayList<>(); // Cambiar a lista de objetos TemporadaApp
    private List<JornadaApp> jornadas; // Lista de jornadas de la temporada

    // Constructor
    public TemporadaApp(String nombre, añoTemporadaApp añoTemporada) {
        this.nombre = nombre;
        this.añoTemporada = añoTemporada;
        this.campeon = null; // Al principio no hay campeón
        this.estado = EstadoTemporada.Sin_Iniciar; // Inicialmente la temporada no está iniciada
        this.jornadas = new ArrayList<>(); // Inicializamos la lista de jornadas
        temporadas.add(this); // Agregar la temporada a la lista estática
    }

    public TemporadaApp(String nombreTemporada) {
        this.nombre = nombreTemporada;
        this.añoTemporada = null; // Si solo tienes el nombre, el año podría ser nulo
        this.estado = EstadoTemporada.Sin_Iniciar; // Inicialmente sin iniciar
        this.jornadas = new ArrayList<>(); // Inicializamos la lista de jornadas
        temporadas.add(this); // Agregar la temporada a la lista estática
    }

    // Método para agregar una jornada
    public void agregarJornada(JornadaApp jornada) {
        this.jornadas.add(jornada);
    }

    // Método para generar un archivo CSV con los partidos de la temporada
    public void generarArchivoCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("jornadas_" + nombre + ".csv"))) {
            // Escribir el encabezado
            writer.write("Jornada,Equipo Local,Equipo Visitante,Goles Local,Goles Visitante");
            writer.newLine();

            for (JornadaApp jornada : jornadas) {
                for (String[] partido : jornada.getPartido()) {  // Cambié Array a String[]
                    // Asumimos que partido es un array de tamaño 2 con el nombre de los equipos
                    String equipoLocal = partido[0];  // Accede al equipo local
                    String equipoVisitante = partido[1];  // Accede al equipo visitante
                    writer.write("Jornada " + jornada.getNumero() + "," + equipoLocal + "," + equipoVisitante + ",0,0");
                    writer.newLine();
                }
            }

            System.out.println("Archivo CSV generado correctamente para la temporada: " + nombre);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }
    // Método para obtener información de la temporada
    public String obtenerInformacion() {
        return "Temporada: " + nombre + " | Fecha: " + (añoTemporada != null ? añoTemporada.obtenerFormatoTemporada() : "N/A") +
               " | Estado: " + estado.toString() + 
               (estado == EstadoTemporada.Finalizada ? " | Campeón: " + campeon : "");
    }

    // Método para iniciar la temporada
    public void iniciarTemporada() {
        if (estado == EstadoTemporada.Sin_Iniciar) {
            this.estado = EstadoTemporada.Iniciada;
        } else {
            System.out.println("La temporada ya ha sido iniciada o finalizada.");
        }
    }

    // Método para finalizar la temporada y asignar un campeón
    public void finalizarTemporada(String campeon) {
        if (estado == EstadoTemporada.Iniciada) {
            this.campeon = campeon;
            this.estado = EstadoTemporada.Finalizada;
        } else {
            System.out.println("La temporada debe estar iniciada para finalizarla.");
        }
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public añoTemporadaApp getAñoTemporada() {
        return añoTemporada;
    }

    public void setAñoTemporada(añoTemporadaApp añoTemporada) {
        this.añoTemporada = añoTemporada;
    }

    public String getCampeon() {
        return campeon;
    }

    public void setCampeon(String campeon) {
        if (estado != EstadoTemporada.Finalizada) {
            throw new IllegalStateException("El campeón solo puede asignarse cuando la temporada está finalizada.");
        }
        this.campeon = campeon;
    }

    public EstadoTemporada getEstado() {
        return estado;
    }

    public void setEstado(EstadoTemporada estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        if (campeon == null) {
            return nombre + " " + estado + " " + añoTemporada;
        } else {
            return nombre + ", campeon=" + campeon + " " + estado + " " + añoTemporada;
        }
    }

    // Obtener todas las temporadas
    public static List<TemporadaApp> obtenerTemporadas() {
        return temporadas;
    }

    // Método para agregar una temporada a la lista estática
    public static void agregarTemporada(TemporadaApp t) {
        if (!temporadas.contains(t)) {
            temporadas.add(t);
        }
    }
}
