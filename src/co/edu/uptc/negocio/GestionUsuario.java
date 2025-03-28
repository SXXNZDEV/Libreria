package co.edu.uptc.negocio;

import java.io.IOException;

public class GestionUsuario {

    private ManejoUsuarioJSON manejoUsuarioJSON;
    private Expresion expresion;
    private Usuario usuarioLogin;
    private Administrador administrador;

    public Usuario userLogin() {
        return manejoUsuarioJSON.getUsuarioLogin();
    }

    public ManejoUsuarioJSON getManejoUsuarioJSON() {
        return manejoUsuarioJSON;
    }

    public GestionUsuario() {
        manejoUsuarioJSON = new ManejoUsuarioJSON();
        expresion = new Expresion();
        usuarioLogin = new Usuario();
        administrador = new Administrador();
    }

    public void registrarUsuario(String nombre, String direccionEnvio, String telefono, String tipoCliente, String correo, String contrasena) throws IllegalArgumentException {
        //Le falta validar los datos que se están ingresando
        expresion.validarDatosUsuario(nombre, direccionEnvio, telefono, correo, contrasena);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setDireccionEnvio(direccionEnvio);
        usuario.setTelefono(Long.parseLong(telefono));
        usuario.setTipoCliente(tipoCliente);
        usuario.getCuenta().setCorreo(correo);
        usuario.getCuenta().setContrasena(contrasena);
        usuario.getCuenta().setLog(false);
        manejoUsuarioJSON.crearUsuario(usuario);
    }

    public void iniciarSesion(String correo, String contrasena) throws IllegalArgumentException{
        validarCamposVaciosLogin(correo, contrasena);
        Usuario usuario = new Usuario();
        usuario.getCuenta().setCorreo(correo);
        usuario.getCuenta().setContrasena(contrasena);
        manejoUsuarioJSON.validarDatosLogin(usuario);
    }

    public void validarCamposVaciosLogin(String correo, String contrasena) throws IllegalArgumentException{
        if (correo.isBlank() && contrasena.isBlank()) {
            throw new IllegalArgumentException("Complete los campos de texto.");
        } else if (correo.isBlank()) {
            throw new IllegalArgumentException("Ingrese un correo.");
        } else if (contrasena.isBlank()) {
            throw new IllegalArgumentException("Ingrese una contraseña.");
        }
    }

    public boolean validarLoginAdmin() {
        return manejoUsuarioJSON.getUsuarioLogin().getCuenta().getCorreo().equals(administrador.getCORREO());
    }

    public void modificarUsuario(Usuario usuario) throws IOException, IllegalArgumentException{
        expresion.validarDatosUsuario(usuario.getNombre(), usuario.getDireccionEnvio(), String.valueOf(usuario.getTelefono()), usuario.getCuenta().getCorreo(), usuario.getCuenta().getContrasena());
        manejoUsuarioJSON.modificarUsuario(usuario);
    }

    public void cerrarSesion() throws IOException {
        manejoUsuarioJSON.getUsuarioLogin().getCuenta().setLog(false);
        manejoUsuarioJSON.modificarUsuario(manejoUsuarioJSON.getUsuarioLogin());
        manejoUsuarioJSON.setUsuarioLogin(null);
    }


}
