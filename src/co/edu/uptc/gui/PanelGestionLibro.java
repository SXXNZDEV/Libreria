package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelGestionLibro extends JPanel {

    private JButton botonRegistrar, botonModificar, botonEliminar, botonRegresar;


    public PanelGestionLibro(Eventos eventos) {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        botonRegistrar = new JButton("Registrar Libro");
        botonRegistrar.addActionListener(eventos);
        botonRegistrar.setActionCommand(eventos.REGISTRAR_LIBRO);
        botonModificar = new JButton("Modificar Libro");
        botonModificar.addActionListener(eventos);
        botonModificar.setActionCommand(eventos.MODIFICAR_LIBRO);
        botonEliminar = new JButton("Eliminar Libro");
        botonRegresar = new JButton("Regresar");
        botonRegresar.addActionListener(eventos);
        botonRegresar.setActionCommand(eventos.REGRESAR);
        botonEliminar.addActionListener(eventos);
        botonEliminar.setActionCommand(eventos.ELIMINAR_LIBRO);


        add(botonRegistrar, gbc);
        gbc.gridy = 1;
        add(botonModificar, gbc);
        gbc.gridy = 2;
        add(botonEliminar, gbc);
        gbc.gridy = 3;
        add(botonRegresar, gbc);
    }

}
