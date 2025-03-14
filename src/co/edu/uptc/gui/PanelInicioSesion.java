package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelInicioSesion extends JPanel {

    private JTextField txtCorreo, txtContrasena;
    private JLabel labelTitulo, labelIniciarSesion, labelTexto, labelCorreo, labelContrasena;
    private JButton botonContinuar, botonCancelar, botonCrearCuenta;

    public PanelInicioSesion(Eventos eventos) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 1, 60, 1);

        Dimension dimensiontxt = new Dimension(500, 25);
        Font letra = new Font("Arial", Font.BOLD, 20);

        labelTitulo = new JLabel("Librería Virtual", SwingConstants.CENTER);
        labelIniciarSesion = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        labelTexto = new JLabel("o crea una cuenta");
        labelContrasena = new JLabel("Contraseña: ");
        labelCorreo = new JLabel("Correo: ");
        txtCorreo = new JTextField("Email", 25);
        txtContrasena = new JTextField("Contraseña", 25);
        txtCorreo.setPreferredSize(dimensiontxt);
        txtContrasena.setPreferredSize(dimensiontxt);

        txtContrasena.selectAll();
        txtCorreo.selectAll();

        botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(eventos);
        botonContinuar.setActionCommand(eventos.CONTINUAR_INICIAR_SESION);
        botonCancelar  = new JButton("Cancelar");
        botonCancelar.addActionListener(eventos);
        botonCancelar.setActionCommand(eventos.REGRESAR);
        botonCrearCuenta = new JButton("Crear Cuenta");
        botonCrearCuenta.addActionListener(eventos);
        botonCrearCuenta.setActionCommand(eventos.REGISTRAR_USUARIO);


        labelTitulo.setFont(letra);
        labelIniciarSesion.setFont(new Font("Arial", Font.BOLD, 15));

        add(labelTitulo, gbc);
        gbc.gridy = 1;
        gbc.insets.bottom = 5;
        add(labelIniciarSesion, gbc);
        gbc.gridy = 2;
        add(labelCorreo, gbc);
        gbc.gridy = 3;
        add(txtCorreo, gbc);
        gbc.gridy = 4;
        add(labelContrasena, gbc);
        gbc.gridy = 5;
        add(txtContrasena, gbc);
        gbc.gridy = 6;
        add(botonContinuar, gbc);
        gbc.gridy = 7;
        add(botonCancelar, gbc);
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        add(labelTexto, gbc);
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(botonCrearCuenta, gbc);
    }
}
