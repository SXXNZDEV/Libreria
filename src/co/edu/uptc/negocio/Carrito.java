package co.edu.uptc.negocio;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<Libro> libros;

    public Carrito() {
        libros = new ArrayList<>();
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void aumentarLibros(Libro libro) {
        for(Libro libroArrayList : libros) {
            if (libroArrayList.getTitulo().equals(libro.getTitulo())) {
                libroArrayList.aumentarCantidad(1);
                return;
            }
        }
    }

    public void disminuirLibros(Libro libro) {
        for(Libro libroArrayList : libros) {
            if (libroArrayList.getTitulo().equals(libro.getTitulo())) {
                libroArrayList.disminuirCantidadUnidad();
                return;
            }
        }
    }

    public void agregarLibroCarrito(Libro libro) {
        libro.setCantidadDisponible(1);
        libros.add(libro);
    }

    public void setLibros(ArrayList<Libro> librosArrayList) {
        this.libros = librosArrayList;
    }
}
