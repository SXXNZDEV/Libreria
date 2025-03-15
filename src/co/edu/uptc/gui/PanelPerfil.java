package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelPerfil extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelNombre;
    private JLabel labelCorreo;
    private JLabel labelTelefono;
    private JLabel labelDireccionEnvio;
    private JLabel labelTipoUsuario;
    private JButton botonActualizacionDatos;

    public PanelPerfil(Eventos eventos) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        inicializarAtributos();
        botonActualizacionDatos.addActionListener(eventos);
        botonActualizacionDatos.setActionCommand(eventos.ACTUALIZAR_DATOS_USUARIO);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 30, 10, 10);

        cambiarFont();
        add(labelTitulo, gbc);
        gbc.gridy = 3;

        gbc.weighty = 0;

        add(labelNombre, gbc);
        gbc.gridy = 4;
        add(labelCorreo, gbc);
        gbc.gridy = 5;
        add(labelDireccionEnvio, gbc);
        gbc.gridy = 6;
        add(labelTelefono, gbc);
        gbc.gridy = 7;
        add(labelTipoUsuario, gbc);
        gbc.gridy = 8;
        gbc.weighty = 1.0;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        add(botonActualizacionDatos, gbc);
    }

    public void inicializarAtributos() {
        labelTitulo = new JLabel("Mi Perfil");
        labelNombre = new JLabel("Nombre:");
        labelCorreo = new JLabel("Correo:");
        labelTelefono = new JLabel("Teléfono: ");
        labelDireccionEnvio = new JLabel("Dirección de Envío:");
        labelTipoUsuario = new JLabel("Tipo de Usuario:");
        botonActualizacionDatos = new JButton("Actualizar Datos");
    }

    public void cambiarFont() {
        Font fontTitulo = new Font("Arial", Font.BOLD, 40);
        labelTitulo.setFont(fontTitulo);
        Font fontNombre = new Font("Arial", Font.BOLD, 20);
        labelNombre.setFont(fontNombre);
        Font fontAtributos = new Font("Arial", Font.PLAIN, 15);
        labelTelefono.setFont(fontAtributos);
        labelCorreo.setFont(fontAtributos);
        labelDireccionEnvio.setFont(fontAtributos);
        labelTipoUsuario.setFont(fontAtributos);
    }
}
