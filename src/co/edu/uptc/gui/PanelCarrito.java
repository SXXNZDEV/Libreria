package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelCarrito extends JPanel {

    private JLabel labelTitulo;

    public PanelCarrito(Eventos eventos) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        labelTitulo = new JLabel("Mi carrito");
        Font fontTitulo = new Font("Arial", Font.BOLD, 30);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5,5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(labelTitulo, gbc);

        JPanel[] paneles = crearPaneles();

        for (int i = 0; i < paneles.length; i++) {
            gbc.gridy = i + 1;
            add(paneles[i], gbc);
        }

    }

    public JPanel[] crearPaneles() {
        JPanel[] paneles = new JPanel[3];

        for (int i = 0; i < paneles.length; i++) {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            panel.setBackground(Color.GRAY);

            JLabel labelImagen = new JLabel("IMAGEN");
            JLabel labelNombreProducto = new JLabel("Producto " + i);
            JButton botonAumentar = new JButton("+");
            JButton botonDisminuir = new JButton("-");
            JLabel labelCantidad = new JLabel("0");
            JButton botonEliminar = new JButton("Eliminar");

            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 5, 5, 5);
            panel.add(labelImagen, gbc);
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            gbc.weightx = 1.0;
            panel.add(labelNombreProducto, gbc);
            gbc.gridx = 4;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            panel.add(botonDisminuir, gbc);
            gbc.gridx = 5;
            gbc.gridwidth = 1;
            panel.add(labelCantidad, gbc);
            gbc.gridx = 6;
            gbc.gridwidth = 1;
            panel.add(botonAumentar, gbc);
            gbc.gridx = 7;
            gbc.weightx = 1.0;
            panel.add(botonEliminar);
            paneles[i] = panel;
        }
        return paneles;
    }
}
