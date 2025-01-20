package Classes;

import java.io.Serializable;

public class TemporadaApp implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private String nombre;
    private añoTemporadaApp añoTemporada;
	private String campeon;
    private EstadoTemporada estado;  // Nuevo campo para controlar el estado de la temporada

    // Constructor
    public TemporadaApp(String nombre, añoTemporadaApp añoTemporada) {
        this.nombre = nombre;
        this.añoTemporada = añoTemporada;
        this.campeon = null; // Al principio no hay campeón
        this.estado = EstadoTemporada.Sin_Iniciar; // Inicialmente la temporada no está iniciada
    }

    // Método para obtener información de la temporada
    public String obtenerInformacion() {
        return "Temporada: " + nombre + " | Fecha: " + añoTemporada.obtenerFormatoTemporada() + 
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
    		return  nombre +" "+ estado +" "+añoTemporada;
	}
    	else {
    		return  nombre + ", campeon=" + campeon + " "+ estado +" "+añoTemporada;	
    	}
}
}