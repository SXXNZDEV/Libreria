package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import java.awt.*;

import java.text.NumberFormat;

public class PanelProducto extends JPanel {

    private JLabel labelNombreProducto;
    private JButton botonAumentar;
    private JButton botonDisminuir;
    private JLabel labelCantidad;
    private JButton botonEliminar;
    private JLabel labelPrecio;
    private NumberFormat format;
    private Libro producto;
    private EventoCantidad eventoCantidad;

    public void setBotonDisminuir(JButton botonDisminuir) {
        this.botonDisminuir = botonDisminuir;
    }

    public NumberFormat getFormat() {
        return format;
    }

    public void setFormat(NumberFormat format) {
        this.format = format;
    }

    public Libro getProducto() {
        return producto;
    }

    public void setProducto(Libro producto) {
        this.producto = producto;
    }

    public JLabel getLabelNombreProducto() {
        return labelNombreProducto;
    }

    public void setLabelNombreProducto(JLabel labelNombreProducto) {
        this.labelNombreProducto = labelNombreProducto;
    }

    public JButton getBotonAumentar() {
        return botonAumentar;
    }

    public void setBotonAumentar(JButton botonAumentar) {
        this.botonAumentar = botonAumentar;
    }

    public JButton getBotonDisminuir() {
        return botonDisminuir;
    }

    public void setButtonDisminuir(JButton buttonDisminuir) {
        this.botonDisminuir = buttonDisminuir;
    }

    public JLabel getLabelCantidad() {
        return labelCantidad;
    }

    public void setLabelCantidad(JLabel labelCantidad) {
        this.labelCantidad = labelCantidad;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void setBotonEliminar(JButton botonEliminar) {
        this.botonEliminar = botonEliminar;
    }

    public JLabel getLabelPrecio() {
        return labelPrecio;
    }

    public void setLabelPrecio(JLabel labelPrecio) {
        this.labelPrecio = labelPrecio;
    }

    public PanelProducto(VentanaPrincipal ventanaPrincipal, Libro producto) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.lightGray);

        /*No sé si sea necesario este Map entonces por eso lo comente porque solo puedo pasar el libro y después obtener sus atributos y valores*/
        //Map<String, ArrayList<Libro>> catalogo = librosDisp;
        format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(1);
        this.producto = producto;
        this.eventoCantidad = new EventoCantidad(ventanaPrincipal, producto, this);

        labelNombreProducto = new JLabel(producto.getTitulo());
        botonAumentar = new JButton("+");
        botonDisminuir = new JButton("-");
        labelCantidad = new JLabel(String.valueOf(producto.getStockReservado()));
        botonEliminar = new JButton("Eliminar");
        labelPrecio = new JLabel(format.format(producto.getPrecioVenta() * producto.getStockReservado()));

        botonAumentar.addActionListener(eventoCantidad);
        botonAumentar.setActionCommand(EventoCantidad.SUMAR);
        botonDisminuir.addActionListener(eventoCantidad);
        botonDisminuir.setActionCommand(EventoCantidad.DISMINUIR);
        botonEliminar.addActionListener(eventoCantidad);
        botonEliminar.setActionCommand(EventoCantidad.ELIMINAR);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(labelNombreProducto, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(botonDisminuir, gbc);
        gbc.gridx = 4;
        gbc.gridwidth = 1;
        add(labelCantidad, gbc);
        gbc.gridx = 5;
        gbc.gridwidth = 1;
        add(botonAumentar, gbc);
        gbc.gridx = 6;
        gbc.gridwidth = 1;
        add(labelPrecio, gbc);
        gbc.gridx = 7;
        gbc.gridwidth = 1;
        add(botonEliminar, gbc);
        botonDisminuir.setVisible(producto.getStockReservado() > 1);
        repaint();
    }

    public void actualizarPrecio(double precio) {
        labelPrecio.setText(format.format(precio));
    }

    public void actualizarCantidad(int cantidad) {
        labelCantidad.setText(String.valueOf(cantidad));
        botonDisminuir.setVisible(cantidad > 1);
    }
}
