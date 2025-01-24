package Classes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class JornadaApp {
int numero;
Array[] partido;

//cosntructor pod defecto 
JornadaApp(){
	numero = 0;
	partido = new Array[0];
}
//Getters y Setters
public int getNumero() {
	return numero;
}

public void setNumero(int numero) {
	this.numero = numero;
}

public Array[] getPartido() {
	return partido;
}

public void setPartido(Array[] partido) {
	this.partido = partido;
}
//hashCode
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.hashCode(partido);
	result = prime * result + Objects.hash(numero);
	return result;
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
	JornadaApp other = (JornadaApp) obj;
	return numero == other.numero && Arrays.equals(partido, other.partido);
}
@Override
public String toString() {
	return "JornadaApp [numero=" + numero + ", partido=" + Arrays.toString(partido) + "]";
}

}
