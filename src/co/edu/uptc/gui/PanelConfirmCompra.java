package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelConfirmCompra extends JDialog {

    private JLabel labelTexto;
    private JRadioButton botonEfectivo;
    private JRadioButton botonTarjeta;
    private JButton botonContinuar;
    private JButton botonCancelar;
    private GridBagConstraints gbc;

    public PanelConfirmCompra(Evento evento) {
        setTitle("Confirmar Compra");
        revalidate();
        repaint();
        preferenciasPanel();

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        add(labelTexto, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(botonEfectivo, gbc);

        gbc.gridx = 1;
        add(botonTarjeta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(botonCancelar, gbc);

        gbc.gridx = 1;
        add(botonContinuar, gbc);
        setModal(true);
        setVisible(false);
        repaint();
    }

    private void preferenciasPanel() {
        setLayout(new GridBagLayout());
        initAtributos();
        setSize(400, 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void initAtributos() {
        gbc = new GridBagConstraints();
        labelTexto = new JLabel("Seleccione el método de pago");
        botonCancelar = new JButton("Cancelar");
        botonContinuar = new JButton("Continuar");
        botonEfectivo  = new JRadioButton("Efectivo");
        botonTarjeta = new JRadioButton("Tarjeta");
    }

    public void visibilizar() {
        setVisible(true);
    }
}
