package co.edu.uptc.gui;

import co.edu.uptc.negocio.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;

    private Evento evento;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelVenta panelVenta;
    private GestionUsuario gestionUsuario;
    private GestionLibro gestionLibro;
    private GestionCatalogo gestionCatalogo;
    private EventoLista eventoLista;
    private GestionCarrito gestionCarrito;
    private EventoCantidad eventoCantidad;
    private CalculadoraIVA calculadoraIVA;


    public VentanaPrincipal() {
        super("Librería Virtual");
        setLayout(new BorderLayout());

        eventoLista = new EventoLista(this);
        evento = new Evento(this);
        cardLayout = new CardLayout();

        gestionUsuario = new GestionUsuario();
        gestionLibro = new GestionLibro();
        gestionCatalogo = new GestionCatalogo();
        gestionCarrito = new GestionCarrito(gestionUsuario.getManejoUsuarioJSON());
        panelCL = new JPanel(cardLayout);
        calculadoraIVA = new CalculadoraIVA();

        panelVenta = new PanelVenta(evento, eventoLista, this);
        panelInicioSesion = new PanelInicioSesion(evento);

        panelCL.add(panelInicioSesion, "Iniciar Sesion");
        panelCL.add(panelVenta, "Panel Venta");

        add(panelCL, BorderLayout.CENTER);
        setResizable(false);
        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        VentanaPrincipal main = new VentanaPrincipal();
    }

    public void activarPanelInicioSesion() {
        cardLayout.show(panelCL, "Iniciar Sesion");
    }

    public void activarPanelGestionLibros() {
        panelVenta.activarPanelGestionLibro();
    }

    public void activarFuncionRegresar() {
        System.exit(0);
    }

    public void activarPanelVenta() {
        String correo = panelInicioSesion.getTxtCorreo().getText();
        String contrasena = panelInicioSesion.getTxtContrasena().getText();
        try {
            gestionUsuario.iniciarSesion(correo, contrasena);
            limpiarTxtLogin();
            panelVenta.getPanelCatalogo().crearPanelesLibros(gestionCatalogo.listarLibros());
            cardLayout.show(panelCL, "Panel Venta");
            panelVenta.activarPanelCatalogo();
            panelVenta.setLabelNombreUsuario(gestionUsuario.userLogin().getNombre());
            if (gestionUsuario.validarLoginAdmin()) {
                panelVenta.anadirFuncionesAdmin();
            } else {
                panelVenta.quitarFuncionesAdmin();
            }
        } catch (IllegalArgumentException | IOException e) {
            JOptionPane.showMessageDialog(panelInicioSesion, e.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void activarCerrarSesion() {
        try {
            gestionUsuario.cerrarSesion();
            cardLayout.first(panelCL);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta, e.getMessage(), "Cerrar Sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarPanelPerfil() {
        panelVenta.getPanelPerfil().setLabelNombre("Nombre: " + gestionUsuario.userLogin().getNombre());
        panelVenta.getPanelPerfil().setLabelCorreo("Correo: " + gestionUsuario.userLogin().getCuenta().getCorreo());
        panelVenta.getPanelPerfil().setLabelDireccionEnvio("Dirección de envío: " + gestionUsuario.userLogin().getDireccionEnvio());
        panelVenta.getPanelPerfil().setLabelTelefono("Teléfono: " + gestionUsuario.userLogin().getTelefono());
        panelVenta.getPanelPerfil().setLabelTipoUsuario("Tipo de usuario: " + gestionUsuario.userLogin().getTipoCliente());
        panelVenta.setLabelNombreUsuario(gestionUsuario.userLogin().getNombre());
        panelVenta.activarPanelPerfil();
    }

    public void activarCarrito() {
        panelVenta.activarPanelCarrito();
        panelVenta.getPanelCarrito().anadirProductosPanel(gestionUsuario.userLogin().getCarrito().getLibros());
        ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
        panelVenta.getPanelCarrito().modificarValores(valorCompra);
    }

    public void activarPanelCompras() {
        panelVenta.activarPanelCompras();
    }

    public void activarPanelRegistrarLibros() {
        panelVenta.activarPanelRegistrarLibros();
    }

    public void activarPanelRegistrarUsuario() {
        panelVenta.activarPanelRegistrarUsuario();
    }

    public void activarCancelarRegistroLibro() {
        panelVenta.activarCancelarRegistroLibro();
    }

    public void activarCancelarRegistroUsuario() {
        panelVenta.activarCancelarRegistroUsuario();
    }

    public void activarPanelModificarLibro() {
        String[] titulosLibros = gestionLibro.obtenerUsuarios();
        panelVenta.getPanelModificarLibro().listarLibros(titulosLibros);
        panelVenta.activarPanelModificarLibro();
    }

    public void llenarCamposModificarLibros(String tituloLibro) {
        Libro libro = gestionLibro.buscarLibro(tituloLibro);
        panelVenta.getPanelModificarLibro().setISBN(libro.getIsbn());
        panelVenta.getPanelModificarLibro().setNombre(libro.getTitulo());
        panelVenta.getPanelModificarLibro().setAutor(libro.getAutor());
        panelVenta.getPanelModificarLibro().setAnoPublicacion(libro.getAnioPublicacion() == 0 ? "" : String.valueOf(libro.getAnioPublicacion()));
        panelVenta.getPanelModificarLibro().setCategoria(libro.getCategoria());
        panelVenta.getPanelModificarLibro().setEditorial(libro.getEditorial());
        panelVenta.getPanelModificarLibro().setNumeroPaginas(libro.getNumeroPaginas() == 0 ? "" : String.valueOf(libro.getNumeroPaginas()));
        panelVenta.getPanelModificarLibro().setPrecio(String.valueOf((int) libro.getPrecioVenta()));
        panelVenta.getPanelModificarLibro().setCantidad(String.valueOf(libro.getStockDisponible()));
        panelVenta.getPanelModificarLibro().setFormato(libro.getTipoLibro());
    }

    public void activarCancelarModificacionLibro() {
        panelVenta.activarCancelarModificacionLibro();
    }

    public void activarModificarDatosUsuario() {
        PanelModificarUsuario panelModificarUsuario = panelVenta.getPanelModificarUsuario();
        Usuario usuario = gestionUsuario.userLogin();
        panelModificarUsuario.setTxtNombre(usuario.getNombre());
        panelModificarUsuario.setTxtCorreo(usuario.getCuenta().getCorreo());
        panelModificarUsuario.setTxtContrasena(usuario.getCuenta().getContrasena());
        panelModificarUsuario.setTxtDireccion(usuario.getDireccionEnvio());
        panelModificarUsuario.setTxtTelefono(String.valueOf(usuario.getTelefono()));
        panelModificarUsuario.setCbTipoCliente(usuario.getTipoCliente());
        panelVenta.activarActualizarDatosUsuario();
    }

    public void limpiarTxtFieldsLibro() {
        panelVenta.getPanelRegistrarLibro().setTxtNombre("");
        panelVenta.getPanelRegistrarLibro().setTxtIsbn("");
        panelVenta.getPanelRegistrarLibro().setTxtAutor("");
        panelVenta.getPanelRegistrarLibro().setTxtAnoPublicacion("");
        panelVenta.getPanelRegistrarLibro().setTxtEditorial("");
        panelVenta.getPanelRegistrarLibro().setTxtCategoria("");
        panelVenta.getPanelRegistrarLibro().setTxtNumeroPaginas("");
        panelVenta.getPanelRegistrarLibro().setTxtPrecio("");
        panelVenta.getPanelRegistrarLibro().setTxtCantidad("");
        panelVenta.getPanelRegistrarLibro().setTxtFormato("");
    }

    public void limpiarTxtActualizarLibro() {
        panelVenta.getPanelActualizarUsuario().setTxtNombre("");
        panelVenta.getPanelActualizarUsuario().setTxtTelefono("");
        panelVenta.getPanelActualizarUsuario().setTxtDireccion("");
        panelVenta.getPanelActualizarUsuario().setCbTipoCliente("");
        panelVenta.getPanelActualizarUsuario().setTxtCorreo("");
        panelVenta.getPanelActualizarUsuario().setTxtContrasena("");
    }

    public void activarCancelarActualizarUser() {
        panelVenta.activarCancelarActualizarUser();
    }

    public void limpiarTxtFieldsUsuario() {
        panelVenta.getPanelRegistrarUsuario().setTxtNombre("");
        panelVenta.getPanelRegistrarUsuario().setTxtCorreo("");
        panelVenta.getPanelRegistrarUsuario().setTxtContrasena("");
        panelVenta.getPanelRegistrarUsuario().setTxtDireccion("");
        panelVenta.getPanelRegistrarUsuario().setTxtTelefono("");
    }

    public void limpiarTxtLogin() {
        panelInicioSesion.getTxtCorreo().setText("");
        panelInicioSesion.getTxtContrasena().setText("");
    }

    public void activarFuncionRegistrarUsuario() {
        try {
            String nombre = panelVenta.getPanelRegistrarUsuario().getTxtNombre();
            String direccion = panelVenta.getPanelRegistrarUsuario().getTxtDireccion();
            String telefono = panelVenta.getPanelRegistrarUsuario().getTxtTelefono();
            String tipoCliente = panelVenta.getPanelRegistrarUsuario().getCbTipoCliente();
            String correo = panelVenta.getPanelRegistrarUsuario().getTxtCorreo();
            String contrasena = panelVenta.getPanelRegistrarUsuario().getTxtContrasena();
            gestionUsuario.registrarUsuario(nombre, direccion, telefono, tipoCliente, correo, contrasena);
            JOptionPane.showMessageDialog(panelVenta.getPanelRegistrarUsuario(), "Usuario Registrado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            limpiarTxtFieldsUsuario();
            panelVenta.getPanelRegistrarUsuario().setVisible(false);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelRegistrarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarEliminarLibros() {
        panelVenta.activarEliminarLibros();
    }

    public void activarAceptarActualizarUser() {
        Usuario usuario = new Usuario();
        try {
            usuario.setNombre(panelVenta.getPanelModificarUsuario().getTxtNombre());
            usuario.setDireccionEnvio(panelVenta.getPanelModificarUsuario().getTxtDireccion());
            usuario.setTelefono(Long.parseLong(panelVenta.getPanelModificarUsuario().getTxtTelefono()));
            usuario.setTipoCliente(panelVenta.getPanelModificarUsuario().getCbTipoCliente());
            usuario.getCuenta().setCorreo(panelVenta.getPanelModificarUsuario().getTxtCorreo());
            usuario.getCuenta().setContrasena(panelVenta.getPanelModificarUsuario().getTxtContrasena());
            usuario.getCuenta().setLog(true);
            gestionUsuario.modificarUsuario(usuario);
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), "Usuario Modificado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarTxtActualizarLibro();
            activarPanelPerfil();

            panelVenta.getPanelModificarUsuario().setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void activarFuncionModificarLibro() {
        String isbn = panelVenta.getPanelModificarLibro().getISBN();
        String titulo = panelVenta.getPanelModificarLibro().getNombre();
        String autor = panelVenta.getPanelModificarLibro().getAutor();
        String anoPublicacion = panelVenta.getPanelModificarLibro().getAnoPublicacion();
        String categoria = panelVenta.getPanelModificarLibro().getCategoria();
        String editorial = panelVenta.getPanelModificarLibro().getEditorial();
        String numeroPaginas = panelVenta.getPanelModificarLibro().getNumeroPaginas();
        String precio = panelVenta.getPanelModificarLibro().getPrecio();
        String cantidad = panelVenta.getPanelModificarLibro().getCantidad();
        TipoLibro tipoLibro = panelVenta.getPanelModificarLibro().getFormato();
        try {
            gestionLibro.modificarLibro(isbn, titulo, autor, anoPublicacion, categoria, editorial, numeroPaginas, precio, cantidad, tipoLibro);
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), "Libro Modificado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            String[] titulosLibros = gestionLibro.obtenerUsuarios();
            panelVenta.getPanelModificarLibro().listarLibros(titulosLibros);
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarFuncionRegistrarLibro() {
        try {
            String isbn = panelVenta.getPanelRegistrarLibro().getTxtIsbn();
            String titulo = panelVenta.getPanelRegistrarLibro().getTxtNombre();
            String autor = panelVenta.getPanelRegistrarLibro().getTxtAutor();
            String anoPublicacion = panelVenta.getPanelRegistrarLibro().getTxtAnoPublicacion();
            String categoria = panelVenta.getPanelRegistrarLibro().getTxtCategoria();
            String editorial = panelVenta.getPanelRegistrarLibro().getTxtEditorial();
            String numeroPaginas = panelVenta.getPanelRegistrarLibro().getTxtNumeroPaginas();
            String precio = panelVenta.getPanelRegistrarLibro().getTxtPrecio();
            String cantidad = panelVenta.getPanelRegistrarLibro().getTxtCantidad();
            TipoLibro tipoLibro = panelVenta.getPanelRegistrarLibro().getTxtFormato();
            gestionLibro.registrarLibro(isbn, titulo, autor, anoPublicacion, categoria, editorial, numeroPaginas, precio, cantidad, tipoLibro);
            limpiarTxtFieldsLibro();
            panelVenta.getPanelRegistrarLibro().setVisible(false);
        } catch (IllegalArgumentException | IOException e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //------------------Metodos de Gestion de catalogo--------------

    public void activarPanelCatalogo() {
        try {
            panelVenta.getPanelCatalogo().crearPanelesLibros(gestionCatalogo.listarLibros());
            panelVenta.activarPanelCatalogo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // -----------------------------Metodos Gestion Carrito------------
    public void anadirProductosCarrito(String titulo, int cantidad) {
        try {
            gestionCarrito.anadirLibrosCarrito(titulo, cantidad);
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sumarProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            double subtotal = gestionCarrito.sumarProducto(producto);
            panelProducto.actualizarPrecio(subtotal);
            panelProducto.actualizarCantidad(producto.getStockReservado());
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            panelVenta.getPanelCarrito().repaintPanel(valorCompra);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelCarrito(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void disminuirProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            double subtotal = gestionCarrito.disminuirProducto(producto);
            panelProducto.actualizarPrecio(subtotal);
            panelProducto.actualizarCantidad(producto.getStockReservado());
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            panelVenta.getPanelCarrito().repaintPanel(valorCompra);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelCarrito(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void eliminarProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            gestionCarrito.eliminarProducto(producto);
            panelVenta.getPanelCarrito().getListPanelesProductos().remove(panelProducto);
            panelVenta.getPanelCarrito().eliminarPanelProducto(panelProducto);
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            panelVenta.getPanelCarrito().repaintPanel(valorCompra);
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelCarrito(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


}