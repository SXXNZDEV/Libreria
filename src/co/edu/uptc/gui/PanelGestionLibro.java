package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelGestionLibro extends JPanel {

    private JButton botonRegistrar, botonModificar, botonEliminar;


    public PanelGestionLibro(Evento evento) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        botonRegistrar = new JButton("Registrar Libro");
        botonRegistrar.addActionListener(evento);
        botonRegistrar.setActionCommand(evento.VENTANA_REGISTRAR_LIBRO);
        botonModificar = new JButton("Modificar Libro");
        botonModificar.addActionListener(evento);
        botonModificar.setActionCommand(evento.VENTANA_MODIFICAR_LIBRO);
        botonEliminar = new JButton("Eliminar Libro");
        botonEliminar.addActionListener(evento);
        botonEliminar.setActionCommand(evento.ELIMINAR_LIBRO);


        add(botonRegistrar, gbc);
        gbc.gridy = 1;
        add(botonModificar, gbc);
        gbc.gridy = 2;
        add(botonEliminar, gbc);
    }

}
