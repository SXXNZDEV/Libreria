package co.edu.uptc.gui;

import co.edu.uptc.negocio.ProductoCarrito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelCarrito extends JPanel {

    private JLabel labelTitulo;

    private ArrayList<ProductoCarrito> productosCarrito;
    private ArrayList<JPanel> panelesProductos;

    public PanelCarrito() {
        productosCarrito = new ArrayList<>();
        panelesProductos = new ArrayList<>();

        //Esto simula lo que es productos, solo es simulacion
        productosCarrito.add(new ProductoCarrito("Libro 1"));
        productosCarrito.add(new ProductoCarrito("Libro 2"));
        productosCarrito.add(new ProductoCarrito("Libro 3"));

        agregarPaneles();
    }

    public void agregarPaneles() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        labelTitulo = new JLabel("Mi carrito");
        Font fontTitulo = new Font("Arial", Font.BOLD, 30);
        labelTitulo.setFont(fontTitulo);
        add(labelTitulo, gbc);

        if (productosCarrito.isEmpty()) {
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            JLabel label = new JLabel("No hay productos seleccionados");
            add(label, gbc);
            return;
        }

        for (ProductoCarrito productoCarrito : productosCarrito) {
            JPanel panel = crearPanelProducto(productoCarrito);
            gbc.gridy++;
            add(panel, gbc);
            panelesProductos.add(panel);
        }

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weighty = 0.3;
        gbc.gridx = 0;

        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(crearPanelResumenCompra(productosCarrito), gbc);

    }

    public JPanel crearPanelProducto(ProductoCarrito producto) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        JLabel labelImagen = new JLabel("IMAGEN");
        JLabel labelNombreProducto = new JLabel(producto.getNombre());
        JButton botonAumentar = new JButton("+");
        JButton botonDisminuir = new JButton("-");
        JLabel labelCantidad = new JLabel(String.valueOf(producto.getCantidad()));
        JButton botonEliminar = new JButton("Eliminar");
        JLabel labelPrecio = new JLabel(String.valueOf(producto.getPrecio()));

        botonAumentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productosCarrito.indexOf(producto);
                if (index >= 0 && index < productosCarrito.size()) {
                    productosCarrito.get(index).aumentarCantidad();
                    labelCantidad.setText(String.valueOf(productosCarrito.get(index).getCantidad()));
                }
            }
        });

        botonDisminuir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productosCarrito.indexOf(producto);
                if (index >= 0 && index < productosCarrito.size()) {
                    productosCarrito.get(index).disminuirCantidad();
                    labelCantidad.setText(String.valueOf(productosCarrito.get(index).getCantidad()));
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto(panel);
            }
        });

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
        panel.add(labelPrecio, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(botonDisminuir, gbc);
        gbc.gridx = 6;
        gbc.gridwidth = 1;
        panel.add(labelCantidad, gbc);
        gbc.gridx = 7;
        gbc.gridwidth = 1;
        panel.add(botonAumentar, gbc);
        gbc.gridx = 8;
        gbc.weightx = 1.0;
        panel.add(botonEliminar);

        return panel;
    }

    public void eliminarProducto(JPanel panel) {
        int posicion = panelesProductos.indexOf(panel);
        if (posicion >= 0 && posicion < productosCarrito.size()) {
            productosCarrito.remove(posicion);
            panelesProductos.remove(panel);

            remove(panel);
            revalidate();
            repaint();
        }
    }

    public JPanel crearPanelResumenCompra(ArrayList<ProductoCarrito> productosCarrito) {
        JPanel panelResumenCompra = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelResumenCompra.setBackground(Color.lightGray);

        JLabel labelTitulo = new JLabel("Resumen Compra");
        JLabel labelSubtotal = new JLabel("Subtotal");
        JLabel labelSubtotalValor = new JLabel("$0.00");
        JLabel labelImpuestos = new JLabel("Impuestos");
        JLabel labelImpuestosValor = new JLabel("$0.00");
        JLabel labelTotal = new JLabel("Total");
        JLabel labelTotalValor = new JLabel("$0.00");
        JButton botonComprar = new JButton("Comprar");

        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
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
        panelResumenCompra.add(labelTitulo, gbc);
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
