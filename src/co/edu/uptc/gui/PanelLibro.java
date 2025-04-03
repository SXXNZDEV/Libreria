package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.NumberFormat;

public class PanelLibro extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelAutorEditorial;
    private JLabel labelCategoriaPaginas;
    private JLabel labelPrecio;
    private JButton botonAgregar;
    private EventoCatalogo eventoCatalogo;
    private NumberFormat format;
    private GridBagConstraints gbc;

    public PanelLibro(VentanaPrincipal ventanaPrincipal, Libro libro) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setBorder(new LineBorder(Color.WHITE, 1, true));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(20, 150));

        modificarAtributos(libro);
        personalizar();
        this.eventoCatalogo = new EventoCatalogo(ventanaPrincipal, libro, this);

        botonAgregar.addActionListener(eventoCatalogo);
        botonAgregar.setActionCommand(eventoCatalogo.AGREGAR_LIBRO);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 0, 2, 0);

        add(labelTitulo, gbc);
        gbc.gridy = 1;
        add(labelAutorEditorial, gbc);
        gbc.gridy = 2;
        add(labelCategoriaPaginas, gbc);
        gbc.gridy = 3;
        add(labelPrecio, gbc);
        gbc.gridy = 4;
        habilitacionValor(libro.getStockDisponible() > 0);
    }

    private void modificarAtributos(Libro libro) {
        format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);
        labelTitulo = new JLabel(libro.getTitulo());
        labelAutorEditorial = new JLabel(libro.getAutor() + (!libro.getEditorial().isBlank() ? " - " + libro.getEditorial() : ""));
        labelCategoriaPaginas = new JLabel(libro.getCategoria() + ((libro.getNumeroPaginas() != 0 ? " - " + libro.getNumeroPaginas()+ " pags." : "")));
        labelPrecio = new JLabel(String.valueOf(format.format(libro.getPrecioVenta())));
        botonAgregar = new JButton("Agregar al carrito");
    }

    public void personalizar() {
        labelTitulo.setFont(new Font("Sunglasses", Font.BOLD, 20));
        labelPrecio.setFont(new Font("Sunglasses", Font.BOLD, 20));
        botonAgregar.setBorderPainted(false);
        botonAgregar.setBackground(new Color(98, 218, 93, 199));
    }

    public void habilitacionValor(boolean valor) {
        if (!valor) {
            botonAgregar.setVisible(false);
            add(new JLabel("Unidades no disponibles"), gbc);
        } else {
            add(botonAgregar, gbc);
        }
        repaint();
    }
}
