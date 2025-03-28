package co.edu.uptc.negocio;

public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String categoria;
    private String editorial;
    private int numeroPaginas;
    private double precioVenta;
    private int stockDisponible;
    private int stockReservado;
    private TipoLibro tipoLibro;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String autor, int anioPublicacion, String categoria, String editorial, int numeroPaginas, double precioVenta, int stockDisponible, TipoLibro tipoLibro) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.precioVenta = precioVenta;
        this.stockDisponible = stockDisponible;
        this.tipoLibro = tipoLibro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void aumentarCantidad(int cantidadDisponible) {
        this.stockReservado += cantidadDisponible;

    }

    public void disminuirCantidadUnidad() {
        this.stockReservado--;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public TipoLibro getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(TipoLibro tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public int getStockReservado() {
        return stockReservado;
    }

    public void setStockReservado(int stockReservado) {
        this.stockReservado = stockReservado;
    }

    public boolean reservarLibro() {
        if (stockDisponible > 0) {
            stockReservado++;
            stockDisponible--;
            return true;
        }
        return false;
    }

    public void cancelarReserva() {
        if (stockReservado > 0) {
            stockDisponible ++;
            stockReservado--;
        }
    }

    public void eliminarReserva() {
        if (stockReservado > 0) {
            stockDisponible += stockReservado;
            stockReservado = 0;
        }
    }

    public void confirmarCompra() {
        if (stockReservado > 0) {
            stockReservado = 0;
        }
    }
}
