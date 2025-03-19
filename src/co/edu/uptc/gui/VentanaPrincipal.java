package co.edu.uptc.gui;

import co.edu.uptc.negocio.GestionUsuario;
import co.edu.uptc.negocio.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;

    private Eventos eventos;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelVenta panelVenta;
    private GestionUsuario gestionUsuario;


    public VentanaPrincipal() {
        super("Librería Virtual");
        setLayout(new BorderLayout());

        eventos = new Eventos(this);
        cardLayout = new CardLayout();

        gestionUsuario = new GestionUsuario();
        panelCL = new JPanel(cardLayout);
        panelVenta = new PanelVenta(eventos);
        panelInicioSesion = new PanelInicioSesion(eventos);

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
        JOptionPane.showMessageDialog(this, "Gestion Libros y Registrar Usuarios aparecerán cuando el Adiminstrador inicie sesión, cuando el usuario inicie sesion estos botones se deshabilitaran");
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
            cardLayout.show(panelCL, "Panel Venta");
            panelVenta.activarPanelCatalogo();
            panelVenta.setLabelNombreUsuario(gestionUsuario.userLogin().getNombre());
            if (gestionUsuario.validarLoginAdmin()) {
                panelVenta.anadirFuncionesAdmin();
            } else {
                panelVenta.quitarFuncionesAdmin();
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelInicioSesion, e.getMessage(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void activarPanelCatalogo() {
        panelVenta.activarPanelCatalogo();
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
        panelVenta.activarPanelPerfil();
    }

    public void activarCarrito() {
        panelVenta.activarPanelCarrito();
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
        panelVenta.activarPanelModificarLibro();
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
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), "Usuario Modificado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            panelVenta.getPanelModificarUsuario().setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelVenta.getPanelModificarUsuario(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void activarFuncionModificarLibro() {
        panelVenta.activarFuncionModificarLibro();
    }

    public void activarFuncionRegistrarLibro() {
        panelVenta.activarFuncionRegistrarLibro();
    }

}