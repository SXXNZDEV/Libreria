package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoCatalogo implements ActionListener {

    public static final String AGREGAR_LIBRO = "Agregar Libro";
    private VentanaPrincipal ventanaPrincipal;
    private Libro libro;
    private PanelLibro panelLibro;

    public EventoCatalogo(VentanaPrincipal ventanaPrincipal, Libro libro, PanelLibro panelLibro) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.libro = libro;
        this.panelLibro = panelLibro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();
        switch (evento){
            case AGREGAR_LIBRO -> ventanaPrincipal.anadirProductosCarrito(libro, 1, panelLibro);
        }
    }
}
