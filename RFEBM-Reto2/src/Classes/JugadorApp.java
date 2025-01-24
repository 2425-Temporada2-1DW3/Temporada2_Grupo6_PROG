package Classes;

import java.util.Objects;

public class JugadorApp {
String nombre;
int edad;
String posicion;
String foto;

JugadorApp(){
	nombre = "";
	edad = 0;
	posicion = "";
	foto = "";
}
//Getters y Setters
public String getNombre() {
	return nombre;
}



public void setNombre(String nombre) {
	this.nombre = nombre;
}



public int getEdad() {
	return edad;
}



public void setEdad(int edad) {
	this.edad = edad;
}



public String getPosicion() {
	return posicion;
}



public void setPosicion(String posicion) {
	this.posicion = posicion;
}



public String getFoto() {
	return foto;
}



public void setFoto(String foto) {
	this.foto = foto;
}


//toString
@Override
public String toString() {
	return "JugadorApp [nombre=" + nombre + ", edad=" + edad + ", posicion=" + posicion + ", foto=" + foto + "]";
}

@Override
public int hashCode() {
	return Objects.hash(edad, foto, nombre, posicion);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	JugadorApp other = (JugadorApp) obj;
	return edad == other.edad && Objects.equals(foto, other.foto) && Objects.equals(nombre, other.nombre)
			&& Objects.equals(posicion, other.posicion);
}

}
