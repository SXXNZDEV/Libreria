package co.edu.uptc.negocio;

import co.edu.uptc.gui.ValorCompra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase encargada de gestionar el carrito de compras del usuario.
 */
public class GestionCarrito {

    /**
     * Carrito del usuario
     */
    private Carrito carrito;

    /**
     * Instancia de Manejo de libros con JSON
     */
    private ManejoLibroJSON manejoLibroJSON;

    /**
     * Instancia de Manejo de usuarios con JSON
     */
    private ManejoUsuarioJSON manejoUsuarioJSON;

    /**
     * Calculadora de IVA
     */
    private CalculadoraIVA calculadoraIVA;

    /**
     * Constructor de la clase
     * @param manejoUsuarioJSON Instancia deManejo de usuarios con JSON
     */
    public GestionCarrito(ManejoUsuarioJSON manejoUsuarioJSON) {
        carrito = new Carrito();
        manejoLibroJSON = new ManejoLibroJSON();
        this.manejoUsuarioJSON = manejoUsuarioJSON;
        calculadoraIVA = new CalculadoraIVA();
    }

    /**
     * Método que devuelve el carrito del usuario
     * @return carrito del usuario
     */
    public Carrito getCarrito() {
        return carrito;
    }

    /**
     * Método que actualiza el carrito del usuario
     * @param carrito carrito del usuario
     */
    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    /**
     * Método que devuelve la instancia de Manejo de usuarios con JSON
     * @return instancia de Manejo de usuarios con JSON
     */
    public ManejoUsuarioJSON getManejoUsuarioJSON() {
        return manejoUsuarioJSON;
    }

    /**
     * Método que agrega los libros al carrito del usuario
     * @param libro libro a agregar al carrito
     * @param cantidad cantidad de libros a agregar al carrito
     * @throws IllegalArgumentException si el libro no existe en el catálogo
     * @throws IOException si ocurre algún error cuando no se escribe el usuario en el JSON
     */
    public void anadirLibrosCarrito(Libro libro, int cantidad) throws IllegalArgumentException, IOException {
        Usuario usuarioLogin = manejoUsuarioJSON.getUsuarioLogin();
        Libro libroCatalogo = validarDisponibilidadLibros(libro.getIsbn(), cantidad);
        if (libroCatalogo != null) {
            Libro libroCarrito = existeProductoCarrito(libro.getIsbn(), usuarioLogin);
            if (libroCarrito != null) {
                if (libroCatalogo.getStockDisponible() == 0) throw new IllegalArgumentException("Libro Agotado");
                libroCatalogo.reservarLibro();
                libroCarrito.aumentarCantidad(1);
                manejoUsuarioJSON.modificarUsuarioCarrito(usuarioLogin);
                manejoLibroJSON.modificarLibro(libroCatalogo);
                return;
            }
            usuarioLogin.getCarrito().agregarLibroCarrito(libroCatalogo);
            libroCatalogo.reservarLibro();
            manejoUsuarioJSON.modificarUsuarioCarrito(usuarioLogin);
            manejoLibroJSON.modificarLibro(libroCatalogo);
            return;
        }
        throw new IllegalArgumentException("No se pudo realizar la operación de añadir libros al carrito");
    }

    /**
     * Método que valida si el libro está disponible en el catálogo
     * @param isbn isbn del libro
     * @param cantidadSolicitada cantidad solicitada
     * @return libro disponible
     * @throws IllegalArgumentException si el libro no está disponible
     */
    public Libro validarDisponibilidadLibros(String isbn, int cantidadSolicitada) throws IllegalArgumentException {
        Map<String, ArrayList<Libro>> mapLibros = manejoLibroJSON.leerLibro();
        for (ArrayList<Libro> listLibros : mapLibros.values()) {
            for (Libro libroList : listLibros) {
                if (libroList.getIsbn().equals(isbn)) {
                    if (libroList.getStockDisponible() == 0) throw new IllegalArgumentException("Libro Agotado");
                    return libroList;
                }
            }
        }
        return null;
    }

