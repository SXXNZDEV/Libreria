package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoEliminar implements ActionListener {

    public static final String ELIMINAR = "Eliminar Libro";
    public static final String CANCELAR_ELIMINAR = "Cancelar Eliminar Libro";
    private VentanaPrincipal ventanaPrincipal;
    private Libro libro;
    private PanelLibroEliminar panelLibroEliminar;

    public EventoEliminar(VentanaPrincipal ventanaPrincipal, Libro libro, PanelLibroEliminar panelLibroEliminar) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.libro = libro;
        this.panelLibroEliminar = panelLibroEliminar;
    }



    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();

        switch (evento) {}
    }
}
