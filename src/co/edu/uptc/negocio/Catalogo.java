package co.edu.uptc.negocio;

import java.util.ArrayList;
import java.util.Map;

public class Catalogo {

    private Map<String, ArrayList<Libro>> mapLibros;

    public Catalogo() {}

    public Map<String, ArrayList<Libro>> getMapLibros() {
        return mapLibros;
    }

    public void setMapLibros(Map<String, ArrayList<Libro>> mapLibros) {
        this.mapLibros = mapLibros;
    }
}