    /**
     * Método que verifica si el libro ya está en el carrito
     * @param isbn isbn del libro
     * @return libro encontrado en el carrito
     * @throws IOException si ocurre algún error cuando no se lee el usuario en el JSON
     */
    public Libro existeProductoCarrito(String isbn, Usuario usuarioLogin) throws IOException {
        if (usuarioLogin == null) return null;
        for (Libro libro : usuarioLogin.getCarrito().getLibros()) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Método que devuelve el arrayList de libros del carrito del usuario
     * @return arrayList de libros del carrito del usuario
     */
    public ArrayList<Libro> listarLibros() {
        return manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
    }

    /**
     * Método que suma la cantidad de un libro en el carrito
     * @param producto libro a sumar
     * @return subtotal del producto
     * @throws IOException si ocurre algún error cuando no se lee el usuario en el JSON
     */
    public double sumarProducto(Libro producto) throws IOException {
        ArrayList<Libro> librosCarrito = manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
        Map<String, ArrayList<Libro>> catalogo = manejoLibroJSON.leerLibro();
        int index = librosCarrito.indexOf(producto);

        if (index >= 0) {
            Libro libroModificar = encontrarLibro(producto, catalogo);
            if (libroModificar.getStockDisponible() == 0) throw new IllegalArgumentException("Libro agotado.");

            libroModificar.reservarLibro();
            librosCarrito.get(index).aumentarCantidad(1);

            manejoUsuarioJSON.escribirUsuarioLogin();
            manejoLibroJSON.escribirLibros(catalogo);

            return calculadoraIVA.subtotalProducto(producto, librosCarrito);
        }
        return 0;
    }

    /**
     * Método que disminuye la cantidad de un libro en el carrito
     * @param producto libro a disminuir
     * @return subtotal del producto
     * @throws IOException si ocurre algún error cuando no se lee el usuario en el JSON
     */
    public double disminuirProducto(Libro producto) throws IOException {
        ArrayList<Libro> librosCarrito = manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
        Map<String, ArrayList<Libro>> catalogo = manejoLibroJSON.leerLibro();
        int index = librosCarrito.indexOf(producto);

        if (index >= 0) {
            Libro libroModificar = encontrarLibro(producto, catalogo);
            libroModificar.cancelarReserva();
            librosCarrito.get(index).disminuirCantidadUnidad();

            manejoUsuarioJSON.escribirUsuarioLogin();
            manejoLibroJSON.escribirLibros(catalogo);

            return calculadoraIVA.subtotalProducto(producto, librosCarrito);
        }
        return 0;
    }

    /**
     * Método que elimina el libro del carrito
     * @param producto libro a eliminar
     * @return subtotal del producto
     * @throws IOException si ocurre algún error cuando no se lee el usuario en el JSON
     */
    public double eliminarProducto(Libro producto) throws IOException {
        ArrayList<Libro> librosCarrito = manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
        Map<String, ArrayList<Libro>> catalogo = manejoLibroJSON.leerLibro();
        int index = librosCarrito.indexOf(producto);

        if (index >= 0) {
            Libro libroModificar = encontrarLibro(producto, catalogo);
            libroModificar.eliminarReserva();
            manejoLibroJSON.escribirLibros(catalogo);

            librosCarrito.remove(index);

            manejoUsuarioJSON.escribirUsuarioLogin();
            manejoLibroJSON.escribirLibros(catalogo);
            return calculadoraIVA.subtotalProducto(producto, librosCarrito);
        }
        return 0;
    }

    /**
     * Método que busca un libro en el catálogo
     * @param libro libro a buscar
     * @param catalogo catálogo de libros para buscar
     * @return libro encontrado
     */
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

    /**
     * Método que calcula el valor total del carrito
     * @return valor total del carrito
     */
    public ValorCompra calculoResumenCompra() {
        ValorCompra valorCompra = new ValorCompra();
        Carrito carritoLocal = manejoUsuarioJSON.getUsuarioLogin().getCarrito();
        valorCompra.setImpuestos(calculadoraIVA.impuestos(carritoLocal));
        valorCompra.setSubtotal(calculadoraIVA.subtotal(carritoLocal));
        valorCompra.setTotal(calculadoraIVA.total(valorCompra.getSubtotal(), valorCompra.getImpuestos()));
        return valorCompra;
    }
}
