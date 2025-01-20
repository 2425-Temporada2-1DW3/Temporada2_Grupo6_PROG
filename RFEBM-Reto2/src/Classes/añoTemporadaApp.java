package Classes;

import java.io.Serializable;

public class añoTemporadaApp implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private int añoInicio;
    private int añoFinal;

    // Constructor
    public añoTemporadaApp(int añoInicio, int añoFinal) {
        if (añoInicio >= añoFinal) {
            throw new IllegalArgumentException("El año de inicio debe ser menor que el año final.");
        }
        this.añoInicio = añoInicio;
        this.añoFinal = añoFinal;
    }

    // Método para obtener la representación formateada
    public String obtenerFormatoTemporada() {
        return añoInicio + "-" + añoFinal;
    }

    // Getters y setters
    public int getAñoInicio() {
        return añoInicio;
    }

    public void setAñoInicio(int añoInicio) {
        if (añoInicio >= this.añoFinal) {
            throw new IllegalArgumentException("El año de inicio debe ser menor que el año final.");
        }
        this.añoInicio = añoInicio;
    }

    public int getAñoFinal() {
        return añoFinal;
    }

    public void setAñoFinal(int añoFinal) {
        if (this.añoInicio >= añoFinal) {
            throw new IllegalArgumentException("El año de inicio debe ser menor que el año final.");
        }
        this.añoFinal = añoFinal;
    }

	@Override
	public String toString() {
		return + añoInicio + "-" + añoFinal;
	}
    
}