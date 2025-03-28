package co.edu.uptc.negocio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManejoUsuarioJSON {

    private List<Usuario> listaUsuarios;
    private File file;
    private ObjectMapper objectMapper;
    private String ruta;
    private Usuario usuarioLogin;

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuario) {
        usuarioLogin = usuario;
    }

    public ManejoUsuarioJSON() {
        objectMapper = new ObjectMapper();
        listaUsuarios = new ArrayList<>();
        ruta = "C:\\Users\\Usuario\\OneDrive\\Proyectos\\Libreria\\src\\co\\edu\\uptc\\persistencia\\usuario.json";
        file = new File(ruta);
    }

    public void crearUsuario(Usuario usuario) throws IllegalArgumentException {
        try {
            listaUsuarios = objectMapper.readValue(new File(ruta), new TypeReference<ArrayList<Usuario>>() {
            });
            if (buscarUsuario(listaUsuarios, usuario) != null) {
                throw new IllegalArgumentException("El correo '" + usuario.getCuenta().getCorreo() + "' ya está vinculado a otra cuenta");
            }
            listaUsuarios.add(usuario);
            objectMapper.writeValue(file, listaUsuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Usuario> leerUsuario() throws IOException {
        ArrayList<Usuario> users;
        try {
            users = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {
            });
        } catch (IOException e) {
            throw new IOException("Error al intentar leer el usuario");
        }
        return users;
    }

    public void modificarUsuario(Usuario usuarioBuscar) throws IOException, IllegalArgumentException {
        try {
            listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {});
            Usuario usuarioBuscado = buscarUsuario(listaUsuarios, usuarioBuscar);
            if (usuarioBuscado == null) throw new IllegalArgumentException("Usuario no encontrado");
            usuarioBuscado.setNombre(usuarioBuscar.getNombre());
            usuarioBuscado.getCuenta().setCorreo(usuarioBuscar.getCuenta().getCorreo());
            usuarioBuscado.getCuenta().setContrasena(usuarioBuscar.getCuenta().getContrasena());
            usuarioBuscado.getCuenta().setLog(usuarioBuscar.getCuenta().isLog());
            usuarioBuscado.setDireccionEnvio(usuarioBuscar.getDireccionEnvio());
            usuarioBuscado.setTelefono(usuarioBuscar.getTelefono());
            usuarioBuscado.setTipoCliente(usuarioBuscar.getTipoCliente());
            usuarioBuscado.getCarrito().setLibros(usuarioBuscar.getCarrito().getLibros());
            objectMapper.writeValue(file, listaUsuarios);
            usuarioLogin = usuarioBuscar;
        } catch (IOException e) {
            throw new IOException("Error al modificar el usuario");
        }
    }

    public void escribirUsuarioLogin() throws IOException{
        try {
            listaUsuarios = objectMapper.readValue(file, new TypeReference<ArrayList<Usuario>>() {});
            Usuario usuarioBuscado = buscarUsuario(listaUsuarios, usuarioLogin);
            usuarioBuscado.getCarrito().setLibros(usuarioLogin.getCarrito().getLibros());
            objectMapper.writeValue(file, listaUsuarios);
        } catch (IOException e) {
            throw new IOException( "Error al escribir el usuario");
        }
    }

    public Usuario buscarUsuario(List<Usuario> usuarioSet, Usuario usuarioBuscar) {
        for (Usuario usuario : usuarioSet) {
            if (usuario.getCuenta().getCorreo().equals(usuarioBuscar.getCuenta().getCorreo())) {
                return usuario;
            }
        }
        return null;
    }

    public void eliminarLibroCarrito(int index) {
        try {
            listaUsuarios = objectMapper.readValue(file, new TypeReference<List<Usuario>>() {
            });
            Usuario usuarioEncontrado = buscarUsuario(listaUsuarios, usuarioLogin);
            usuarioEncontrado.getCarrito().getLibros().remove(index);
            objectMapper.writeValue(file, listaUsuarios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getCause());
            //throw new IllegalArgumentException("Error al eliminar el libro del carrito");
        }
    }

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
