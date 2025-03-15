package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelVenta extends JPanel {

    private JLabel labelTituloIzquierda;
    private JButton botonCatalogo;
    private JButton botonCarrito;
    private JButton botonCompras;
    private JButton botonPerfil;
    private JButton botonCerrarSesion;
    private PanelCatalogo panelCatalogo;
    private PanelCarrito panelCarrito;
    private PanelPerfil panelPerfil;
    private JScrollPane scrollCatalogo;
    private JScrollPane scrollTabla;
    private CardLayout cardLayout;
    private JPanel panelCL;
    private PanelCompras panelCompras;


    public PanelVenta(Eventos eventos) {
        setLayout(new BorderLayout());
        scrollCatalogo = new JScrollPane(panelCatalogo);

        panelCatalogo = new PanelCatalogo();
        scrollCatalogo = new JScrollPane(panelCatalogo);
        scrollCatalogo.getVerticalScrollBar().setUnitIncrement(20);

        panelPerfil = new PanelPerfil(eventos);

        panelCarrito = new PanelCarrito();

        panelCompras = new PanelCompras();
        scrollTabla = new JScrollPane(panelCompras);

        cardLayout = new CardLayout();
        panelCL = new JPanel(cardLayout);
        panelCL.add(scrollCatalogo, "Catalogo");
        panelCL.add(panelPerfil, "Perfil");
        panelCL.add(panelCarrito, "Carrito");
        panelCL.add(scrollTabla, "Compras");

        add(panelIzquierda(eventos), BorderLayout.WEST);
        add(panelCL, BorderLayout.CENTER);
    }

    public JPanel panelIzquierda(Eventos eventos) {
        JPanel panelIzquierda = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        labelTituloIzquierda = new JLabel("Libreria Virtual");
        botonCatalogo = new JButton("Catalogo");
        botonCarrito = new JButton("Mi carrito");
        botonCompras = new JButton("Mis compras");
        botonPerfil = new JButton("Perfil");
        botonCerrarSesion = new JButton("Cerrar Sesión");

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

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 5, 5);
        panelIzquierda.add(labelTituloIzquierda, gbc);
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
        gbc.gridy = 5;
        panelIzquierda.add(new JLabel(), gbc);
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.SOUTH;
        panelIzquierda.add(botonCerrarSesion, gbc);
        panelIzquierda.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
        return panelIzquierda;
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

    public void activarPanelTabla() {
        cardLayout.show(panelCL, "Compras");
    }
}
