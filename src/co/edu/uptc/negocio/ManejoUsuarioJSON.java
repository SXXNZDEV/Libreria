package co.edu.uptc.negocio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar la persistencia de usuarios en formato JSON.
 * Permite la lectura, escritura y manipulación de los datos de los usuarios en un archivo JSON.
 */
public class ManejoUsuarioJSON {

    /**
     * Lista que almacena los usuarios cargados desde el archivo JSON.
     */
    private List<Usuario> listaUsuarios;

    /**
     * Archivo donde se almacenan los datos de los usuarios en formato JSON.
     */
    private File file;

    /**
     * Objeto de Jackson para serializar y deserializar los datos en JSON.
     */
    private ObjectMapper objectMapper;

    /**
     * Ruta del archivo JSON donde se guardan los usuarios.
     */
    private String ruta;

    /**
     * Usuario que ha iniciado sesión en el sistema.
     */
    private Usuario usuarioLogin;

    /**
     * Obtiene la lista de usuarios almacenados.
     *
     * @return Una lista de usuarios.
     */
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Establece la lista de usuarios.
     *
     * @param listaUsuarios La nueva lista de usuarios.
     */
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * Obtiene el archivo donde se almacenan los datos de los usuarios en formato JSON.
     *
     * @return El archivo JSON de usuarios.
     */
    public File getFile() {
        return file;
    }

    /**
     * Establece el archivo donde se almacenarán los usuarios.
     *
     * @param file Archivo JSON de usuarios.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Obtiene el objeto ObjectMapper utilizado para la serialización y deserialización de JSON.
     *
     * @return El ObjectMapper de la clase.
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Establece el ObjectMapper utilizado para manejar los datos en JSON.
     *
     * @param objectMapper Un nuevo ObjectMapper.
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Obtiene el usuario actualmente autenticado en el sistema.
     *
     * @return El usuario que ha iniciado sesión.
     */
    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    /**
     * Establece el usuario que ha iniciado sesión en el sistema.
     *
     * @param usuario El usuario autenticado.
     */
    public void setUsuarioLogin(Usuario usuario) {
        usuarioLogin = usuario;
    }

    /**
     * Constructor de la clase ManejoUsuarioJSON.
     * Inicializa el ObjectMapper, la lista de usuarios y define la ruta del archivo JSON.
     */
    public ManejoUsuarioJSON() {
        objectMapper = new ObjectMapper();
        listaUsuarios = new ArrayList<>();
        ruta = "src/co/edu/uptc/persistencia/usuario.json";
        file = new File(ruta);
    }

