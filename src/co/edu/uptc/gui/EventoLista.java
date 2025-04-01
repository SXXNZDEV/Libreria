package co.edu.uptc.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EventoLista implements ItemListener {

    public VentanaPrincipal ventanaPrincipal;

    public EventoLista(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == itemEvent.SELECTED) {
            String seleccion = itemEvent.getItem().toString();
            ventanaPrincipal.llenarCamposModificarLibros(seleccion);
        }
    }

}
