package co.edu.uptc.gui;

import co.edu.uptc.negocio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PanelCarrito extends JPanel {

    private JLabel labelTitulo;
    private JScrollPane scrollPane;
    private JPanel panelCompras;
    private JPanel panelProductos;
    private ArrayList<JPanel> listPanelesProductos;
    private GridBagConstraints gbcGeneral;
    private ManejoUsuarioJSON manejoUsuarioJSON;
    private CalculadoraIVA calculadoraIVA;
    private NumberFormat numberFormat;
    private JLabel labelTituloCompra;
    private JLabel labelSubtotal;
    private JLabel labelSubtotalValor;
    private JLabel labelImpuestos;
    private JLabel labelImpuestosValor;
    private JLabel labelTotal;
    private JLabel labelTotalValor;
    private JButton botonComprar;

    public PanelCarrito() {
        listPanelesProductos = new ArrayList<>();
        gbcGeneral = new GridBagConstraints();
        panelCompras = new JPanel();
        agregarPaneles();
        calculadoraIVA = new CalculadoraIVA();
        numberFormat = NumberFormat.getCurrencyInstance();
        labelTituloCompra = new JLabel("Resumen Compra");
        labelSubtotal = new JLabel("Subtotal");
        labelSubtotalValor = new JLabel();
        labelImpuestos = new JLabel("Impuestos");
        labelImpuestosValor = new JLabel();
        labelTotal = new JLabel("Total");
        labelTotalValor = new JLabel();
        botonComprar = new JButton("Comprar");
    }

    public void agregarPaneles() {
        setLayout(new GridBagLayout());

        gbcGeneral.gridy = 0;
        gbcGeneral.gridx = 0;
        gbcGeneral.weightx = 1.0;
        gbcGeneral.fill = GridBagConstraints.HORIZONTAL;
        gbcGeneral.anchor = GridBagConstraints.NORTHWEST;
        gbcGeneral.insets = new Insets(5, 5, 5, 5);

        labelTitulo = new JLabel("Mi carrito");
        Font fontTitulo = new Font("Arial", Font.BOLD, 30);
        labelTitulo.setFont(fontTitulo);
        add(labelTitulo, gbcGeneral);
    }

    public void anadirProductosPanel(ArrayList<Libro> librosCarrito, ManejoUsuarioJSON manejoUsuarioJSON) {
        this.manejoUsuarioJSON = manejoUsuarioJSON;
        listPanelesProductos = new ArrayList<>();

        if (panelProductos != null) {
            panelProductos.removeAll();
            panelProductos.revalidate();
            panelProductos.repaint();
        }

        panelProductos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 0;

        if (librosCarrito.isEmpty()) {
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JLabel label = new JLabel("No hay productos seleccionados");
            panelProductos.add(label, gbc);
        } else {

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            for (Libro libro : librosCarrito) {
                JPanel panel = crearPanelProducto(libro, librosCarrito);
                gbc.gridy++;
                panelProductos.add(panel, gbc);
                listPanelesProductos.add(panel);
            }
            gbc.gridy++;
            gbc.weighty = 4;
            panelProductos.add(new JLabel(), gbc);
        }

        gbcGeneral.gridy = 1;
        gbcGeneral.gridheight = 3;
        gbcGeneral.weighty = 2;
        gbcGeneral.fill = GridBagConstraints.BOTH;

        if (scrollPane != null) {
            remove(scrollPane);
        }

        scrollPane = new JScrollPane(panelProductos);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setPreferredSize(new Dimension(200, 120));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, gbcGeneral);


        gbcGeneral.weighty = 0.1;
        gbcGeneral.gridy = 2;
        gbcGeneral.gridx = 0;

        gbcGeneral.insets = new Insets(20, 10, 0, 10);
        gbcGeneral.anchor = GridBagConstraints.SOUTH;
        gbcGeneral.fill = GridBagConstraints.HORIZONTAL;
        panelCompras = crearPanelResumenCompra();
        add(panelCompras, gbcGeneral);
        revalidate();
        repaint();
    }

    public JPanel crearPanelProducto(Libro libro, ArrayList<Libro> librosCarrito) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.lightGray);
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);

        JLabel labelNombreProducto = new JLabel(libro.getTitulo());
        JButton botonAumentar = new JButton("+");
        JButton botonDisminuir = new JButton("-");
        JLabel labelCantidad = new JLabel(String.valueOf(libro.getCantidadDisponible()));
        JButton botonEliminar = new JButton("Eliminar");
        JLabel labelPrecio = new JLabel(format.format(libro.getPrecioVenta()));

        botonAumentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = librosCarrito.indexOf(libro);
                if (index >= 0 && index < librosCarrito.size()) {
                    librosCarrito.get(index).aumentarCantidad(1);
                    if (labelCantidad.getText().equals("1")) {
                        botonDisminuir.setVisible(true);
                    }
                    try {
                        manejoUsuarioJSON.escribirUsuarioLogin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    labelPrecio.setText(format.format(calculadoraIVA.subtotalProducto(libro, librosCarrito)));
                    labelCantidad.setText(String.valueOf(librosCarrito.get(index).getCantidadDisponible()));
                    repintarPanelResumenCompra();
                }
            }
        });

        botonDisminuir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = librosCarrito.indexOf(libro);
                if (index >= 0 && index < librosCarrito.size()) {
                    librosCarrito.get(index).disminuirCantidadUnidad();
                    try {
                        manejoUsuarioJSON.escribirUsuarioLogin();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    labelPrecio.setText(format.format(calculadoraIVA.subtotalProducto(libro, librosCarrito)));
                    labelCantidad.setText(String.valueOf(librosCarrito.get(index).getCantidadDisponible()));
                    repintarPanelResumenCompra();
                    if (labelCantidad.getText().equals("1")) {
                        botonDisminuir.setVisible(false);
                    }
                }
            }
        });


        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(labelNombreProducto, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(botonDisminuir, gbc);
        gbc.gridx = 4;
        gbc.gridwidth = 1;
        panel.add(labelCantidad, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        panel.add(botonAumentar, gbc);
        gbc.gridx = 6;
        gbc.gridwidth = 1;
        panel.add(labelPrecio, gbc);
        gbc.gridx = 7;
        gbc.gridwidth = 1;
        panel.add(botonEliminar, gbc);
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(panel, librosCarrito);
                repintarPanelResumenCompra();
            }
        });

        return panel;
    }

    public void eliminarProducto(JPanel panel, ArrayList<Libro> listLibros) {
        int posicion = listPanelesProductos.indexOf(panel);
        if (posicion >= 0 && posicion < listLibros.size()) {
            listLibros.remove(posicion);
            listPanelesProductos.remove(panel);
            panelProductos.remove(panel);
            manejoUsuarioJSON.eliminarLibroCarrito(posicion);
        }

        if (listLibros.isEmpty()) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            JLabel label = new JLabel("No hay productos en el carrito");
            panelProductos.add(label);
        }
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    public void repintarPanelResumenCompra() {
        remove(panelCompras);
        panelCompras = crearPanelResumenCompra();
        add(panelCompras, gbcGeneral);
        revalidate();
        repaint();
    }

    public JPanel crearPanelResumenCompra() {
        JPanel panelResumenCompra = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panelResumenCompra.setBackground(Color.pink);
        double impuestos = calculadoraIVA.impuestos(manejoUsuarioJSON.getUsuarioLogin().getCarrito());
        double subtotal = calculadoraIVA.subtotal(manejoUsuarioJSON.getUsuarioLogin().getCarrito());
        double total = calculadoraIVA.total(subtotal, impuestos);


        labelSubtotalValor.setText(numberFormat.format(impuestos));
        labelImpuestosValor.setText(numberFormat.format(subtotal));
        labelTotalValor.setText(numberFormat.format(total));

        labelTituloCompra.setFont(new Font("Arial", Font.BOLD, 20));
        labelTotalValor.setFont(new Font("Arial", Font.BOLD, 18));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));

        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Comprado");
            }
        });

        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelResumenCompra.add(labelTituloCompra, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        panelResumenCompra.add(labelSubtotal, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        panelResumenCompra.add(labelSubtotalValor, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        panelResumenCompra.add(labelImpuestos, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        panelResumenCompra.add(labelImpuestosValor, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 3;
        panelResumenCompra.add(labelTotal, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        panelResumenCompra.add(labelTotalValor, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        panelResumenCompra.add(botonComprar, gbc);

        gbc.weighty = 1.0;
        gbc.weightx = 0;
        gbc.gridx++;

        add(new JLabel(), gbc);
        return panelResumenCompra;
    }
}
