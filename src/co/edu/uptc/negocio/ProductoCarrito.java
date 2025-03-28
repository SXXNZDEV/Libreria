package co.edu.uptc.negocio;

public class ProductoCarrito {

    private String isbn;
    private String nombre;
    private int cantidad;
    private int precio;

    public ProductoCarrito(String nombre, String isbn) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.cantidad = 0;
        this.precio = 0;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void aumentarCantidad() {
        cantidad++;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void disminuirCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }
}
