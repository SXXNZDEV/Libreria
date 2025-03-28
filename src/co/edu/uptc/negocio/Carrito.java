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
                libroArrayList.reservarLibro();
                return;
            }
        }
    }

    public void disminuirLibros(Libro libro) {
        for(Libro libroArrayList : libros) {
            if (libroArrayList.getTitulo().equals(libro.getTitulo())) {
                libroArrayList.cancelarReserva();
                return;
            }
        }
    }

    public void eliminarLibro(Libro libro) {
        for(Libro libroArrayList : libros) {
            if (libroArrayList.getTitulo().equals(libro.getTitulo())) {
                libroArrayList.eliminarReserva();
                return;
            }
        }
    }

    public void agregarLibroCarrito(Libro libro) {
        Libro libroGuardar = new Libro();
        libroGuardar.setIsbn(libro.getIsbn());
        libroGuardar.setAutor(libro.getAutor());
        libroGuardar.setEditorial(libro.getEditorial());
        libroGuardar.setCategoria(libro.getCategoria());
        libroGuardar.setTipoLibro(libro.getTipoLibro());
        libroGuardar.setTitulo(libro.getTitulo());
        libroGuardar.setAnioPublicacion(libro.getAnioPublicacion());
        libroGuardar.setNumeroPaginas(libro.getNumeroPaginas());
        libroGuardar.setPrecioVenta(libro.getPrecioVenta());
        libroGuardar.setStockDisponible(0);
        libroGuardar.setStockReservado(1);
        libros.add(libroGuardar);
    }

    public void setLibros(ArrayList<Libro> librosArrayList) {
        this.libros = librosArrayList;
    }
}
