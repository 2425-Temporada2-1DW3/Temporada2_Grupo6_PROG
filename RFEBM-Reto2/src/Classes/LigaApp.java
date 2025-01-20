package Classes;

import java.util.Objects;

public class LigaApp extends TemporadaApp{
String nombre;
String pais;

//constructor por defecto 
	LigaApp(){
	super("Nombre", new añoTemporadaApp(2023,2024));
	nombre = "";
	pais = "";
}
//Getters y Setters
public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}
//hashCode
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(nombre, pais);
	return result;
}
//equals
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	LigaApp other = (LigaApp) obj;
	return Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais);
}
@Override
public String toString() {
	return "LigaApp [nombre=" + nombre + ", pais=" + pais + "]";
}

}
