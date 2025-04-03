package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class PanelEliminarLibro extends JPanel {

    private JLabel labelTitulo;
    private JPanel panelLibros;
    private int conteoColumnas;
    private int conteoFilas;
    private GridBagLayout gbPanelLibros;
    private GridBagConstraints gbcPanelLibros;
    private JScrollPane scrollPanelLibros;
    private NumberFormat numberFormat;
    private VentanaPrincipal ventanaPrincipal;
    private JButton botonEliminar;
    private JButton botonCancelar;
    private ArrayList<PanelLibroEliminar> listPanelesLibros;

    public ArrayList<PanelLibroEliminar> getListPanelesLibros() {
        return listPanelesLibros;
    }

    public void eliminarPanelProducto(PanelLibroEliminar panelProducto) {
        panelLibros.remove(panelProducto);
    }

    public PanelEliminarLibro(VentanaPrincipal ventanaPrincipal, Evento evento) {
        initAtributos(ventanaPrincipal);
        GridBagConstraints gbc = new GridBagConstraints();
        personalizarFont();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(400, 200));

        botonEliminar.addActionListener(evento);
        botonEliminar.setActionCommand(evento.FUNCION_ELIMINAR_LIBRO);

        botonCancelar.addActionListener(evento);
        botonCancelar.setActionCommand(evento.CANCELAR_ELIMINAR_LIBRO);

        validarExistenciaProductos();

        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 30, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(labelTitulo, gbc);

        gbc.weighty = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        panelLibros.setBorder(new LineBorder(Color.WHITE));
        panelLibros.setBackground(Color.WHITE);
        scrollPanelLibros = new JScrollPane(panelLibros);
        scrollPanelLibros.getVerticalScrollBar().setUnitIncrement(15);
        scrollPanelLibros.setBackground(Color.WHITE);
        scrollPanelLibros.setBorder(new LineBorder(Color.WHITE));
        add(scrollPanelLibros, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.REMAINDER;
        add(new JLabel(), gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        add(botonEliminar, gbc);

        gbc.gridx = 1;
        add(botonCancelar, gbc);
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

    public void anadirLibrosPanel(PanelLibroEliminar panelLibro) {
        gbcPanelLibros.insets = new Insets(10, 10, 10, 10);
        gbcPanelLibros.fill = GridBagConstraints.NONE;


        gbcPanelLibros.gridx = conteoColumnas;
        gbcPanelLibros.gridy = conteoFilas;
        panelLibros.add(panelLibro, gbcPanelLibros);
        listPanelesLibros.add(panelLibro);

        conteoColumnas++;
        if (conteoColumnas == 3) {
            conteoColumnas = 0;
            conteoFilas++;
        }
    }

    public void crearPanelesLibros(Map<String, ArrayList<Libro>> mapLibros) {
        listPanelesLibros = new ArrayList<>();

        panelLibros.removeAll();
        conteoColumnas = 0;
        conteoFilas = 0;

        gbcPanelLibros.weightx = 1.0;
        gbcPanelLibros.weighty = 1.0;
        gbcPanelLibros.anchor = GridBagConstraints.NORTHWEST;

        for (ArrayList<Libro> libroArrayList : mapLibros.values()) {
            for (Libro libro : libroArrayList) {
                PanelLibroEliminar panelLibro = new PanelLibroEliminar(ventanaPrincipal, libro);
                panelLibro.setPreferredSize(new Dimension(180, 120));
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
        botonEliminar = new JButton("Eliminar");
        botonCancelar = new JButton("Regresar");
        listPanelesLibros = new ArrayList<>();
    }

    private void personalizarFont() {
        Font font = new Font("Arial", Font.BOLD, 30);
        labelTitulo = new JLabel("Eliminar Libros");
        labelTitulo.setFont(font);
    }

    private void validarExistenciaProductos() {
        if (panelLibros != null) return;
        gbcPanelLibros.weighty = 1.0;
        gbcPanelLibros.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel("No hay productos seleccionados");
        panelLibros.add(label, gbcPanelLibros);
    }
}
