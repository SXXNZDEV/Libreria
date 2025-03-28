package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class PanelCatalogo extends JPanel {

    private JLabel labelTitulo;
    private JPanel panelLibros;
    private int conteoColumnas;
    private int conteoFilas;
    private GridBagLayout gbPanelLibros;
    private GridBagConstraints gbcPanelLibros;
    private JScrollPane scrollPanelLibros;
    private NumberFormat numberFormat;
    private VentanaPrincipal ventanaPrincipal;

    public PanelCatalogo(VentanaPrincipal ventanaPrincipal) {
        setLayout(new GridBagLayout());
        gbcPanelLibros = new GridBagConstraints();
        gbPanelLibros = new GridBagLayout();
        panelLibros = new JPanel(gbPanelLibros);
        this.ventanaPrincipal = ventanaPrincipal;
        conteoFilas = 0;
        conteoColumnas = 0;
        numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setMinimumFractionDigits(0);

        GridBagConstraints gbc = new GridBagConstraints();

        Font font = new Font("Arial", Font.BOLD, 30);
        labelTitulo = new JLabel("Catálogo de Libros");
        labelTitulo.setFont(font);

        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 30, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;


        gbc.weighty = 0.9;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        panelLibros.setBorder(new LineBorder(Color.black));
        gbc.gridy = 1;
        panelLibros.setBorder(new LineBorder(Color.WHITE));
        scrollPanelLibros = new JScrollPane(panelLibros);
        scrollPanelLibros.getVerticalScrollBar().setUnitIncrement(15);
        scrollPanelLibros.setBorder(new LineBorder(Color.WHITE));

        add(scrollPanelLibros, gbc);
    }

    public void crearPanelesLibros(Map<String, ArrayList<Libro>> mapLibros) {

        if (panelLibros != null) {
            repintarPanelLibros();
        }

        for (ArrayList<Libro> libroArrayList : mapLibros.values()) {
            for (Libro libro : libroArrayList) {
                JPanel panelLibro = new JPanel(new GridBagLayout());
                panelLibro.setPreferredSize(new Dimension(20, 150));
                GridBagConstraints gbc = new GridBagConstraints();
                panelLibro.setBorder(new LineBorder(Color.WHITE, 1, true));
                panelLibro.setBackground(Color.WHITE);

                JLabel labelTitulo = new JLabel(libro.getTitulo());
                JLabel labelAutorEditorial = new JLabel(libro.getAutor() + " - " + libro.getEditorial());
                JLabel labelCategoriaPaginas = new JLabel(libro.getCategoria() + " - " + libro.getNumeroPaginas() + " pags.");
                JLabel labelPrecioVenta = new JLabel(numberFormat.format(libro.getPrecioVenta()));
                JButton botonAgregar = new JButton("Agregar al carrito");

                labelTitulo.setFont(new Font("Sunglasses", Font.BOLD, 20));
                labelPrecioVenta.setFont(new Font("Sunglasses", Font.BOLD, 20));
                botonAgregar.setBorderPainted(false);
                botonAgregar.setBackground(new Color(98, 218, 93, 199));

                botonAgregar.addActionListener(e -> {
                    String titulo = labelTitulo.getText();
                    ventanaPrincipal.anadirProductosCarrito(titulo, 1);
                });

                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.insets = new Insets(2, 0, 2, 0);

                panelLibro.add(labelTitulo, gbc);
                gbc.gridy = 1;
                panelLibro.add(labelAutorEditorial, gbc);
                gbc.gridy = 2;
                panelLibro.add(labelCategoriaPaginas, gbc);
                gbc.gridy = 3;
                panelLibro.add(labelPrecioVenta, gbc);
                gbc.gridy = 4;
                panelLibro.add(botonAgregar, gbc);
                anadirLibrosPanel(panelLibro);
            }
        }
    }

    public void repintarPanelLibros() {
        panelLibros.removeAll();
        panelLibros.revalidate();
        panelLibros.repaint();
        conteoColumnas = 0;
        conteoFilas = 0;
    }

    public void anadirLibrosPanel(JPanel panel) {
        gbcPanelLibros.weightx = 1.0;
        gbcPanelLibros.insets = new Insets(10, 10, 10, 10);
        gbcPanelLibros.fill = GridBagConstraints.BOTH;
        gbcPanelLibros.gridwidth = 1;

        gbcPanelLibros.gridx = conteoColumnas;
        gbcPanelLibros.gridy = conteoFilas;
        panelLibros.add(panel, gbcPanelLibros);

        conteoColumnas++;
        if (conteoColumnas == 2) {
            conteoColumnas = 0;
            conteoFilas++;
        }
    }
}