    /**
     * Agrega un nuevo usuario al archivo JSON si no está registrado previamente.
     *
     * @param usuario El usuario que se desea agregar.
     * @throws IllegalArgumentException Si el correo del usuario ya está vinculado a otra cuenta.
     */
    public void crearUsuario(Usuario usuario) throws IllegalArgumentException {
        try {
            if (listaUsuarios == null || !file.exists()) {
                listaUsuarios = new ArrayList<>();
            } else {
                listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {
                });
            }
            if (buscarUsuario(listaUsuarios, usuario) != null) {
                throw new IllegalArgumentException("El correo '" + usuario.getCuenta().getCorreo() + "' ya está vinculado a otra cuenta");
            }
            listaUsuarios.add(usuario);
            objectMapper.writeValue(file, listaUsuarios);
        } catch (IOException e) {
            throw new RuntimeException("Error al acceder al archivo JSON de usuarios");
        }
    }

    /**
     * Modifica los datos del usuario existente en el archivo JSON.
     *
     * @param usuarioBuscar El usuario que se desea modificar.
     * @throws IOException Si ocurre algún error al leer o escribir el archivo JSON.
     * @throws IllegalArgumentException Si la lista de usuarios no está inicializada o el usuario no existe.
     */
    public void modificarUsuario(Usuario usuarioBuscar) throws IOException, IllegalArgumentException {
        try {
            if (listaUsuarios == null) {
                throw new IllegalArgumentException("No hay usuarios registrados");
            }
            listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {
            });
            Usuario usuarioBuscado = buscarUsuario(listaUsuarios, usuarioBuscar);
            if (usuarioBuscado == null) throw new IllegalArgumentException("Usuario no encontrado");
            usuarioBuscado.setNombre(usuarioBuscar.getNombre());
            usuarioBuscado.getCuenta().setCorreo(usuarioBuscar.getCuenta().getCorreo());
            usuarioBuscado.getCuenta().setContrasena(usuarioBuscar.getCuenta().getContrasena());
            usuarioBuscado.getCuenta().setLog(usuarioBuscar.getCuenta().isLog());
            usuarioBuscado.setDireccionEnvio(usuarioBuscar.getDireccionEnvio());
            usuarioBuscado.setTelefono(usuarioBuscar.getTelefono());
            usuarioBuscado.setTipoCliente(usuarioBuscar.getTipoCliente());
            objectMapper.writeValue(file, listaUsuarios);
            usuarioLogin = usuarioBuscado;
        } catch (IOException e) {
            throw new IOException("Error al modificar el usuario");
        }
    }

    public void modificarUsuarioCarrito(Usuario usuarioBuscar) throws IOException, IllegalArgumentException {
        try {
            if (listaUsuarios == null) {
                throw new IllegalArgumentException("No hay usuarios registrados");
            }
            listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {
            });
            Usuario usuarioBuscado = buscarUsuario(listaUsuarios, usuarioBuscar);
            if (usuarioBuscado == null) throw new IllegalArgumentException("Usuario no encontrado");
            usuarioBuscado.setNombre(usuarioBuscar.getNombre());
            usuarioBuscado.getCuenta().setCorreo(usuarioBuscar.getCuenta().getCorreo());
            usuarioBuscado.getCuenta().setContrasena(usuarioBuscar.getCuenta().getContrasena());
            usuarioBuscado.getCuenta().setLog(usuarioBuscar.getCuenta().isLog());
            usuarioBuscado.setDireccionEnvio(usuarioBuscar.getDireccionEnvio());
            usuarioBuscado.setTelefono(usuarioBuscar.getTelefono());
            usuarioBuscado.setTipoCliente(usuarioBuscar.getTipoCliente());
            usuarioBuscado.setCarrito(usuarioBuscar.getCarrito());
            objectMapper.writeValue(file, listaUsuarios);
            usuarioLogin = usuarioBuscado;
        } catch (IOException e) {
            throw new IOException("Error al modificar el usuario");
        }
    }


    /**
     * Escribe el usuario actualmente autenticado en el archivo JSON.
     *
     * @throws IOException Si ocurre algún error al leer o escribir el archivo JSON.
     * @throws IllegalArgumentException Si la lista de usuarios no está inicializada.
     */
    public void escribirUsuarioLogin() throws IOException, IllegalArgumentException {
        try {
            if (listaUsuarios == null) {
                throw new IllegalArgumentException("No hay usuarios registrados");
            }
            listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {
            });
            Usuario usuarioBuscado = buscarUsuario(listaUsuarios, usuarioLogin);
            usuarioBuscado.getCarrito().setLibros(usuarioLogin.getCarrito().getLibros());
            objectMapper.writeValue(file, listaUsuarios);
        } catch (IOException e) {
            throw new IOException("Error al guardar el usuario");
        }
    }

    /**
     * Busca un usuario en la lista de usuarios.
     *
     * @param usuarioSet Lista de usuarios.
     * @param usuarioBuscar Usuario a buscar.
     * @return Usuario encontrado.
     */
    public Usuario buscarUsuario(List<Usuario> usuarioSet, Usuario usuarioBuscar) {
        for (Usuario usuario : usuarioSet) {
            if (usuario.getCuenta().getCorreo().equals(usuarioBuscar.getCuenta().getCorreo())) {
                return usuario;
            }
        }
        return null;
    }

    /*public void eliminarLibroCarrito(int index) {
        try {
            listaUsuarios = objectMapper.readValue(file, new TypeReference<List<Usuario>>() {
            });
            Usuario usuarioEncontrado = buscarUsuario(listaUsuarios, usuarioLogin);
            usuarioEncontrado.getCarrito().getLibros().remove(index);
            objectMapper.writeValue(file, listaUsuarios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            //throw new IllegalArgumentException("Error al eliminar el libro del carrito");
        }
    }*/

    /**
     * Valida si los datos para iniciar sesión son correctos.
     *
     * @param usuario Usuario a validar.
     * @return {@code true} si los datos son correctos, {@code false} en caso contrario.
     * @throws IllegalArgumentException Si el usuario no existe o la contraseña es incorrecta.
     */
    public boolean validarDatosLogin(Usuario usuario) throws IllegalArgumentException {
        try {
            listaUsuarios = objectMapper.readValue(file, new TypeReference<List<Usuario>>() {
            });
            Usuario usuarioEncontrado = buscarUsuario(listaUsuarios, usuario);
            if (usuarioEncontrado == null) {
                throw new IllegalArgumentException("Usuario no encontrado");
            }
            if (!usuarioEncontrado.getCuenta().getContrasena().equals(usuario.getCuenta().getContrasena())) {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
            usuarioEncontrado.getCuenta().setLog(true);
            usuarioLogin = usuarioEncontrado;
            objectMapper.writeValue(file, listaUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
