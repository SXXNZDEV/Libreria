package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelVenta extends JPanel {

    private JLabel labelTituloMenu;
    private JLabel labelNombreUsuario;
    private JButton botonCatalogo;
    private JButton botonCarrito;
    private JButton botonCompras;
    private JButton botonPerfil;
    private JButton botonCerrarSesion;
    private JButton botonGestionarLibros;
    private JButton botonRegistrarUsuario;
    private PanelCatalogo panelCatalogo;
    private PanelCarrito panelCarrito;
    private PanelPerfil panelPerfil;
    private JScrollPane scrollTabla;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelCompras panelCompras;
    private PanelGestionLibro panelGestionLibro;
    private PanelRegistrarLibro panelRegistrarLibro;
    private PanelRegistrarUsuario panelRegistrarUsuario;
    private PanelModificarLibro panelModificarLibro;
    private PanelModificarUsuario panelModificarUsuario;
    private GridBagConstraints gbc;
    private JPanel panelIzquierda;

    public PanelCompras getPanelCompras() {
        return panelCompras;
    }

    public PanelGestionLibro getPanelGestionLibro() {
        return panelGestionLibro;
    }

    public PanelRegistrarLibro getPanelRegistrarLibro() {
        return panelRegistrarLibro;
    }

    public PanelRegistrarUsuario getPanelRegistrarUsuario() {
        return panelRegistrarUsuario;
    }

    public PanelModificarLibro getPanelModificarLibro() {
        return panelModificarLibro;
    }

    public PanelModificarUsuario getPanelModificarUsuario() {
        return panelModificarUsuario;
    }

    public PanelModificarUsuario getPanelActualizarUsuario() {
        return panelModificarUsuario;
    }

    public PanelPerfil getPanelPerfil() {
        return panelPerfil;
    }

    public PanelCatalogo getPanelCatalogo() {
        return panelCatalogo;
    }

    public void setLabelNombreUsuario(String nombreUsuario) {
        labelNombreUsuario.setText(nombreUsuario);
    }

    public PanelVenta(Eventos eventos, EventosItemListener eventosItemListener, VentanaPrincipal ventanaPrincipal) {
        setLayout(new BorderLayout());
        panelIzquierda = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        panelCatalogo = new PanelCatalogo(ventanaPrincipal);


        panelPerfil = new PanelPerfil(eventos);
        panelCarrito = new PanelCarrito();
        panelCompras = new PanelCompras();
        scrollTabla = new JScrollPane(panelCompras);
        panelGestionLibro = new PanelGestionLibro(eventos);
        panelRegistrarLibro = new PanelRegistrarLibro(eventos);
        panelRegistrarUsuario = new PanelRegistrarUsuario(eventos);
        panelModificarLibro = new PanelModificarLibro(eventos, eventosItemListener);
        panelModificarUsuario = new PanelModificarUsuario(eventos);


        panelGestionLibro = new PanelGestionLibro(eventos);

        cardLayout = new CardLayout();
        panelCL = new JPanel(cardLayout);
        panelCL.add(panelCatalogo, "Catalogo");
        panelCL.add(panelPerfil, "Perfil");
        panelCL.add(panelCarrito, "Carrito");
        panelCL.add(scrollTabla, "Compras");
        panelCL.add(panelGestionLibro, "Gestion Libros");

        add(panelIzquierda(eventos), BorderLayout.WEST);
        add(panelCL, BorderLayout.CENTER);
    }

    public JPanel panelIzquierda(Eventos eventos) {
        panelIzquierda = new JPanel(new GridBagLayout());

        labelTituloMenu = new JLabel("Librería Virtual", SwingUtilities.CENTER);
        labelNombreUsuario = new JLabel("", SwingUtilities.CENTER);
        botonCatalogo = new JButton("Catalogo");
        botonCarrito = new JButton("Mi carrito");
        botonCompras = new JButton("Mis compras");
        botonPerfil = new JButton("Perfil");
        botonCerrarSesion = new JButton("Cerrar Sesión");
        botonGestionarLibros = new JButton("Gestionar Libros");
        botonRegistrarUsuario = new JButton("Registrar Usuario");

        labelNombreUsuario.setForeground(new Color(128, 16, 5));

        botonCatalogo.addActionListener(eventos);
        botonCatalogo.setActionCommand(eventos.CATALOGO);
        botonPerfil.addActionListener(eventos);
        botonPerfil.setActionCommand(eventos.PERFIL);
        botonCarrito.addActionListener(eventos);
        botonCarrito.setActionCommand(eventos.CARRITO);
        botonCerrarSesion.addActionListener(eventos);
        botonCerrarSesion.setActionCommand(eventos.CERRAR_SESION);
        botonCompras.addActionListener(eventos);
        botonCompras.setActionCommand(eventos.COMPRAS);
        botonGestionarLibros.addActionListener(eventos);
        botonGestionarLibros.setActionCommand(eventos.GESTIONAR_LIBROS);
        botonRegistrarUsuario.addActionListener(eventos);
        botonRegistrarUsuario.setActionCommand(eventos.VENTANA_REGISTRAR_USUARIO);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 5, 5);
        panelIzquierda.add(labelTituloMenu, gbc);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 1;
        panelIzquierda.add(botonCatalogo, gbc);
        gbc.gridy = 2;
        panelIzquierda.add(botonCarrito, gbc);
        gbc.gridy = 3;
        panelIzquierda.add(botonCompras, gbc);
        gbc.gridy = 4;
        panelIzquierda.add(botonPerfil, gbc);
        gbc.weighty = 1.0;
        gbc.gridy = 7;
        panelIzquierda.add(new JLabel(), gbc);
        gbc.weighty = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.SOUTH;
        panelIzquierda.add(labelNombreUsuario, gbc);
        gbc.gridy = 9;
        panelIzquierda.add(botonCerrarSesion, gbc);
        panelIzquierda.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
        return panelIzquierda;
    }

    public void anadirFuncionesAdmin() {
        gbc.weighty = 0;
        gbc.gridy = 5;
        panelIzquierda.add(botonGestionarLibros, gbc);
        gbc.gridy = 6;
        panelIzquierda.add(botonRegistrarUsuario, gbc);
        panelIzquierda.revalidate();
        panelIzquierda.repaint();
    }

    public void quitarFuncionesAdmin() {
        panelIzquierda.remove(botonGestionarLibros);
        panelIzquierda.remove(botonRegistrarUsuario);
        panelIzquierda.revalidate();
        panelIzquierda.repaint();
    }

    public void activarPanelCatalogo() {
        cardLayout.show(panelCL, "Catalogo");
    }

    public void activarPanelPerfil() {
        cardLayout.show(panelCL, "Perfil");
    }

    public void activarPanelCarrito() {
        cardLayout.show(panelCL, "Carrito");
    }

    public void activarPanelCompras() {
        cardLayout.show(panelCL, "Compras");
    }

    public void activarPanelGestionLibro() {
        cardLayout.show(panelCL, "Gestion Libros");
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

    public void activarActualizarDatosUsuario() {
        panelModificarUsuario.setVisible(true);
    }

    public void activarCancelarActualizarUser() {
        panelModificarUsuario.setVisible(false);
    }



    public void activarEliminarLibros() {
        JOptionPane.showMessageDialog(this, "Eliminar libros...");
    }


    public void activarFuncionRegistrarLibro() {
        JOptionPane.showMessageDialog(this, "Libro Registrado...");
        panelRegistrarLibro.setVisible(false);
    }
}
