package co.edu.uptc.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GestionCarrito {

    private Carrito carrito;
    private ManejoLibroJSON manejoLibroJSON;
    private ManejoUsuarioJSON manejoUsuarioJSON;

    public GestionCarrito(ManejoUsuarioJSON manejoUsuarioJSON) {
        carrito = new Carrito();
        manejoLibroJSON = new ManejoLibroJSON();
        this.manejoUsuarioJSON = manejoUsuarioJSON;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public void anadirLibrosCarrito(String titulo, int cantidad) throws IllegalArgumentException, IOException {
        Usuario usuarioLogin = manejoUsuarioJSON.getUsuarioLogin();
        Libro libro = validarDisponibilidadLibros(titulo, cantidad);
        if (libro != null) {
            Libro libroCarrito = existeProductoCarrito(titulo);
            if (libroCarrito != null) {
                if (libro.getStockDisponible() == 0) throw new IllegalArgumentException("Libro Agotado");
                libro.reservarLibro();
                libroCarrito.aumentarCantidad(1);
                manejoUsuarioJSON.modificarUsuario(usuarioLogin);
                manejoLibroJSON.modificarLibro(libro);
                return;
            }
            usuarioLogin.getCarrito().agregarLibroCarrito(libro);
            libro.reservarLibro();
            manejoUsuarioJSON.modificarUsuario(usuarioLogin);
            manejoLibroJSON.modificarLibro(libro);
            return;
        }
        throw new IllegalArgumentException("No se pudo realizar la operación de añadir libros al carrito");
    }

    public Libro validarDisponibilidadLibros(String titulo, int cantidadSolicitada) throws IllegalArgumentException{
        Map<String, ArrayList<Libro>> mapLibros = manejoLibroJSON.leerLibro();
        for (ArrayList<Libro> listLibros : mapLibros.values()) {
            for (Libro libroList : listLibros) {
                if (libroList.getStockDisponible() == 0) throw new IllegalArgumentException("Libro Agotado");
                if (libroList.getTitulo().equals(titulo)) {
                    return libroList;
                }
            }
        }
        return null;
    }

    public Libro existeProductoCarrito(String titulo) throws IOException {
        if (manejoUsuarioJSON.getUsuarioLogin().getCarrito() == null) return null;
        for (Libro libro : manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros()) {
            if (libro.getTitulo().equals(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public ArrayList<Libro> listarLibros() {
        return manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
    }
}
