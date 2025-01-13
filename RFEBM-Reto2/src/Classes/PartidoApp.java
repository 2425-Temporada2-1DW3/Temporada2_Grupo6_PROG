package Classes;

import java.util.Objects;

public class PartidoApp extends JornadaApp{
String EquipoLocal;
String EquipoVisitante;
String Resultado;
//Constructor por defecto
PartidoApp(){
	EquipoLocal = "";
	EquipoVisitante = "";
	Resultado = "";
}
//Getters y Setters
public String getEquipoLocal() {
	return EquipoLocal;
}
public void setEquipoLocal(String equipoLocal) {
	EquipoLocal = equipoLocal;
}
public String getEquipoVisitante() {
	return EquipoVisitante;
}
public void setEquipoVisitante(String equipoVisitante) {
	EquipoVisitante = equipoVisitante;
}
public String getResultado() {
	return Resultado;
}
public void setResultado(String resultado) {
	Resultado = resultado;
}
//hashCode
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(EquipoLocal, EquipoVisitante, Resultado);
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
	PartidoApp other = (PartidoApp) obj;
	return Objects.equals(EquipoLocal, other.EquipoLocal) && Objects.equals(EquipoVisitante, other.EquipoVisitante)
			&& Objects.equals(Resultado, other.Resultado);
}
@Override
public String toString() {
	return "PartidoApp [EquipoLocal=" + EquipoLocal + ", EquipoVisitante=" + EquipoVisitante + ", Resultado="
			+ Resultado + "]";
}

}
