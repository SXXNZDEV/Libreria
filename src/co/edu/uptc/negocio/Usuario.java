package co.edu.uptc.negocio;

public class Usuario {

    private String nombre;
    private String direccionEnvio;
    private long telefono;
    private String tipoCliente;
    private Cuenta cuenta;

    public Usuario() {
        cuenta = new Cuenta();
    }

    public Usuario(String nombre, String direccionEnvio, long telefono, String tipoCliente) {
        this.nombre = nombre;
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public Cuenta getCuenta()  {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
