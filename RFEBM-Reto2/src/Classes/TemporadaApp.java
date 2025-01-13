package Classes;

import java.util.Objects;

public class TemporadaApp extends EquipoApp {
int año;
String campeon;
String participantes;
//constructor pos defecto
TemporadaApp(){
	año = 2024;
	campeon = "";
	participantes = "";
}
//Getters y Setters
public int getAño() {
	return año;
}

public void setAño(int año) {
	this.año = año;
}

public String getCampeon() {
	return campeon;
}

public void setCampeon(String campeon) {
	this.campeon = campeon;
}

public String getParticipantes() {
	return participantes;
}

public void setParticipantes(String participantes) {
	this.participantes = participantes;
}
//toString
@Override
public String toString() {
	return "TemporadaApp [año=" + año + ", campeon=" + campeon + ", participantes=" + participantes + "]";
}
//hashCode
@Override
public int hashCode() {
	return Objects.hash(año, campeon, participantes);
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
	TemporadaApp other = (TemporadaApp) obj;
	return año == other.año && Objects.equals(campeon, other.campeon)
			&& Objects.equals(participantes, other.participantes);
}

}
