package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoCantidad implements ActionListener {


    public static final String SUMAR = "Sumar";
    public static final String DISMINUIR = "Disminuir";
    public static final String ELIMINAR = "Eliminar";
    public PanelProducto panelProducto;
    private VentanaPrincipal ventanaPrincipal;
    private Libro producto;

    public EventoCantidad(VentanaPrincipal ventanaPrincipal, Libro producto, PanelProducto panelProducto) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.producto = producto;
        this.panelProducto = panelProducto;
    }

    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();
        switch (evento) {
            case SUMAR -> ventanaPrincipal.sumarProductoCarrito(producto, panelProducto);
            case DISMINUIR -> ventanaPrincipal.disminuirProductoCarrito(producto, panelProducto);
            case ELIMINAR -> ventanaPrincipal.eliminarProductoCarrito(producto, panelProducto);
        }
    }
}
