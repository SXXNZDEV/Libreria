package co.edu.uptc.negocio;

import co.edu.uptc.gui.ValorCompra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GestionCarrito {

    private Carrito carrito;
    private ManejoLibroJSON manejoLibroJSON;
    private ManejoUsuarioJSON manejoUsuarioJSON;
    private CalculadoraIVA calculadoraIVA;

    public GestionCarrito(ManejoUsuarioJSON manejoUsuarioJSON) {
        carrito = new Carrito();
        manejoLibroJSON = new ManejoLibroJSON();
        this.manejoUsuarioJSON = manejoUsuarioJSON;
        calculadoraIVA = new CalculadoraIVA();
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public ManejoUsuarioJSON getManejoUsuarioJSON() {
        return manejoUsuarioJSON;
    }

    public void anadirLibrosCarrito(Libro libro, int cantidad) throws IllegalArgumentException, IOException {
        Usuario usuarioLogin = manejoUsuarioJSON.getUsuarioLogin();
        Libro libroCatalogo = validarDisponibilidadLibros(libro.getIsbn(), cantidad);
        if (libroCatalogo != null) {
            Libro libroCarrito = existeProductoCarrito(libro.getIsbn());
            if (libroCarrito != null) {
                if (libroCatalogo.getStockDisponible() == 0) throw new IllegalArgumentException("Libro Agotado");
                libroCatalogo.reservarLibro();
                libroCarrito.aumentarCantidad(1);
                manejoUsuarioJSON.modificarUsuario(usuarioLogin);
                manejoLibroJSON.modificarLibro(libroCatalogo);
                return;
            }
            usuarioLogin.getCarrito().agregarLibroCarrito(libroCatalogo);
            libroCatalogo.reservarLibro();
            manejoUsuarioJSON.modificarUsuario(usuarioLogin);
            manejoLibroJSON.modificarLibro(libroCatalogo);
            return;
        }
        throw new IllegalArgumentException("No se pudo realizar la operación de añadir libros al carrito");
    }

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

    public Libro existeProductoCarrito(String isbn) throws IOException {
        if (manejoUsuarioJSON.getUsuarioLogin().getCarrito() == null) return null;
        for (Libro libro : manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros()) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    public ArrayList<Libro> listarLibros() {
        return manejoUsuarioJSON.getUsuarioLogin().getCarrito().getLibros();
    }

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

    public ValorCompra calculoResumenCompra() {
        ValorCompra valorCompra = new ValorCompra();
        Carrito carritoLocal = manejoUsuarioJSON.getUsuarioLogin().getCarrito();
        valorCompra.setImpuestos(calculadoraIVA.impuestos(carritoLocal));
        valorCompra.setSubtotal(calculadoraIVA.subtotal(carritoLocal));
        valorCompra.setTotal(calculadoraIVA.total(valorCompra.getSubtotal(), valorCompra.getImpuestos()));
        return valorCompra;
    }
}
