package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class PanelResumenCompra extends JPanel {

    private JLabel labelSubtotalValor;
    private JLabel labelImpuestosValor;
    private JLabel labelTotalValor;
    private JLabel labelTitulo;
    private JLabel labelSubtotal;
    private JLabel labelImpuestos;
    private JLabel labelTotal;
    private NumberFormat format;
    private JButton botonComprar;


    public PanelResumenCompra(Evento evento) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(Color.pink);
        format = NumberFormat.getCurrencyInstance();

        initAtributos();
        modificarFont();

        botonComprar.addActionListener(evento);
        botonComprar.setActionCommand(evento.COMPRAR);

        medidasLayout(gbc);
    }

    public void initAtributos() {
        format = NumberFormat.getCurrencyInstance();
        labelTitulo = new JLabel("Resumen Compra");
        labelImpuestos = new JLabel("Impuestos");
        labelSubtotal = new JLabel("Subtotal");
        labelTotal = new JLabel("Total");
        labelImpuestosValor = new JLabel(format.format(0));
        labelSubtotalValor = new JLabel(format.format(0));
        labelTotalValor = new JLabel(format.format(0));
        botonComprar = new JButton("Comprar");
    }

    public void modificarFont() {
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTotalValor.setFont(new Font("Arial", Font.BOLD, 18));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
    }

    public void medidasLayout(GridBagConstraints gbc) {
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(labelTitulo, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(labelSubtotal, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        add(labelSubtotalValor, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 2;
        add(labelImpuestos, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        add(labelImpuestosValor, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 3;
        add(labelTotal, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        add(labelTotalValor, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(botonComprar, gbc);

        gbc.weighty = 1.0;
        gbc.weightx = 0;
        gbc.gridx++;

        add(new JLabel(), gbc);
    }

    public void modificarValor(ValorCompra valorCompra) {
        labelImpuestosValor.setText(format.format(valorCompra.getImpuestos()));
        labelSubtotalValor.setText(format.format(valorCompra.getSubtotal()));
        labelTotalValor.setText(format.format(valorCompra.getTotal()));
        repaint();
    }
}
