package Classes;

public class UsuarioApp {
    private String nombreUsuario;
    private String contraseña;
    private RolApp rol;

    public UsuarioApp(String nombreUsuario, String contraseña, RolApp rol) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public RolApp getRol() {
        return rol;
    }

    public void setRol(RolApp rol) {
        this.rol = rol;
    }

    @Override
	public String toString() {
		return "UsuarioApp [nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña + ", rol=" + rol + "]";
	}
}