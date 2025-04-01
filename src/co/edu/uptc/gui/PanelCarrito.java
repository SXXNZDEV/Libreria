package co.edu.uptc.gui;

import co.edu.uptc.negocio.*;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PanelCarrito extends JPanel {

    private JLabel labelTitulo;
    private JScrollPane scrollPane;
    private JPanel panelProductos;
    private ArrayList<PanelProducto> listPanelesProductos;//
    private GridBagConstraints gbcGeneral;
    private VentanaPrincipal ventanaPrincipal;
    private GridBagConstraints gbcPanelProductos;
    private PanelResumenCompra panelResumenCompra;

    public ArrayList<PanelProducto> getListPanelesProductos() {
        return listPanelesProductos;
    }

    public void eliminarPanelProducto(PanelProducto panelProducto) {
        panelProductos.remove(panelProducto);
    }

    public PanelCarrito(VentanaPrincipal ventanaPrincipal, Evento evento) {
        listPanelesProductos = new ArrayList<>();
        gbcGeneral = new GridBagConstraints();
        gbcPanelProductos = new GridBagConstraints();
        agregarPaneles();
        panelResumenCompra = new PanelResumenCompra(evento);
        this.ventanaPrincipal = ventanaPrincipal;
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

    public void anadirProductosPanel(ArrayList<Libro> librosCarrito) {
        listPanelesProductos = new ArrayList<>();

        if (panelProductos != null) {
            panelProductos.revalidate();
            panelProductos.repaint();
        }

        panelProductos = new JPanel(new GridBagLayout());
        gbcPanelProductos = new GridBagConstraints();
        gbcPanelProductos.insets = new Insets(5, 5, 5, 5);
        gbcPanelProductos.gridy = 0;

        if (librosCarrito.isEmpty()) {
            validarExistenciaProductos();
        } else {
            gbcPanelProductos.fill = GridBagConstraints.HORIZONTAL;
            gbcPanelProductos.weightx = 1.0;
            for (Libro libro : librosCarrito) {
                PanelProducto panelProducto = new PanelProducto(ventanaPrincipal, libro);

                gbcPanelProductos.gridy++;
                panelProductos.add(panelProducto, gbcPanelProductos);
                listPanelesProductos.add(panelProducto);
            }
            gbcPanelProductos.gridy++;
            gbcPanelProductos.weighty = 4;
            panelProductos.add(new JLabel(), gbcPanelProductos);
        }

        gbcGeneral.gridy = 1;
        gbcGeneral.gridheight = 1;
        gbcGeneral.weighty = 1;
        gbcGeneral.fill = GridBagConstraints.BOTH;

        if (scrollPane != null) {
            remove(scrollPane);
        }

        scrollPane = new JScrollPane(panelProductos);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, gbcGeneral);


        gbcGeneral.weighty = 0.1;
        gbcGeneral.gridy = 2;
        gbcGeneral.gridx = 0;

        gbcGeneral.insets = new Insets(0, 10, 0, 10);
        gbcGeneral.anchor = GridBagConstraints.SOUTH;
        gbcGeneral.fill = GridBagConstraints.HORIZONTAL;
        add(panelResumenCompra, gbcGeneral);
        revalidate();
        repaint();
    }

    private void validarExistenciaProductos() {
        if (!listPanelesProductos.isEmpty()) return;
        gbcPanelProductos.weighty = 1.0;
        gbcPanelProductos.fill = GridBagConstraints.BOTH;
        JLabel label = new JLabel("No hay productos seleccionados");
        panelProductos.add(label, gbcPanelProductos);
    }

    public void repaintPanel(ValorCompra valorCompra) {
        modificarValores(valorCompra);
        validarExistenciaProductos();
        panelResumenCompra.repaint();
        revalidate();
        repaint();
    }

    public void modificarValores(ValorCompra valorCompra) {
        panelResumenCompra.modificarValor(valorCompra);
        repaint();
    }
}
