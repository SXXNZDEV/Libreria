package co.edu.uptc.negocio;

import java.time.LocalDate;

/**
 * Clase encargad de validar los datos del usuario.
 * Contiene expresiones regulares para validar los datos.
 */
public class Expresion {

    /**
     * Expresiones regulares
     */
    public static final String EXPRESION_ALFABETICA = "^[a-zA-Z\\s]+$";
    public static final String EXPRESION_NUMERICA = "^[0-9]+$";
    public static final String EXPRESION_DIRECCION = "^([\\w\\s#.-]+),\\s*[\\p{L}\\s]+,\\s*[\\p{L}\\s]+$";
    public static final String EXPRESION_CORREO = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}+$";
    public static final String EXPRESION_CONTRASENA = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String EXPRESION_ISBN = "^(978|979)(-?[0-9]){10}$";
    public static final String EXPRESION_ANO_PUBLICACION = "^[0-9]{4}$";


    /**
     * Metodo que valida los datos del usuario en el formulario
     * @param usuario a validar los datos
     * @throws IllegalArgumentException si alguno de los campos no cumple con las reglas
     */
    public void validarDatosUsuario(Usuario usuario) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (!usuario.getCuenta().getCorreo().matches(EXPRESION_CORREO)) {
            sb.append("El correo debe tener la estructura usuario@dominio.extension\n");
        }
        if (!usuario.getCuenta().getContrasena().matches(EXPRESION_CONTRASENA)) {
            sb.append("La contraseña debe tener al menos una letra, un número y ocho caracteres.\n");
        }
        if (!String.valueOf(usuario.getTelefono()).matches(EXPRESION_NUMERICA)) {
            sb.append("El teléfono debe tener solo numeros\n");
        }
        if (!usuario.getDireccionEnvio().matches(EXPRESION_DIRECCION)) {
            sb.append("Estructura de la dirección ej: Calle 123 #45-67, Bogotá, Colombia\n");
        }
        if (!usuario.getNombre().matches(EXPRESION_ALFABETICA)) {
            sb.append("El nombre solo puede llevar letras\n");
        }
        if (!sb.isEmpty()) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /**
     * Metodo que valida los datos del usuario en el formulario
     * @param libro libro a validar
     * @throws IllegalArgumentException si alguno de los campos no cumple con las reglas
     */
    public void validarFormatoDatosLibro(Libro libro) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (!libro.getIsbn().matches(EXPRESION_ISBN)) {
            sb.append("El ISBN debe tener al menos 13 numeros y debe comenzar con 979 0 978\n");
        }
        if (!libro.getAutor().matches(EXPRESION_ALFABETICA)) {
            sb.append("El nombre del autor solo puede tener letras\n");
        }
        if (String.valueOf(libro.getAnioPublicacion()).isBlank() || libro.getAnioPublicacion() != 0) {
            if (!String.valueOf(libro.getAnioPublicacion()).matches(EXPRESION_ANO_PUBLICACION) || libro.getAnioPublicacion() > LocalDate.now().getYear()) {
                sb.append("El año de publicación debe tener cuatro digitos y debe ser igual o menor al actual.\n");
            }
        }
        if (!String.valueOf(libro.getNumeroPaginas()).isBlank() || libro.getNumeroPaginas() == 0) {
            if (!String.valueOf(libro.getNumeroPaginas()).matches(EXPRESION_NUMERICA)) {
                sb.append("Numero de páginas invalido\n");
            }
        }
        if (!String.valueOf((int)libro.getPrecioVenta()).matches(EXPRESION_NUMERICA)) {
            sb.append("Precio Unitario del libro invalido.\n");
        }
        if (!String.valueOf(libro.getStockDisponible()).matches(EXPRESION_NUMERICA)) {
            sb.append("Cantidad ingresada invalida\n");
        }
        if (!sb.isEmpty()) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /**
     * Metodo que valida los datos del usuario en el formulario
     * @param libro libro a validar
     * @throws IllegalArgumentException si alguno de los campos no cumple con las reglas
     */
    public void validarDatosObligatorios(Libro libro) throws IllegalArgumentException {
        if (libro.getIsbn().isBlank() || libro.getTitulo().isBlank() || libro.getAutor().isBlank() || (String.valueOf(libro.getPrecioVenta()).isBlank() || libro.getPrecioVenta() == 0) || (String.valueOf(libro.getStockDisponible()).isBlank() || libro.getStockDisponible() == 0) || (String.valueOf(libro.getNumeroPaginas()).isBlank() || libro.getNumeroPaginas() == 0)) {
            throw new IllegalArgumentException("Los campos con * son obligatorios.\n");
        }
    }
}
