package Classes;

import java.util.Objects;

public class EquipoApp extends JugadorApp{
String nombre;
String estado;
String ciudad;
String Escudo;

//cosntructor pod defecto
EquipoApp(){
nombre = "";
estado = "";
ciudad = "";
Escudo = "";

}
//Getters y Setters
public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public String getCiudad() {
	return ciudad;
}

public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}

public String getEscudo() {
	return Escudo;
}

public void setEscudo(String escudo) {
	Escudo = escudo;
}
//hashCode
@Override
public int hashCode() {
	return Objects.hash(Escudo, ciudad, estado, nombre);
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
	EquipoApp other = (EquipoApp) obj;
	return Objects.equals(Escudo, other.Escudo) && Objects.equals(ciudad, other.ciudad)
			&& Objects.equals(estado, other.estado) && Objects.equals(nombre, other.nombre);
}
@Override
public String toString() {
	return "EquipoApp [nombre=" + nombre + ", estado=" + estado + ", ciudad=" + ciudad + ", Escudo=" + Escudo + "]";
}

}
