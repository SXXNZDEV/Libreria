package co.edu.uptc.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class GestionCarrito {

    private Carrito carrito;
    private ManejoLibroJSON manejoLibroJSON;
    private ManejoUsuarioJSON manejoUsuarioJSON;

    public GestionCarrito() {
        carrito = new Carrito();
        manejoLibroJSON = new ManejoLibroJSON();
        this.manejoUsuarioJSON = new ManejoUsuarioJSON();

    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public void anadirLibrosCarrito(String titulo, int cantidad) throws IllegalArgumentException, IOException {
        Libro libro = validarDisponibilidadLibros(titulo, cantidad);
        if (libro != null) {
            Libro libroCarrito = existeProductoCarrito(titulo);
            if (libroCarrito != null) {
                libroCarrito.setCantidadDisponible(cantidad);
                return;
            }
            getCarrito().agregarLibroCarrito(libro);
            return;
        }
        throw new IllegalArgumentException("No se pudo realizar la operación de añadir libros al carrito");
    }

    public Libro validarDisponibilidadLibros(String titulo, int cantidadSolicitada) {
        Map<String, ArrayList<Libro>> mapLibros = manejoLibroJSON.leerLibro();
        for (ArrayList<Libro> listLibros : mapLibros.values()) {
            for (Libro libroList : listLibros) {
                if (libroList.getTitulo().equals(titulo)) {
                    return libroList;
                }
            }
        }
        return null;
    }

    public Libro existeProductoCarrito(String titulo) throws IOException {
        ArrayList<Usuario> usuarios = manejoUsuarioJSON.leerUsuario();
        for (Libro libro : manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros()) {
            if (libro.equals(titulo)) {
                return  libro;
            }
        }
        return null;
    }
}
