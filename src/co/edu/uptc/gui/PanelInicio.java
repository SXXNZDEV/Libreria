package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel{

    private JLabel labelTitulo;
    private JButton botonInciarSesion, botonGestionarLibro;

    public PanelInicio(Eventos eventos) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 1, 60, 1);

        Dimension size = new Dimension(250, 30);
        botonInciarSesion = new JButton(eventos.INICIAR_SESION);
        botonInciarSesion.addActionListener(eventos);
        botonInciarSesion.setActionCommand(eventos.INICIAR_SESION);
        botonGestionarLibro = new JButton(eventos.GESTIONAR_LIBROS);
        botonGestionarLibro.addActionListener(eventos);
        botonGestionarLibro.setActionCommand(eventos.GESTIONAR_LIBROS);
        labelTitulo = new JLabel("Inicio", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 40));
        botonGestionarLibro.setPreferredSize(size);
        botonInciarSesion.setPreferredSize(size);

        setBackground(Color.white);
        add(labelTitulo, gbc);
        gbc.gridy = 1;
        gbc.insets.bottom = 10;
        add(botonInciarSesion, gbc);
        gbc.gridy = 2;
        add(botonGestionarLibro, gbc);
    }
}
