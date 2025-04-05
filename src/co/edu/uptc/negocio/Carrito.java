package co.edu.uptc.negocio;

import java.util.ArrayList;

/**
 * Clase encargada de almacenar los libros del carrito de compras.
 * Contiene un ArrayList de Libro.
 */
public class Carrito {

    /**
     * ArrayList de libros del carrito
     */
    private ArrayList<Libro> libros;

    /**
     * Constructor de la clase
     */
    public Carrito() {
        libros = new ArrayList<>();
    }

    /**
     * Método que devuelve el arrayList de libros del carrito
     * @return arrayList de libros del carrito
     */
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Agrega un libro al carrito
     * @param libro libro a agregar a la base de datos
     */
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

    /**
     * Método que actualiza el los atributos del libro en el carrito
     * @param librosArrayList arrayList de libros del carrito
     */
    public void setLibros(ArrayList<Libro> librosArrayList) {
        this.libros = librosArrayList;
    }
}
