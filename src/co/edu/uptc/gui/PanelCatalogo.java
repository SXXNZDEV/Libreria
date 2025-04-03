package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
        initAtributos(ventanaPrincipal);
        GridBagConstraints gbc = new GridBagConstraints();
        personalizarFont();

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
        gbc.gridy = 1;
        panelLibros.setBorder(new LineBorder(Color.WHITE));
        scrollPanelLibros = new JScrollPane(panelLibros);
        scrollPanelLibros.getVerticalScrollBar().setUnitIncrement(15);
        scrollPanelLibros.setBorder(new LineBorder(Color.WHITE));

        add(scrollPanelLibros, gbc);
        repaint();
    }

    private void personalizarFont() {
        Font font = new Font("Arial", Font.BOLD, 30);
        labelTitulo = new JLabel("Catálogo de Libros");
        labelTitulo.setFont(font);
    }

    private void initAtributos(VentanaPrincipal ventanaPrincipal) {
        setLayout(new GridBagLayout());
        gbcPanelLibros = new GridBagConstraints();
        gbPanelLibros = new GridBagLayout();
        panelLibros = new JPanel(gbPanelLibros);
        this.ventanaPrincipal = ventanaPrincipal;
        conteoFilas = 0;
        conteoColumnas = 0;
        numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setMinimumFractionDigits(0);
    }

    public void crearPanelesLibros(Map<String, ArrayList<Libro>> mapLibros) {

        panelLibros.removeAll();
        conteoColumnas = 0;
        conteoFilas = 0;

        gbcPanelLibros.weighty = 1;
        gbcPanelLibros.weightx = 1;
        gbcPanelLibros.anchor = GridBagConstraints.NORTHWEST;

        for (ArrayList<Libro> libroArrayList : mapLibros.values()) {
            for (Libro libro : libroArrayList) {
                PanelLibro panelLibro = new PanelLibro(ventanaPrincipal, libro);
                panelLibro.setPreferredSize(new Dimension(270, 150));
                anadirLibrosPanel(panelLibro);
            }
        }

        if (panelLibros.getComponentCount() == 0) {
            repintarPanelLibros();
            panelLibros.revalidate();
            panelLibros.repaint();
        }
        revalidate();
        repaint();
    }

    public void repintarPanelLibros() {
        gbcPanelLibros.gridy = 0;
        gbcPanelLibros.gridx = 0;
        gbcPanelLibros.weightx = 1;
        gbcPanelLibros.weighty = 1;
        gbcPanelLibros.fill = GridBagConstraints.CENTER;
        conteoColumnas = 0;
        conteoFilas = 0;
        panelLibros.add(new JLabel("No hay libros registrados..."), gbcPanelLibros);
    }

    public void anadirLibrosPanel(PanelLibro panelLibro) {
        gbcPanelLibros.weightx = 1.0;
        gbcPanelLibros.insets = new Insets(10, 10, 10, 10);
        gbcPanelLibros.fill = GridBagConstraints.NONE;
        gbcPanelLibros.gridwidth = 1;

        gbcPanelLibros.gridx = conteoColumnas;
        gbcPanelLibros.gridy = conteoFilas;
        panelLibros.add(panelLibro, gbcPanelLibros);

        conteoColumnas++;
        if (conteoColumnas == 2) {
            conteoColumnas = 0;
            conteoFilas++;
        }
    }
}
