package co.edu.uptc.negocio;

import java.util.ArrayList;

public class CalculadoraIVA {

    public double subtotal(Carrito carrito) {
        double subtotal = 0;
        if (carrito.getLibros().isEmpty()) return subtotal;
        for (Libro libro : carrito.getLibros()) {
            subtotal += libro.getStockReservado() * libro.getPrecioVenta();
        }
        return subtotal;
    }

    public double impuestos(Carrito carrito) {
        double impuestos = 0;
        if (carrito.getLibros().isEmpty()) return impuestos;
        for (Libro libro : carrito.getLibros()) {
            if (libro.getTipoLibro() == TipoLibro.FISICO) {
                impuestos += libro.getStockReservado() * 0.19 * libro.getPrecioVenta();
            } else {
                impuestos += libro.getStockReservado() * 0.05 * libro.getPrecioVenta();
            }
        }
        return impuestos;
    }

    public double total(double subtotal, double impuestos) {
        return subtotal + impuestos;
    }

    public double impuestoProducto(Libro libroParametro, ArrayList<Libro> catalogo) {
        for (Libro libro : catalogo) {
            if (libro.getTitulo().equals(libroParametro.getTitulo())) {
                if (libroParametro.getTipoLibro() == TipoLibro.FISICO) {
                    return libroParametro.getStockReservado() * libro.getPrecioVenta() * 0.19;
                } else {
                    return libroParametro.getStockReservado() * libro.getPrecioVenta() * 0.05;
                }
            }
        }
        return 0;
    }

    public double subtotalProducto(Libro libroParametro, ArrayList<Libro> catalogo) {
        for (Libro libro : catalogo) {
            if (libro.getIsbn().equals(libroParametro.getIsbn())) {
                return libroParametro.getStockReservado() * libro.getPrecioVenta();
            }
        }
        return 0;
    }
}

