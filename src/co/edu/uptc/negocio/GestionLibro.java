package co.edu.uptc.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GestionLibro {

    private ManejoLibroJSON manejoLibroJSON;
    private Expresion expresion;

    public GestionLibro() {
        manejoLibroJSON = new ManejoLibroJSON();
        expresion = new Expresion();
    }

    public ManejoLibroJSON getManejoLibroJSON() {
        return manejoLibroJSON;
    }

    public void setManejoLibroJSON(ManejoLibroJSON manejoLibroJSON) {
        this.manejoLibroJSON = manejoLibroJSON;
    }

    public void registrarLibro(String isbn, String titulo, String autor, String anioPublicacion, String categoria, String editorial, String numeroPaginas, String precioVenta, String cantidadDisponible, TipoLibro tipoLibro) throws IllegalArgumentException, IOException {
        Libro libro = new Libro();
        expresion.validarDatosObligatorios(isbn, titulo, autor, anioPublicacion, editorial, numeroPaginas, precioVenta, cantidadDisponible);
        expresion.validarFormatoDatosLibro(isbn, titulo, autor, anioPublicacion, editorial, numeroPaginas, precioVenta, cantidadDisponible);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnioPublicacion(anioPublicacion.isBlank() ? 0 : Integer.parseInt(anioPublicacion));
        libro.setCategoria(categoria);
        libro.setEditorial(editorial);
        libro.setNumeroPaginas(numeroPaginas.isBlank() ? 0 : Integer.parseInt(numeroPaginas));
        libro.setPrecioVenta(Double.parseDouble(precioVenta));
        libro.aumentarCantidad(Integer.parseInt(cantidadDisponible));
        libro.setTipoLibro(tipoLibro);
        manejoLibroJSON.crearLibro(libro);
    }

    public void modificarLibro(String isbn, String titulo, String autor, String anioPublicacion, String categoria, String editorial, String numeroPaginas, String precioVenta, String cantidadDisponible, TipoLibro tipoLibro) throws IllegalArgumentException, IOException {
        Libro libro = new Libro();
        expresion.validarDatosObligatorios(isbn, titulo, autor, anioPublicacion, editorial, numeroPaginas, precioVenta, cantidadDisponible);
        expresion.validarFormatoDatosLibro(isbn, titulo, autor, anioPublicacion, editorial, numeroPaginas, precioVenta, cantidadDisponible);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnioPublicacion(anioPublicacion.isBlank() ? 0 : Integer.parseInt(anioPublicacion));
        libro.setCategoria(categoria);
        libro.setEditorial(editorial);
        libro.setNumeroPaginas(numeroPaginas.isBlank() ? 0 : Integer.parseInt(numeroPaginas));
        libro.setPrecioVenta(Double.parseDouble(precioVenta));
        libro.aumentarCantidad(Integer.parseInt(cantidadDisponible));
        libro.setTipoLibro(tipoLibro);
        manejoLibroJSON.modificarLibro(libro);
    }

    public String[] obtenerUsuarios() {
        ArrayList<String> libros = new ArrayList<>();
        String[] arrayLibros;
        for (ArrayList<Libro> catalogo : manejoLibroJSON.getMapLibros().values()) {
            for (Libro libro : catalogo) {
                libros.add(libro.getTitulo());
            }
        }
        arrayLibros = new String[libros.size()];
        for (int i = 0; i < arrayLibros.length; i++) {
            arrayLibros[i] = libros.get(i);
        }
        return arrayLibros;
    }

    public Libro buscarLibro(String tituloLibro) {
        for (ArrayList<Libro> catalogo : manejoLibroJSON.getMapLibros().values()) {
            for (Libro libroCatalogo : catalogo) {
                if (libroCatalogo.getTitulo().equals(tituloLibro)) {
                    return libroCatalogo;
                }
            }
        }
        return null;
    }

    public Libro encontrarLibro(Libro libro, Map<String, ArrayList<Libro>> catalogo) {
        for (ArrayList<Libro> libros : catalogo.values()) {
            for (Libro libroCatalogo : libros) {
                if (libro.getIsbn().equals(libroCatalogo.getIsbn())) {
                    return libroCatalogo;
                }
            }
        }
        return null;
    }
}
