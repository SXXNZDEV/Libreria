package co.edu.uptc.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.*;

public class PanelCompras extends JPanel {

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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"},{"01/01/25", "Harry Potter", "3", "Tunja", "20.99"}}, new Object[]{"Fecha y Hora", "Producto", "Cantidad", "Dirección", "Valor"});

        tablaCompras = new JTable(tableModel);


        JTableHeader tableHeader = tablaCompras.getTableHeader();
        tableHeader.setBackground(new Color(0x24242C));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scroll = new JScrollPane(tablaCompras);
        scroll.setPreferredSize(new Dimension(200, 350));

        add(scroll, gbc);
    }
}
