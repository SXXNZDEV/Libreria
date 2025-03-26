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

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public void agregarLibroCarrito(Libro libro){
        libros.add(libro);
    }
}
