package co.edu.uptc.gui;

import co.edu.uptc.negocio.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;

    private Evento evento;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private MenuPrincipal menuPrincipal;
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

        menuPrincipal = new MenuPrincipal(evento, eventoLista, this);
        panelInicioSesion = new PanelInicioSesion(evento);

        panelCL.add(panelInicioSesion, "Iniciar Sesion");
        panelCL.add(menuPrincipal, "Panel Venta");

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
        menuPrincipal.activarPanelGestionLibro();
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
            menuPrincipal.getPanelCatalogo().crearPanelesLibros(gestionCatalogo.listarLibros());
            cardLayout.show(panelCL, "Panel Venta");
            menuPrincipal.activarPanelCatalogo();
            menuPrincipal.setLabelNombreUsuario(gestionUsuario.userLogin().getNombre());
            if (gestionUsuario.validarLoginAdmin()) {
                menuPrincipal.anadirFuncionesAdmin();
            } else {
                menuPrincipal.quitarFuncionesAdmin();
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
            JOptionPane.showMessageDialog(menuPrincipal, e.getMessage(), "Cerrar Sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarPanelPerfil() {
        menuPrincipal.getPanelPerfil().setLabelNombre("Nombre: " + gestionUsuario.userLogin().getNombre());
        menuPrincipal.getPanelPerfil().setLabelCorreo("Correo: " + gestionUsuario.userLogin().getCuenta().getCorreo());
        menuPrincipal.getPanelPerfil().setLabelDireccionEnvio("Dirección de envío: " + gestionUsuario.userLogin().getDireccionEnvio());
        menuPrincipal.getPanelPerfil().setLabelTelefono("Teléfono: " + gestionUsuario.userLogin().getTelefono());
        menuPrincipal.getPanelPerfil().setLabelTipoUsuario("Tipo de usuario: " + gestionUsuario.userLogin().getTipoCliente());
        menuPrincipal.setLabelNombreUsuario(gestionUsuario.userLogin().getNombre());
        menuPrincipal.activarPanelPerfil();
    }

    public void activarCarrito() {
        menuPrincipal.activarPanelCarrito();
        menuPrincipal.getPanelCarrito().anadirProductosPanel(gestionUsuario.userLogin().getCarrito().getLibros());
        ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
        menuPrincipal.getPanelCarrito().modificarValores(valorCompra);
    }

    public void activarPanelCompras() {
        menuPrincipal.activarPanelCompras();
    }

    public void activarPanelRegistrarLibros() {
        menuPrincipal.activarPanelRegistrarLibros();
    }

    public void activarPanelRegistrarUsuario() {
        menuPrincipal.activarPanelRegistrarUsuario();
    }

    public void activarCancelarRegistroLibro() {
        menuPrincipal.getPanelRegistrarLibro().limpiarTxt();
        menuPrincipal.activarCancelarRegistroLibro();
    }

    public void activarCancelarRegistroUsuario() {
        menuPrincipal.getPanelRegistrarUsuario().limpiarTxt();
        menuPrincipal.activarCancelarRegistroUsuario();
    }

    public void activarPanelModificarLibro() {
        String[] titulosLibros = gestionLibro.obtenerUsuarios();
        menuPrincipal.getPanelModificarLibro().listarLibros(titulosLibros);
        menuPrincipal.activarPanelModificarLibro();
    }

    public void llenarCamposModificarLibros(String tituloLibro) {
        Libro libro = gestionLibro.buscarLibro(tituloLibro);
        menuPrincipal.getPanelModificarLibro().setISBN(libro.getIsbn());
        menuPrincipal.getPanelModificarLibro().setNombre(libro.getTitulo());
        menuPrincipal.getPanelModificarLibro().setAutor(libro.getAutor());
        menuPrincipal.getPanelModificarLibro().setAnoPublicacion(libro.getAnioPublicacion() == 0 ? "" : String.valueOf(libro.getAnioPublicacion()));
        menuPrincipal.getPanelModificarLibro().setCategoria(libro.getCategoria());
        menuPrincipal.getPanelModificarLibro().setEditorial(libro.getEditorial());
        menuPrincipal.getPanelModificarLibro().setNumeroPaginas(libro.getNumeroPaginas() == 0 ? "" : String.valueOf(libro.getNumeroPaginas()));
        menuPrincipal.getPanelModificarLibro().setPrecio(String.valueOf((int) libro.getPrecioVenta()));
        menuPrincipal.getPanelModificarLibro().setCantidad(String.valueOf(libro.getStockDisponible()));
        menuPrincipal.getPanelModificarLibro().setFormato(libro.getTipoLibro());
    }

    public void activarCancelarModificacionLibro() {
        menuPrincipal.activarCancelarModificacionLibro();
    }

    public void activarModificarDatosUsuario() {
        PanelModificarUsuario panelModificarUsuario = menuPrincipal.getPanelModificarUsuario();
        Usuario usuario = gestionUsuario.userLogin();
        panelModificarUsuario.setTxtNombre(usuario.getNombre());
        panelModificarUsuario.setTxtCorreo(usuario.getCuenta().getCorreo());
        panelModificarUsuario.setTxtContrasena(usuario.getCuenta().getContrasena());
        panelModificarUsuario.setTxtDireccion(usuario.getDireccionEnvio());
        panelModificarUsuario.setTxtTelefono(String.valueOf(usuario.getTelefono()));
        panelModificarUsuario.setCbTipoCliente(usuario.getTipoCliente());
        menuPrincipal.activarActualizarDatosUsuario();
    }

    public void limpiarTxtFieldsLibro() {
        menuPrincipal.getPanelRegistrarLibro().setTxtNombre("");
        menuPrincipal.getPanelRegistrarLibro().setTxtIsbn("");
        menuPrincipal.getPanelRegistrarLibro().setTxtAutor("");
        menuPrincipal.getPanelRegistrarLibro().setTxtAnoPublicacion("");
        menuPrincipal.getPanelRegistrarLibro().setTxtEditorial("");
        menuPrincipal.getPanelRegistrarLibro().setTxtCategoria("");
        menuPrincipal.getPanelRegistrarLibro().setTxtNumeroPaginas("");
        menuPrincipal.getPanelRegistrarLibro().setTxtPrecio("");
        menuPrincipal.getPanelRegistrarLibro().setTxtCantidad("");
        menuPrincipal.getPanelRegistrarLibro().setTxtFormato("");
    }

    public void limpiarTxtActualizarLibro() {
        menuPrincipal.getPanelActualizarUsuario().setTxtNombre("");
        menuPrincipal.getPanelActualizarUsuario().setTxtTelefono("");
        menuPrincipal.getPanelActualizarUsuario().setTxtDireccion("");
        menuPrincipal.getPanelActualizarUsuario().setCbTipoCliente("");
        menuPrincipal.getPanelActualizarUsuario().setTxtCorreo("");
        menuPrincipal.getPanelActualizarUsuario().setTxtContrasena("");
    }

    public void activarCancelarActualizarUser() {
        menuPrincipal.activarCancelarActualizarUser();
    }

    public void limpiarTxtLogin() {
        panelInicioSesion.getTxtCorreo().setText("");
        panelInicioSesion.getTxtContrasena().setText("");
    }

    public void activarFuncionRegistrarUsuario() {
        try {
            Usuario usuario = menuPrincipal.getPanelRegistrarUsuario().obtenerDatos();
            gestionUsuario.registrarUsuario(usuario);
            JOptionPane.showMessageDialog(menuPrincipal.getPanelRegistrarUsuario(), "Usuario Registrado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            menuPrincipal.getPanelRegistrarUsuario().limpiarTxt();
            menuPrincipal.getPanelRegistrarUsuario().setVisible(false);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelRegistrarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarEliminarLibros() {
        try {
            menuPrincipal.activarEliminarLibros();
            menuPrincipal.getPanelEliminarLibro().crearPanelesLibros(gestionCatalogo.listarLibros());
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelEliminarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarFuncionEliminarLibros() {
        try {
            ArrayList<PanelLibroEliminar> listPaneles = menuPrincipal.getPanelEliminarLibro().getListPanelesLibros();
            gestionLibro.eliminarLibros(listPaneles);
            menuPrincipal.getPanelEliminarLibro().repintarPanelLibros();
            menuPrincipal.getPanelEliminarLibro().crearPanelesLibros(gestionCatalogo.listarLibros());
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelEliminarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarAceptarActualizarUser() {
        try {
            Usuario usuario = menuPrincipal.getPanelModificarUsuario().obtenerDatos();
            usuario.getCuenta().setLog(true);
            gestionUsuario.modificarUsuario(usuario);
            JOptionPane.showMessageDialog(menuPrincipal.getPanelModificarUsuario(), "Usuario Modificado Exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarTxtActualizarLibro();
            activarPanelPerfil();

            menuPrincipal.getPanelModificarUsuario().setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelModificarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void activarFuncionModificarLibro() {
        try {
            Libro libro = menuPrincipal.getPanelModificarLibro().obtenerDatos();
            gestionLibro.modificarLibro(libro);
            JOptionPane.showMessageDialog(menuPrincipal.getPanelModificarLibro(), "Libro Modificado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            String[] titulosLibros = gestionLibro.obtenerUsuarios();
            menuPrincipal.getPanelModificarLibro().listarLibros(titulosLibros);
        } catch (IOException | RuntimeException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelModificarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarFuncionRegistrarLibro() {
        try {
            Libro libro = menuPrincipal.getPanelRegistrarLibro().obtenerDatos();
            gestionLibro.registrarLibro(libro);
            limpiarTxtFieldsLibro();
            menuPrincipal.getPanelRegistrarLibro().setVisible(false);
        } catch (RuntimeException | IOException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //------------------Metodos de Gestion de catalogo--------------

    public void activarPanelCatalogo() {
        try {
            menuPrincipal.getPanelCatalogo().crearPanelesLibros(gestionCatalogo.listarLibros());
            menuPrincipal.activarPanelCatalogo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // -----------------------------Metodos Gestion Carrito------------
    public void anadirProductosCarrito(Libro libro, int cantidad, PanelLibro panelLibro) {
        try {
            gestionCarrito.anadirLibrosCarrito(libro, cantidad);
            panelLibro.habilitacionBoton(gestionLibro.validarExistencia(libro));
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelRegistrarLibro(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sumarProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            double subtotal = gestionCarrito.sumarProducto(producto);
            panelProducto.actualizarPrecio(subtotal);
            panelProducto.actualizarCantidad(producto.getStockReservado());
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            menuPrincipal.getPanelCarrito().repaintPanel(valorCompra);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelCarrito(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void disminuirProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            double subtotal = gestionCarrito.disminuirProducto(producto);
            panelProducto.actualizarPrecio(subtotal);
            panelProducto.actualizarCantidad(producto.getStockReservado());
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            menuPrincipal.getPanelCarrito().repaintPanel(valorCompra);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelCarrito(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void eliminarProductoCarrito(Libro producto, PanelProducto panelProducto) {
        try {
            gestionCarrito.eliminarProducto(producto);
            menuPrincipal.getPanelCarrito().getListPanelesProductos().remove(panelProducto);
            menuPrincipal.getPanelCarrito().eliminarPanelProducto(panelProducto);
            ValorCompra valorCompra = gestionCarrito.calculoResumenCompra();
            menuPrincipal.getPanelCarrito().repaintPanel(valorCompra);
        } catch (IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(menuPrincipal.getPanelCarrito(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activarCancelarEliminarLibro() {
        menuPrincipal.activarPanelGestionLibro();
    }

    public void activarPanelConfirmCompra() {
        menuPrincipal.activarPanelConfirmCompra();
    }
    //TODO implementar que cuando inicie la app se muestre la interfaz de Catalogo con un usuario 'user_default' y que haya un boton arriba de cerrar sesión que sea el de iniciar sesión...:) y pues también la lógica obviamente
}