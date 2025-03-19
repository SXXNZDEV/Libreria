package co.edu.uptc.negocio;

public class Expresion {

    public static final String EXPRESION_ALFABETICA = "^[a-zA-Z\\s]+$";
    public static final String EXPRESION_NUMERICA = "^[0-9]+$";
    public static final String EXPRESION_DIRECCION = "^([\\w\\s#.-]+),\\s*[\\p{L}\\s]+,\\s*[\\p{L}\\s]+$";
    public static final String EXPRESION_CORREO = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}+$";
    public static final String EXPRESION_CONTRASENA = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    public void validarDatosUsuario(String nombre, String direccion, String telefono, String correo, String contrasena) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (!correo.matches(EXPRESION_CORREO)) {
            sb.append("El correo debe tener la estructura usuario@dominio.extension\n");
        }
        if (!contrasena.matches(EXPRESION_CONTRASENA)) {
            sb.append("La contraseña debe tener al menos una letra, un número y ocho caracteres.\n");
        }
        if (!telefono.matches(EXPRESION_NUMERICA)) {
            sb.append("El teléfono debe tener solo numeros\n");
        }
        if (!direccion.matches(EXPRESION_DIRECCION)) {
            sb.append("Estructura de la dirección ej: Calle 123 #45-67, Bogotá, Colombia\n");
        }
        if (!nombre.matches(EXPRESION_ALFABETICA)) {
            sb.append("El nombre solo puede llevar letras\n");
        }
        if (!sb.isEmpty()) {
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
