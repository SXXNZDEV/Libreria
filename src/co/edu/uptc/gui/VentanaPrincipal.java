package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelInicio panelInicio;
    private PanelInicioSesion panelInicioSesion;
    private PanelGestionLibro panelGestionLibro;
    private PanelRegistrarLibro panelRegistrarLibro;
    private Eventos eventos;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelRegistrarUsuario panelRegistrarUsuario;
    private PanelModificarLibro panelModificarLibro;
    private PanelVenta panelVenta;
    private JScrollPane scrollPane;


    public VentanaPrincipal() {
        super("Librería Virtual");
        setLayout(new BorderLayout());

        eventos = new Eventos(this);
        cardLayout = new CardLayout();

        panelCL = new JPanel(cardLayout);
        panelInicio = new PanelInicio(eventos);
        panelInicioSesion = new PanelInicioSesion(eventos);
        panelGestionLibro = new PanelGestionLibro(eventos);
        panelRegistrarLibro = new PanelRegistrarLibro(eventos);
        panelRegistrarUsuario = new PanelRegistrarUsuario(eventos);
        panelModificarLibro = new PanelModificarLibro(eventos);
        panelVenta = new PanelVenta(eventos);

        panelCL.add(panelInicio, "Inicio");
        panelCL.add(panelInicioSesion, "Iniciar Sesion");
        panelCL.add(panelGestionLibro, "Gestionar Libros");
        panelCL.add(panelVenta, "Continuar");

        add(panelCL, BorderLayout.CENTER);
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
        cardLayout.show(panelCL, "Gestionar Libros");
    }

    public void activarFuncionRegresar() {
        cardLayout.first(panelCL);
    }

    public void activarPanelRegistrarLibros() {
        panelRegistrarLibro.setVisible(true);
    }

    public void activarPanelRegistrarUsuario() {
        panelRegistrarUsuario.setVisible(true);
    }

    public void activarCancelarRegistroLibro() {
        panelRegistrarLibro.setVisible(false);
    }

    public void activarCancelarRegistroUsuario() {
        panelRegistrarUsuario.setVisible(false);
    }

    public void activarPanelModificarLibro() {
        panelModificarLibro.setVisible(true);
    }

    public void activarCancelarModificacionLibro() {
        panelModificarLibro.setVisible(false);
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
}