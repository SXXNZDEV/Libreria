package co.edu.uptc.negocio;

public class Cuenta {

    private String correo;
    private String contrasena;
    private boolean isLog;

    public Cuenta() {}

    public Cuenta(String correo, String contrasena, boolean isLog) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.isLog = isLog;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }
}
