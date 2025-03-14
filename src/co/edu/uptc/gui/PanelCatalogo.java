package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelCatalogo extends JPanel{

    private JLabel labelTitulo;

    public PanelCatalogo() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font font = new Font("Arial", Font.BOLD, 30);
        labelTitulo = new JLabel("Catálogo de Libros");
        labelTitulo.setFont(font);

        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(5, 30, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;

        JPanel[][] paneles = crearPanelesLibros();
        gbc.weighty = 0.9;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;


        for (int i = 0; i < paneles.length; i++) {
            for (int j = 0; j < paneles[i].length; j++) {
                gbc.gridx = j;
                gbc.gridy = i + 1;
                add(paneles[i][j], gbc);
            }
        }
    }

    public JPanel[][] crearPanelesLibros(/*Libro[] libros*/) {
        JPanel[][] paneles = new JPanel[3][3];

        for (int i = 0; i < paneles.length; i++) {
            for (int j = 0; j < 3; j++) {

                JPanel panelLibro = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                panelLibro.setBorder(new LineBorder(Color.GRAY, 1, true));

                JLabel labelImagen = new JLabel("IMAGEN");
                JLabel labelTituloAnio = new JLabel("Titulo-Año");
                JLabel labelAutor = new JLabel("Autor");
                JLabel labelEditorial = new JLabel("Editorial");
                JLabel labelCategoriaPaginas = new JLabel("Categoria-Pag");
                JLabel labelPrecioVenta = new JLabel("Precio");
                JButton botonAgregar = new JButton("Agregar al carrito");

                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.insets = new Insets(2, 0, 2, 0);

                panelLibro.add(labelImagen, gbc);
                gbc.gridy = 1;
                panelLibro.add(labelTituloAnio, gbc);
                gbc.gridy = 2;
                panelLibro.add(labelAutor, gbc);
                gbc.gridy = 3;
                panelLibro.add(labelEditorial, gbc);
                gbc.gridy = 4;
                panelLibro.add(labelCategoriaPaginas, gbc);
                gbc.gridy = 5;
                panelLibro.add(labelPrecioVenta, gbc);
                gbc.gridy = 6;
                panelLibro.add(botonAgregar, gbc);
                paneles[i][j] = panelLibro;
            }
        }
        return paneles;
    }
}
