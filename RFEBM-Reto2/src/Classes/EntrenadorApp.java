package Classes;

import java.util.Objects;

public class EntrenadorApp extends EquipoApp {
String nombre;
String pais;

//construcntor por defecto 
EntrenadorApp(){
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
	return Objects.hash(nombre, pais);
}
//equals
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	EntrenadorApp other = (EntrenadorApp) obj;
	return Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais);
}
@Override
public String toString() {
	return "EntrenadorApp [nombre=" + nombre + ", pais=" + pais + "]";
}

}
