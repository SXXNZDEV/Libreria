package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelCompras extends JPanel{

    private JTable tablaCompras;
    private JLabel labelTitulo;

    public PanelCompras() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        labelTitulo = new JLabel("Mis Compras");
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        add(labelTitulo, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        tablaCompras = new JTable(3, 5);
        tablaCompras.setValueAt("Fecha y hora", 0, 0);
        tablaCompras.setValueAt("Producto", 0, 1);
        tablaCompras.setValueAt("Cantidad", 0, 2);
        tablaCompras.setValueAt("Dirección", 0, 3);
        tablaCompras.setValueAt("Valor", 0, 4);
        tablaCompras.setValueAt("dd/mm/AA-HH:MM", 1, 0);
        tablaCompras.setValueAt("Libro X", 1, 1);
        tablaCompras.setValueAt("2", 1, 2);
        tablaCompras.setValueAt("Yes", 1, 3);
        tablaCompras.setValueAt("$2000", 1, 4);
        add(tablaCompras, gbc);
    }
}
