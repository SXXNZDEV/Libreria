package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicioSesion panelInicioSesion;

    private Eventos eventos;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelVenta panelVenta;


    public VentanaPrincipal() {
        super("Librería Virtual");
        setLayout(new BorderLayout());

        eventos = new Eventos(this);
        cardLayout = new CardLayout();

        panelCL = new JPanel(cardLayout);
        panelVenta = new PanelVenta(eventos);
        panelInicioSesion = new PanelInicioSesion(eventos);

        panelCL.add(panelInicioSesion, "Iniciar Sesion");
        panelCL.add(panelVenta, "Continuar");

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
        JOptionPane.showMessageDialog(this, "En este caso esta opcion aparece cuando el Administrador inicia sesion, cuando un usuario inicia sesión automáticamente se deshabilita", "Mensaje de Información", 1);
        panelVenta.activarPanelGestionLibro();
    }

    public void activarFuncionRegresar() {
        System.exit(0);
    }

    public void activarPanelVenta() {
        cardLayout.show(panelCL, "Continuar");
    }

    public void activarPanelCatalogo() {
        panelVenta.activarPanelCatalogo();
    }

    public void activarCerrarSesion() {
        cardLayout.first(panelCL);
    }

    public void activarPanelPerfil() {
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

    public void activarActualizarDatosUsuario() {
        panelVenta.activarActualizarDatosUsuario();
    }

    public void activarCancelarActualizarUser() {
        panelVenta.activarCancelarActualizarUser();
    }

    public void activarFuncionRegistrarUsuario() {
        panelVenta.activarFuncionRegistrarUsuario();
    }

    public void activarEliminarLibros() {
        panelVenta.activarEliminarLibros();
    }

    public void activarAceptarActualizarUser() {
        panelVenta.activarAceptarActualizarUser();
    }

    public void activarFuncionModificarLibro() {
        panelVenta.activarFuncionModificarLibro();
    }

    public void activarFuncionRegistrarLibro() {
        panelVenta.activarFuncionRegistrarLibro();
    }
}